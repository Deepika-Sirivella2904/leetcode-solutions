const mongoose = require('mongoose');
const bcrypt = require('bcryptjs');

const userSchema = new mongoose.Schema({
    name: {
        type: String,
        required: [true, 'Name is required'],
        trim: true,
        maxlength: [50, 'Name cannot exceed 50 characters']
    },
    email: {
        type: String,
        required: [true, 'Email is required'],
        unique: true,
        lowercase: true,
        match: [/^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/, 'Please enter a valid email']
    },
    password: {
        type: String,
        required: [true, 'Password is required'],
        minlength: [6, 'Password must be at least 6 characters'],
        select: false
    },
    avatar: {
        type: String,
        default: 'https://picsum.photos/seed/user/200/200.jpg'
    },
    role: {
        type: String,
        enum: ['user', 'admin'],
        default: 'user'
    },
    phone: {
        type: String,
        match: [/^[+]?[\d\s-()]+$/, 'Please enter a valid phone number']
    },
    addresses: [{
        type: {
            type: String,
            enum: ['home', 'work', 'other'],
            default: 'home'
        },
        street: String,
        city: String,
        state: String,
        zipCode: String,
        country: String,
        isDefault: {
            type: Boolean,
            default: false
        }
    }],
    wishlist: [{
        type: mongoose.Schema.Types.ObjectId,
        ref: 'Product'
    }],
    cart: [{
        product: {
            type: mongoose.Schema.Types.ObjectId,
            ref: 'Product',
            required: true
        },
        quantity: {
            type: Number,
            required: true,
            min: [1, 'Quantity must be at least 1'],
            default: 1
        },
        addedAt: {
            type: Date,
            default: Date.now
        }
    }],
    emailVerified: {
        type: Boolean,
        default: false
    },
    verificationToken: String,
    resetPasswordToken: String,
    resetPasswordExpires: Date,
    lastLogin: Date,
    isActive: {
        type: Boolean,
        default: true
    }
}, {
    timestamps: true
});

// Hash password before saving
userSchema.pre('save', async function(next) {
    if (!this.isModified('password')) return next();
    
    try {
        const salt = await bcrypt.genSalt(10);
        this.password = await bcrypt.hash(this.password, salt);
        next();
    } catch (error) {
        next(error);
    }
});

// Compare password method
userSchema.methods.comparePassword = async function(candidatePassword) {
    return await bcrypt.compare(candidatePassword, this.password);
};

// Get user's cart total
userSchema.methods.getCartTotal = function() {
    return this.cart.reduce((total, item) => {
        return total + (item.product.price * item.quantity);
    }, 0);
};

// Add item to cart
userSchema.methods.addToCart = function(productId, quantity = 1) {
    const existingItem = this.cart.find(item => 
        item.product.toString() === productId.toString()
    );
    
    if (existingItem) {
        existingItem.quantity += quantity;
    } else {
        this.cart.push({ product: productId, quantity });
    }
    
    return this.save();
};

// Remove item from cart
userSchema.methods.removeFromCart = function(productId) {
    this.cart = this.cart.filter(item => 
        item.product.toString() !== productId.toString()
    );
    return this.save();
};

// Update cart item quantity
userSchema.methods.updateCartItemQuantity = function(productId, quantity) {
    const item = this.cart.find(item => 
        item.product.toString() === productId.toString()
    );
    
    if (item) {
        if (quantity <= 0) {
            this.removeFromCart(productId);
        } else {
            item.quantity = quantity;
        }
    }
    
    return this.save();
};

// Clear cart
userSchema.methods.clearCart = function() {
    this.cart = [];
    return this.save();
};

// Add to wishlist
userSchema.methods.addToWishlist = function(productId) {
    if (!this.wishlist.includes(productId)) {
        this.wishlist.push(productId);
        return this.save();
    }
    return this;
};

// Remove from wishlist
userSchema.methods.removeFromWishlist = function(productId) {
    this.wishlist = this.wishlist.filter(id => 
        id.toString() !== productId.toString()
    );
    return this.save();
};

// Get user's orders count
userSchema.virtual('orderCount', {
    ref: 'Order',
    localField: '_id',
    foreignField: 'user',
    count: true
});

module.exports = mongoose.model('User', userSchema);
