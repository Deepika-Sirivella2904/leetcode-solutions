const express = require('express');
const Product = require('../models/Product');
const router = express.Router();

// Get all products with filtering and pagination
router.get('/', async (req, res) => {
    try {
        const {
            page = 1,
            limit = 12,
            category,
            subcategory,
            minPrice,
            maxPrice,
            sort = 'createdAt',
            order = 'desc',
            search,
            featured,
            badge
        } = req.query;

        // Build filter
        const filter = { status: 'active' };
        
        if (category) filter.category = category;
        if (subcategory) filter.subcategory = subcategory;
        if (featured === 'true') filter.featured = true;
        if (badge) filter.badge = badge;
        
        if (minPrice || maxPrice) {
            filter.price = {};
            if (minPrice) filter.price.$gte = parseFloat(minPrice);
            if (maxPrice) filter.price.$lte = parseFloat(maxPrice);
        }
        
        if (search) {
            filter.$text = { $search: search };
        }

        // Build sort
        const sortOptions = {};
        sortOptions[sort] = order === 'desc' ? -1 : 1;

        // Execute query
        const products = await Product.find(filter)
            .populate('createdBy', 'name')
            .sort(sortOptions)
            .limit(limit * 1)
            .skip((page - 1) * limit)
            .exec();

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

// Get product by ID
router.get('/:id', async (req, res) => {
    try {
        const product = await Product.findById(req.params.id)
            .populate('createdBy', 'name')
            .populate('reviews.user', 'name avatar');

        if (!product) {
            return res.status(404).json({
                success: false,
                message: 'Product not found'
            });
        }

        // Increment views
        product.views += 1;
        await product.save();

        res.json({
            success: true,
            data: product
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Error fetching product',
            error: error.message
        });
    }
});

// Get featured products
router.get('/featured/list', async (req, res) => {
    try {
        const products = await Product.find({ 
            status: 'active', 
            featured: true 
        })
        .populate('createdBy', 'name')
        .sort({ sales: -1 })
        .limit(8);

        res.json({
            success: true,
            data: products
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Error fetching featured products',
            error: error.message
        });
    }
});

// Get products by category
router.get('/category/:category', async (req, res) => {
    try {
        const { category } = req.params;
        const { page = 1, limit = 12, sort = 'createdAt' } = req.query;

        const products = await Product.find({ 
            category, 
            status: 'active' 
        })
        .populate('createdBy', 'name')
        .sort({ [sort]: -1 })
        .limit(limit * 1)
        .skip((page - 1) * limit);

        const total = await Product.countDocuments({ 
            category, 
            status: 'active' 
        });

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
            message: 'Error fetching products by category',
            error: error.message
        });
    }
});

// Search products
router.get('/search/query', async (req, res) => {
    try {
        const { q: query, page = 1, limit = 12 } = req.query;

        if (!query) {
            return res.status(400).json({
                success: false,
                message: 'Search query is required'
            });
        }

        const products = await Product.find({
            $and: [
                { status: 'active' },
                {
                    $or: [
                        { name: { $regex: query, $options: 'i' } },
                        { description: { $regex: query, $options: 'i' } },
                        { tags: { $in: [new RegExp(query, 'i')] } }
                    ]
                }
            ]
        })
        .populate('createdBy', 'name')
        .sort({ 'rating.average': -1 })
        .limit(limit * 1)
        .skip((page - 1) * limit);

        const total = await Product.countDocuments({
            $and: [
                { status: 'active' },
                {
                    $or: [
                        { name: { $regex: query, $options: 'i' } },
                        { description: { $regex: query, $options: 'i' } },
                        { tags: { $in: [new RegExp(query, 'i')] } }
                    ]
                }
            ]
        });

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
            message: 'Error searching products',
            error: error.message
        });
    }
});

// Get product categories with counts
router.get('/categories/list', async (req, res) => {
    try {
        const categories = await Product.aggregate([
            { $match: { status: 'active' } },
            { $group: { _id: '$category', count: { $sum: 1 } } },
            { $sort: { count: -1 } }
        ]);

        res.json({
            success: true,
            data: categories
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Error fetching categories',
            error: error.message
        });
    }
});

// Add product review
router.post('/:id/reviews', async (req, res) => {
    try {
        const { rating, title, comment } = req.body;
        const userId = req.user?.id; // Assuming user is authenticated

        if (!userId) {
            return res.status(401).json({
                success: false,
                message: 'Authentication required'
            });
        }

        const product = await Product.findById(req.params.id);
        if (!product) {
            return res.status(404).json({
                success: false,
                message: 'Product not found'
            });
        }

        // Check if user already reviewed
        const existingReview = product.reviews.find(review => 
            review.user.toString() === userId
        );

        if (existingReview) {
            return res.status(400).json({
                success: false,
                message: 'You have already reviewed this product'
            });
        }

        // Add review
        product.reviews.push({
            user: userId,
            rating,
            title,
            comment
        });

        // Update product rating
        await product.updateRating();

        res.json({
            success: true,
            message: 'Review added successfully',
            data: product
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Error adding review',
            error: error.message
        });
    }
});

// Get product reviews
router.get('/:id/reviews', async (req, res) => {
    try {
        const { page = 1, limit = 10, sort = 'createdAt' } = req.query;

        const product = await Product.findById(req.params.id)
            .populate('reviews.user', 'name avatar')
            .select('reviews');

        if (!product) {
            return res.status(404).json({
                success: false,
                message: 'Product not found'
            });
        }

        const reviews = product.reviews
            .sort((a, b) => {
                if (sort === 'rating') {
                    return b.rating - a.rating;
                }
                return new Date(b.createdAt) - new Date(a.createdAt);
            })
            .slice((page - 1) * limit, page * limit);

        res.json({
            success: true,
            data: reviews,
            pagination: {
                page: parseInt(page),
                limit: parseInt(limit),
                total: product.reviews.length,
                pages: Math.ceil(product.reviews.length / limit)
            }
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Error fetching reviews',
            error: error.message
        });
    }
});

module.exports = router;
