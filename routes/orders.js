const express = require('express');
const Order = require('../models/Order');
const Product = require('../models/Product');
const router = express.Router();

// Middleware to protect routes
const protect = async (req, res, next) => {
    const jwt = require('jsonwebtoken');
    let token;

    if (req.headers.authorization && req.headers.authorization.startsWith('Bearer')) {
        token = req.headers.authorization.split(' ')[1];
    }

    if (!token) {
        return res.status(401).json({
            success: false,
            message: 'Access denied. No token provided.'
        });
    }

    try {
        const decoded = jwt.verify(token, process.env.JWT_SECRET);
        req.user = await User.findById(decoded.id);
        next();
    } catch (error) {
        res.status(401).json({
            success: false,
            message: 'Invalid token.'
        });
    }
};

// Get all orders (for admin)
router.get('/', protect, async (req, res) => {
    try {
        const {
            page = 1,
            limit = 10,
            status,
            startDate,
            endDate,
            search
        } = req.query;

        // Build filter
        const filter = {};
        if (status) filter.status = status;
        if (startDate || endDate) {
            filter.createdAt = {};
            if (startDate) filter.createdAt.$gte = new Date(startDate);
            if (endDate) filter.createdAt.$lte = new Date(endDate);
        }
        if (search) {
            filter.$or = [
                { orderNumber: { $regex: search, $options: 'i' } },
                { 'shippingAddress.city': { $regex: search, $options: 'i' } }
            ];
        }

        const orders = await Order.find(filter)
            .populate('user', 'name email')
            .populate('items.product', 'name thumbnail')
            .sort({ createdAt: -1 })
            .limit(limit * 1)
            .skip((page - 1) * limit);

        const total = await Order.countDocuments(filter);

        res.json({
            success: true,
            data: orders,
            pagination: {
                page: parseInt(page),
                limit: parseInt(limit),
                total,
                pages: Math.ceil(total / limit)
            }
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Error fetching orders',
            error: error.message
        });
    }
});

// Get order statistics
router.get('/stats', protect, async (req, res) => {
    try {
        const [
            totalOrders,
            totalRevenue,
            pendingOrders,
            processingOrders,
            shippedOrders,
            deliveredOrders,
            cancelledOrders,
            recentOrders
        ] = await Promise.all([
            Order.countDocuments(),
            Order.aggregate([
                { $match: { status: 'delivered' } },
                { $group: { _id: null, total: { $sum: '$total' } } }
            ]),
            Order.countDocuments({ status: 'pending' }),
            Order.countDocuments({ status: 'processing' }),
            Order.countDocuments({ status: 'shipped' }),
            Order.countDocuments({ status: 'delivered' }),
            Order.countDocuments({ status: 'cancelled' }),
            Order.find()
                .populate('user', 'name')
                .sort({ createdAt: -1 })
                .limit(5)
        ]);

        // Get monthly sales data
        const monthlySales = await Order.aggregate([
            {
                $match: {
                    status: 'delivered',
                    createdAt: {
                        $gte: new Date(new Date().getFullYear(), 0, 1)
                    }
                }
            },
            {
                $group: {
                    _id: { $month: '$createdAt' },
                    revenue: { $sum: '$total' },
                    orders: { $sum: 1 }
                }
            },
            { $sort: { '_id': 1 } }
        ]);

        res.json({
            success: true,
            data: {
                totalOrders,
                totalRevenue: totalRevenue[0]?.total || 0,
                pendingOrders,
                processingOrders,
                shippedOrders,
                deliveredOrders,
                cancelledOrders,
                recentOrders,
                monthlySales
            }
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Error fetching order statistics',
            error: error.message
        });
    }
});

// Get order by ID
router.get('/:id', protect, async (req, res) => {
    try {
        const order = await Order.findById(req.params.id)
            .populate('user', 'name email phone')
            .populate('items.product', 'name thumbnail images sku')
            .populate('statusHistory.updatedBy', 'name');

        if (!order) {
            return res.status(404).json({
                success: false,
                message: 'Order not found'
            });
        }

        res.json({
            success: true,
            data: order
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Error fetching order',
            error: error.message
        });
    }
});

