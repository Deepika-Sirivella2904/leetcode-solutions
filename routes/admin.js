const express = require('express');
const Product = require('../models/Product');
const User = require('../models/User');
const Order = require('../models/Order');
const router = express.Router();

// Middleware to protect routes and check admin role
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
        
        if (req.user.role !== 'admin') {
            return res.status(403).json({
                success: false,
                message: 'Access denied. Admin role required.'
            });
        }
        
        next();
    } catch (error) {
        res.status(401).json({
            success: false,
            message: 'Invalid token.'
        });
    }
};

// Get dashboard statistics
router.get('/dashboard', protect, async (req, res) => {
    try {
        const [
            totalProducts,
            totalUsers,
            totalOrders,
            totalRevenue,
            recentOrders,
            topProducts,
            lowStockProducts,
            pendingReviews
        ] = await Promise.all([
            Product.countDocuments({ status: 'active' }),
            User.countDocuments({ role: 'user' }),
            Order.countDocuments(),
            Order.aggregate([
                { $match: { status: 'delivered' } },
                { $group: { _id: null, total: { $sum: '$total' } } }
            ]),
            Order.find()
                .populate('user', 'name')
                .sort({ createdAt: -1 })
                .limit(5),
            Product.find({ status: 'active' })
                .sort({ sales: -1 })
                .limit(5)
                .select('name sales thumbnail'),
            Product.find({ 
                status: 'active',
                $expr: { $lte: ['$available', 10] }
            })
                .sort({ available: 1 })
                .limit(5)
                .select('name available stock'),
            Product.aggregate([
                { $unwind: '$reviews' },
                { $match: { 'reviews.verified': false } },
                { $group: { _id: null, count: { $sum: 1 } } }
            ])
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

        // Get category distribution
        const categoryStats = await Product.aggregate([
            { $match: { status: 'active' } },
            { $group: { _id: '$category', count: { $sum: 1 } } },
            { $sort: { count: -1 } }
        ]);

        res.json({
            success: true,
            data: {
                totalProducts,
                totalUsers,
                totalOrders,
                totalRevenue: totalRevenue[0]?.total || 0,
                recentOrders,
                topProducts,
                lowStockProducts,
                pendingReviews: pendingReviews[0]?.count || 0,
                monthlySales,
                categoryStats
            }
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Error fetching dashboard data',
            error: error.message
        });
    }
});

// Get all products (admin)
router.get('/products', protect, async (req, res) => {
    try {
        const {
            page = 1,
            limit = 20,
            category,
            status,
            search,
            sort = 'createdAt'
        } = req.query;

        const filter = {};
        if (category) filter.category = category;
        if (status) filter.status = status;
        if (search) {
            filter.$or = [
                { name: { $regex: search, $options: 'i' } },
                { description: { $regex: search, $options: 'i' } },
                { sku: { $regex: search, $options: 'i' } }
            ];
        }

        const products = await Product.find(filter)
            .populate('createdBy', 'name')
            .sort({ [sort]: -1 })
            .limit(limit * 1)
            .skip((page - 1) * limit);

        const total = await Product.countDocuments(filter);

        res.json({
            success: true,
            data: products,
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
            message: 'Error fetching products',
            error: error.message
        });
    }
});

// Create product
router.post('/products', protect, async (req, res) => {
    try {
        const productData = {
            ...req.body,
            createdBy: req.user._id
        };

        const product = new Product(productData);
        await product.save();

        res.status(201).json({
            success: true,
            message: 'Product created successfully',
            data: product
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Error creating product',
            error: error.message
        });
    }
});

// Update product
router.put('/products/:id', protect, async (req, res) => {
    try {
        const product = await Product.findByIdAndUpdate(
            req.params.id,
            req.body,
            { new: true, runValidators: true }
        );

        if (!product) {
            return res.status(404).json({
                success: false,
                message: 'Product not found'
            });
        }

        res.json({
            success: true,
            message: 'Product updated successfully',
            data: product
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Error updating product',
            error: error.message
        });
    }
});

// Delete product
router.delete('/products/:id', protect, async (req, res) => {
    try {
        const product = await Product.findByIdAndDelete(req.params.id);

        if (!product) {
            return res.status(404).json({
                success: false,
                message: 'Product not found'
            });
        }

        res.json({
            success: true,
            message: 'Product deleted successfully'
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Error deleting product',
            error: error.message
        });
    }
});

// Get all users
router.get('/users', protect, async (req, res) => {
    try {
        const {
            page = 1,
            limit = 20,
            role,
            search,
            status = 'active'
        } = req.query;

        const filter = { isActive: status === 'active' };
        if (role) filter.role = role;
        if (search) {
            filter.$or = [
                { name: { $regex: search, $options: 'i' } },
                { email: { $regex: search, $options: 'i' } }
            ];
        }

        const users = await User.find(filter)
            .select('-password')
            .sort({ createdAt: -1 })
            .limit(limit * 1)
            .skip((page - 1) * limit);

        const total = await User.countDocuments(filter);

        res.json({
            success: true,
            data: users,
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
            message: 'Error fetching users',
            error: error.message
        });
    }
});

// Update user status
router.put('/users/:id/status', protect, async (req, res) => {
    try {
        const { isActive } = req.body;
        const user = await User.findByIdAndUpdate(
            req.params.id,
            { isActive },
            { new: true }
        ).select('-password');

        if (!user) {
            return res.status(404).json({
                success: false,
                message: 'User not found'
            });
        }

        res.json({
            success: true,
            message: 'User status updated successfully',
            data: user
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Error updating user status',
            error: error.message
        });
    }
});

// Get all orders
router.get('/orders', protect, async (req, res) => {
    try {
        const {
            page = 1,
            limit = 20,
            status,
            startDate,
            endDate
        } = req.query;

        const filter = {};
        if (status) filter.status = status;
        if (startDate || endDate) {
            filter.createdAt = {};
            if (startDate) filter.createdAt.$gte = new Date(startDate);
            if (endDate) filter.createdAt.$lte = new Date(endDate);
        }

        const orders = await Order.find(filter)
            .populate('user', 'name email')
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

// Get sales analytics
router.get('/analytics/sales', protect, async (req, res) => {
    try {
        const { period = 'month' } = req.query;
        let dateFormat;

        switch (period) {
            case 'day':
                dateFormat = { $dateToString: { format: "%Y-%m-%d", date: "$createdAt" } };
                break;
            case 'week':
                dateFormat = { $dateToString: { format: "%Y-%U", date: "$createdAt" } };
                break;
            case 'month':
                dateFormat = { $dateToString: { format: "%Y-%m", date: "$createdAt" } };
                break;
            case 'year':
                dateFormat = { $dateToString: { format: "%Y", date: "$createdAt" } };
                break;
        }

        const salesData = await Order.aggregate([
            { $match: { status: 'delivered' } },
            {
                $group: {
                    _id: dateFormat,
                    revenue: { $sum: '$total' },
                    orders: { $sum: 1 }
                }
            },
            { $sort: { '_id': 1 } }
        ]);

        res.json({
            success: true,
            data: salesData
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Error fetching sales analytics',
            error: error.message
        });
    }
});

// Get top selling products
router.get('/analytics/top-products', protect, async (req, res) => {
    try {
        const { limit = 10 } = req.query;

        const topProducts = await Product.find({ status: 'active' })
            .sort({ sales: -1 })
            .limit(parseInt(limit))
            .select('name sales thumbnail price');

        res.json({
            success: true,
            data: topProducts
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Error fetching top products',
            error: error.message
        });
    }
});

// Get customer analytics
router.get('/analytics/customers', protect, async (req, res) => {
    try {
        const [
            totalCustomers,
            newCustomersThisMonth,
            topCustomers,
            customerGrowth
        ] = await Promise.all([
            User.countDocuments({ role: 'user' }),
            User.countDocuments({
                role: 'user',
                createdAt: {
                    $gte: new Date(new Date().getFullYear(), new Date().getMonth(), 1)
                }
            }),
            Order.aggregate([
                { $group: { _id: '$user', totalSpent: { $sum: '$total' }, orderCount: { $sum: 1 } } },
                { $sort: { totalSpent: -1 } },
                { $limit: 10 },
                {
                    $lookup: {
                        from: 'users',
                        localField: '_id',
                        foreignField: '_id',
                        as: 'userInfo'
                    }
                },
                { $unwind: '$userInfo' },
                {
                    $project: {
                        name: '$userInfo.name',
                        email: '$userInfo.email',
                        totalSpent: 1,
                        orderCount: 1
                    }
                }
            ]),
            User.aggregate([
                {
                    $group: {
                        _id: { $month: '$createdAt' },
                        count: { $sum: 1 }
                    }
                },
                { $sort: { '_id': 1 } }
            ])
        ]);

        res.json({
            success: true,
            data: {
                totalCustomers,
                newCustomersThisMonth,
                topCustomers,
                customerGrowth
            }
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Error fetching customer analytics',
            error: error.message
        });
    }
});

module.exports = router;
