const express = require('express');
const User = require('../models/User');
const Product = require('../models/Product');
const Order = require('../models/Order');
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

// Get user's cart
router.get('/cart', protect, async (req, res) => {
    try {
        const user = await User.findById(req.user._id)
            .populate('cart.product', 'name price thumbnail images stock');

        res.json({
            success: true,
            data: user.cart
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Error fetching cart',
            error: error.message
        });
    }
});

// Add item to cart
router.post('/cart', protect, async (req, res) => {
    try {
        const { productId, quantity = 1 } = req.body;

        // Check if product exists and is in stock
        const product = await Product.findById(productId);
        if (!product) {
            return res.status(404).json({
                success: false,
                message: 'Product not found'
            });
        }

        if (!product.isInStock(quantity)) {
            return res.status(400).json({
                success: false,
                message: 'Product is out of stock'
            });
        }

        const user = await User.findById(req.user._id);
        await user.addToCart(productId, quantity);

        res.json({
            success: true,
            message: 'Added to cart',
            data: user.cart
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Error adding to cart',
            error: error.message
        });
    }
});

// Update cart item quantity
router.put('/cart/:productId', protect, async (req, res) => {
    try {
        const { productId } = req.params;
        const { quantity } = req.body;

        if (quantity < 1) {
            return res.status(400).json({
                success: false,
                message: 'Quantity must be at least 1'
            });
        }

        // Check if product has enough stock
        const product = await Product.findById(productId);
        if (!product.isInStock(quantity)) {
            return res.status(400).json({
                success: false,
                message: 'Insufficient stock'
            });
        }

        const user = await User.findById(req.user._id);
        await user.updateCartItemQuantity(productId, quantity);

        res.json({
            success: true,
            message: 'Cart updated',
            data: user.cart
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Error updating cart',
            error: error.message
        });
    }
});

// Remove item from cart
router.delete('/cart/:productId', protect, async (req, res) => {
    try {
        const { productId } = req.params;

        const user = await User.findById(req.user._id);
        await user.removeFromCart(productId);

        res.json({
            success: true,
            message: 'Removed from cart',
            data: user.cart
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Error removing from cart',
            error: error.message
        });
    }
});

// Clear cart
router.delete('/cart', protect, async (req, res) => {
    try {
        const user = await User.findById(req.user._id);
        await user.clearCart();

        res.json({
            success: true,
            message: 'Cart cleared'
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Error clearing cart',
            error: error.message
        });
    }
});

// Get user's orders
router.get('/orders', protect, async (req, res) => {
    try {
        const { page = 1, limit = 10, status } = req.query;

        const filter = { user: req.user._id };
        if (status) filter.status = status;

        const orders = await Order.find(filter)
            .populate('items.product', 'name thumbnail images')
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

// Get order by ID
router.get('/orders/:orderId', protect, async (req, res) => {
    try {
        const order = await Order.findOne({
            _id: req.params.orderId,
            user: req.user._id
        })
        .populate('items.product', 'name thumbnail images');

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

// Create order
router.post('/orders', protect, async (req, res) => {
    try {
        const {
            items,
            shippingAddress,
            billingAddress,
            payment,
            subtotal,
            tax,
            shipping,
            discount,
            total,
            notes
        } = req.body;

        // Validate cart items
        for (const item of items) {
            const product = await Product.findById(item.product);
            if (!product || !product.isInStock(item.quantity)) {
                return res.status(400).json({
                    success: false,
                    message: `Product ${item.name} is out of stock`
                });
            }
        }

        // Reserve stock
        for (const item of items) {
            const product = await Product.findById(item.product);
            await product.reserveStock(item.quantity);
        }

        // Create order
        const order = new Order({
            user: req.user._id,
            items,
            shippingAddress,
            billingAddress,
            payment,
            subtotal,
            tax,
            shipping,
            discount,
            total,
            notes
        });

        await order.save();

        // Clear user's cart
        const user = await User.findById(req.user._id);
        await user.clearCart();

        res.status(201).json({
            success: true,
            message: 'Order created successfully',
            data: order
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Error creating order',
            error: error.message
        });
    }
});

// Cancel order
router.put('/orders/:orderId/cancel', protect, async (req, res) => {
    try {
        const { reason } = req.body;
        const { orderId } = req.params;

        const order = await Order.findOne({
            _id: orderId,
            user: req.user._id
        });

        if (!order) {
            return res.status(404).json({
                success: false,
                message: 'Order not found'
            });
        }

        // Check if order can be cancelled
        if (['shipped', 'delivered'].includes(order.status)) {
            return res.status(400).json({
                success: false,
                message: 'Cannot cancel shipped or delivered order'
            });
        }

        // Cancel order and release stock
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

// Add review to order
router.post('/orders/:orderId/reviews', protect, async (req, res) => {
    try {
        const { orderId } = req.params;
        const { productId, rating, comment } = req.body;

        const order = await Order.findOne({
            _id: orderId,
            user: req.user._id,
            status: 'delivered'
        });

        if (!order) {
            return res.status(404).json({
                success: false,
                message: 'Order not found or not delivered'
            });
        }

        // Check if product is in order
        const orderItem = order.items.find(item => 
            item.product.toString() === productId
        );

        if (!orderItem) {
            return res.status(400).json({
                success: false,
                message: 'Product not found in order'
            });
        }

        // Add review to product
        const product = await Product.findById(productId);
        product.reviews.push({
            user: req.user._id,
            rating,
            title: `Review for ${product.name}`,
            comment
        });

        await product.updateRating();

        // Add review to order
        order.reviews.push({
            product: productId,
            rating,
            comment
        });

        await order.save();

        res.json({
            success: true,
            message: 'Review added successfully'
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Error adding review',
            error: error.message
        });
    }
});

// Get user statistics
router.get('/stats', protect, async (req, res) => {
    try {
        const userId = req.user._id;

        const [
            totalOrders,
            totalSpent,
            pendingOrders,
            deliveredOrders,
            wishlistCount,
            cartCount
        ] = await Promise.all([
            Order.countDocuments({ user: userId }),
            Order.aggregate([
                { $match: { user: userId, status: 'delivered' } },
                { $group: { _id: null, total: { $sum: '$total' } } }
            ]),
            Order.countDocuments({ user: userId, status: 'pending' }),
            Order.countDocuments({ user: userId, status: 'delivered' }),
            User.findById(userId).select('wishlist').then(user => user.wishlist.length),
            User.findById(userId).select('cart').then(user => user.cart.length)
        ]);

        res.json({
            success: true,
            data: {
                totalOrders,
                totalSpent: totalSpent[0]?.total || 0,
                pendingOrders,
                deliveredOrders,
                wishlistCount,
                cartCount
            }
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Error fetching user statistics',
            error: error.message
        });
    }
});

module.exports = router;