// Update order status
router.put('/:id/status', protect, async (req, res) => {
    try {
        const { status, note } = req.body;
        const { id } = req.params;

        const order = await Order.findById(id);
        if (!order) {
            return res.status(404).json({
                success: false,
                message: 'Order not found'
            });
        }

        // Validate status transition
        const validTransitions = {
            'pending': ['confirmed', 'cancelled'],
            'confirmed': ['processing', 'cancelled'],
            'processing': ['shipped'],
            'shipped': ['delivered'],
            'delivered': ['refunded'],
            'cancelled': [],
            'refunded': []
        };

        if (!validTransitions[order.status].includes(status)) {
            return res.status(400).json({
                success: false,
                message: `Cannot transition from ${order.status} to ${status}`
            });
        }

        await order.updateStatus(status, note, req.user._id);

        // If order is confirmed, reserve stock
        if (status === 'confirmed') {
            for (const item of order.items) {
                const product = await Product.findById(item.product);
                await product.reserveStock(item.quantity);
            }
        }

        // If order is shipped, add tracking
        if (status === 'shipped' && req.body.tracking) {
            await order.addTracking(
                req.body.tracking.number,
                req.body.tracking.carrier,
                req.body.tracking.url,
                req.body.tracking.estimatedDelivery
            );
        }

        // If order is delivered, confirm sales
        if (status === 'delivered') {
            for (const item of order.items) {
                const product = await Product.findById(item.product);
                await product.confirmSale(item.quantity);
            }
        }

        res.json({
            success: true,
            message: 'Order status updated successfully',
            data: order
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Error updating order status',
            error: error.message
        });
    }
});

// Add tracking information
router.put('/:id/tracking', protect, async (req, res) => {
    try {
        const { trackingNumber, carrier, url, estimatedDelivery } = req.body;
        const { id } = req.params;

        const order = await Order.findById(id);
        if (!order) {
            return res.status(404).json({
                success: false,
                message: 'Order not found'
            });
        }

        await order.addTracking(trackingNumber, carrier, url, estimatedDelivery);

        res.json({
            success: true,
            message: 'Tracking information added',
            data: order
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Error adding tracking information',
            error: error.message
        });
    }
});

// Cancel order
router.put('/:id/cancel', protect, async (req, res) => {
    try {
        const { reason } = req.body;
        const { id } = req.params;

        const order = await Order.findById(id);
        if (!order) {
            return res.status(404).json({
                success: false,
                message: 'Order not found'
            });
        }

        await order.cancel(reason);

        // Release reserved stock
        for (const item of order.items) {
            const product = await Product.findById(item.product);
            await product.releaseReservedStock(item.quantity);
        }

        res.json({
            success: true,
            message: 'Order cancelled successfully',
            data: order
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Error cancelling order',
            error: error.message
        });
    }
});

// Refund order
router.put('/:id/refund', protect, async (req, res) => {
    try {
        const { amount, reason } = req.body;
        const { id } = req.params;

        const order = await Order.findById(id);
        if (!order) {
            return res.status(404).json({
                success: false,
                message: 'Order not found'
            });
        }

        if (order.status !== 'delivered') {
            return res.status(400).json({
                success: false,
                message: 'Only delivered orders can be refunded'
            });
        }

        // Update payment status
        order.payment.status = 'refunded';
        order.status = 'refunded';
        order.statusHistory.push({
            status: 'refunded',
            note: reason,
            updatedBy: req.user._id,
            timestamp: new Date()
        });

        await order.save();

        // Handle stock return if needed
        if (req.body.returnStock) {
            for (const item of order.items) {
                const product = await Product.findById(item.product);
                product.stock += item.quantity;
                await product.save();
            }
        }

        res.json({
            success: true,
            message: 'Order refunded successfully',
            data: order
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Error refunding order',
            error: error.message
        });
    }
});

// Export orders to CSV
router.get('/export/csv', protect, async (req, res) => {
    try {
        const { startDate, endDate, status } = req.query;

        const filter = {};
        if (startDate || endDate) {
            filter.createdAt = {};
            if (startDate) filter.createdAt.$gte = new Date(startDate);
            if (endDate) filter.createdAt.$lte = new Date(endDate);
        }
        if (status) filter.status = status;

        const orders = await Order.find(filter)
            .populate('user', 'name email')
            .populate('items.product', 'name')
            .sort({ createdAt: -1 });

        // Convert to CSV
        const csv = [
            'Order Number,Date,Customer,Email,Status,Total,Items',
            ...orders.map(order => [
                order.orderNumber,
                order.createdAt.toISOString().split('T')[0],
                order.user.name,
                order.user.email,
                order.status,
                order.total,
                order.items.map(item => `${item.name}(${item.quantity})`).join(';')
            ].join(','))
        ].join('\n');

        res.setHeader('Content-Type', 'text/csv');
        res.setHeader('Content-Disposition', 'attachment; filename=orders.csv');
        res.send(csv);
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Error exporting orders',
            error: error.message
        });
    }
});

module.exports = router;
