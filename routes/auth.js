const express = require('express');
const jwt = require('jsonwebtoken');
const User = require('../models/User');
const router = express.Router();

// Middleware to protect routes
const protect = async (req, res, next) => {
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
        req.user = await User.findById(decoded.id).select('-password');
        next();
    } catch (error) {
        res.status(401).json({
            success: false,
            message: 'Invalid token.'
        });
    }
};

// Register user
router.post('/register', async (req, res) => {
    try {
        const { name, email, password, phone } = req.body;

        // Check if user already exists
        const existingUser = await User.findOne({ email });
        if (existingUser) {
            return res.status(400).json({
                success: false,
                message: 'User already exists with this email'
            });
        }

        // Create new user
        const user = new User({
            name,
            email,
            password,
            phone
        });

        await user.save();

        // Generate token
        const token = jwt.sign(
            { id: user._id },
            process.env.JWT_SECRET,
            { expiresIn: process.env.JWT_EXPIRE }
        );

        res.status(201).json({
            success: true,
            message: 'User registered successfully',
            data: {
                user: {
                    id: user._id,
                    name: user.name,
                    email: user.email,
                    role: user.role,
                    avatar: user.avatar
                },
                token
            }
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Error registering user',
            error: error.message
        });
    }
});

// Login user
router.post('/login', async (req, res) => {
    try {
        const { email, password } = req.body;

        // Find user and include password
        const user = await User.findOne({ email }).select('+password');

        if (!user || !(await user.comparePassword(password))) {
            return res.status(401).json({
                success: false,
                message: 'Invalid email or password'
            });
        }

        // Update last login
        user.lastLogin = new Date();
        await user.save();

        // Generate token
        const token = jwt.sign(
            { id: user._id },
            process.env.JWT_SECRET,
            { expiresIn: process.env.JWT_EXPIRE }
        );

        res.json({
            success: true,
            message: 'Login successful',
            data: {
                user: {
                    id: user._id,
                    name: user.name,
                    email: user.email,
                    role: user.role,
                    avatar: user.avatar,
                    phone: user.phone,
                    addresses: user.addresses
                },
                token
            }
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Error logging in',
            error: error.message
        });
    }
});

// Get current user profile
router.get('/profile', protect, async (req, res) => {
    try {
        const user = await User.findById(req.user._id)
            .populate('wishlist', 'name price thumbnail images')
            .populate('cart.product', 'name price thumbnail images');

        res.json({
            success: true,
            data: user
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Error fetching profile',
            error: error.message
        });
    }
});

// Update user profile
router.put('/profile', protect, async (req, res) => {
    try {
        const { name, email, phone, avatar } = req.body;
        const updateData = { name, email, phone, avatar };

        // Check if email is being changed and if it's already taken
        if (email && email !== req.user.email) {
            const existingUser = await User.findOne({ email });
            if (existingUser) {
                return res.status(400).json({
                    success: false,
                    message: 'Email already exists'
                });
            }
        }

        const user = await User.findByIdAndUpdate(
            req.user._id,
            updateData,
            { new: true, runValidators: true }
        ).select('-password');

        res.json({
            success: true,
            message: 'Profile updated successfully',
            data: user
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Error updating profile',
            error: error.message
        });
    }
});

// Change password
router.put('/change-password', protect, async (req, res) => {
    try {
        const { currentPassword, newPassword } = req.body;

        // Get user with password
        const user = await User.findById(req.user._id).select('+password');

        // Check current password
        if (!(await user.comparePassword(currentPassword))) {
            return res.status(400).json({
                success: false,
                message: 'Current password is incorrect'
            });
        }

        // Update password
        user.password = newPassword;
        await user.save();

        res.json({
            success: true,
            message: 'Password changed successfully'
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Error changing password',
            error: error.message
        });
    }
});

// Add address
router.post('/addresses', protect, async (req, res) => {
    try {
        const { type, street, city, state, zipCode, country, isDefault } = req.body;

        const user = await User.findById(req.user._id);

        // If this is default, unset other default addresses
        if (isDefault) {
            user.addresses.forEach(addr => {
                addr.isDefault = false;
            });
        }

        user.addresses.push({
            type,
            street,
            city,
            state,
            zipCode,
            country,
            isDefault: isDefault || user.addresses.length === 0
        });

        await user.save();

        res.status(201).json({
            success: true,
            message: 'Address added successfully',
            data: user.addresses
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Error adding address',
            error: error.message
        });
    }
});

// Update address
router.put('/addresses/:addressId', protect, async (req, res) => {
    try {
        const { addressId } = req.params;
        const updateData = req.body;

        const user = await User.findById(req.user._id);

        const addressIndex = user.addresses.findIndex(
            addr => addr._id.toString() === addressId
        );

        if (addressIndex === -1) {
            return res.status(404).json({
                success: false,
                message: 'Address not found'
            });
        }

        // If this is default, unset other default addresses
        if (updateData.isDefault) {
            user.addresses.forEach(addr => {
                addr.isDefault = false;
            });
        }

        user.addresses[addressIndex] = { ...user.addresses[addressIndex], ...updateData };
        await user.save();

        res.json({
            success: true,
            message: 'Address updated successfully',
            data: user.addresses
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Error updating address',
            error: error.message
        });
    }
});

// Delete address
router.delete('/addresses/:addressId', protect, async (req, res) => {
    try {
        const { addressId } = req.params;

        const user = await User.findById(req.user._id);

        user.addresses = user.addresses.filter(
            addr => addr._id.toString() !== addressId
        );

        await user.save();

        res.json({
            success: true,
            message: 'Address deleted successfully',
            data: user.addresses
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Error deleting address',
            error: error.message
        });
    }
});

// Add to wishlist
router.post('/wishlist/:productId', protect, async (req, res) => {
    try {
        const { productId } = req.params;

        await User.findByIdAndUpdate(
            req.user._id,
            { $addToSet: { wishlist: productId } }
        );

        res.json({
            success: true,
            message: 'Added to wishlist'
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Error adding to wishlist',
            error: error.message
        });
    }
});

// Remove from wishlist
router.delete('/wishlist/:productId', protect, async (req, res) => {
    try {
        const { productId } = req.params;

        await User.findByIdAndUpdate(
            req.user._id,
            { $pull: { wishlist: productId } }
        );

        res.json({
            success: true,
            message: 'Removed from wishlist'
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Error removing from wishlist',
            error: error.message
        });
    }
});

// Get wishlist
router.get('/wishlist', protect, async (req, res) => {
    try {
        const user = await User.findById(req.user._id)
            .populate('wishlist', 'name price thumbnail images rating');

        res.json({
            success: true,
            data: user.wishlist
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Error fetching wishlist',
            error: error.message
        });
    }
});

module.exports = router;
