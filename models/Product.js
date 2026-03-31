const mongoose = require('mongoose');

const productSchema = new mongoose.Schema({
    name: {
        type: String,
        required: [true, 'Product name is required'],
        trim: true,
        maxlength: [100, 'Product name cannot exceed 100 characters']
    },
    description: {
        type: String,
        required: [true, 'Product description is required'],
        maxlength: [1000, 'Description cannot exceed 1000 characters']
    },
    price: {
        type: Number,
        required: [true, 'Product price is required'],
        min: [0, 'Price cannot be negative']
    },
    originalPrice: {
        type: Number,
        min: [0, 'Original price cannot be negative']
    },
    category: {
        type: String,
        required: [true, 'Product category is required'],
        enum: ['electronics', 'fashion', 'home', 'sports', 'beauty', 'books', 'toys', 'food'],
        lowercase: true
    },
    subcategory: {
        type: String,
        trim: true
    },
    brand: {
        type: String,
        trim: true
    },
    images: [{
        url: {
            type: String,
            required: true
        },
        alt: String,
        isMain: {
            type: Boolean,
            default: false
        }
    }],
    thumbnail: {
        type: String,
        required: true
    },
    sku: {
        type: String,
        unique: true,
        required: [true, 'SKU is required'],
        uppercase: true
    },
    stock: {
        type: Number,
        required: [true, 'Stock quantity is required'],
        min: [0, 'Stock cannot be negative'],
        default: 0
    },
    reserved: {
        type: Number,
        default: 0
    },
    available: {
        type: Number,
        default: function() {
            return this.stock - this.reserved;
        }
    },
    weight: {
        type: Number,
        min: [0, 'Weight cannot be negative']
    },
    dimensions: {
        length: Number,
        width: Number,
        height: Number
    },
    colors: [{
        name: String,
        code: String,
        image: String
    }],
    sizes: [{
        name: String,
        stock: Number
    }],
    features: [String],
    specifications: [{
        key: String,
        value: String
    }],
    rating: {
        average: {
            type: Number,
            default: 0,
            min: [0, 'Rating cannot be less than 0'],
            max: [5, 'Rating cannot be more than 5']
        },
        count: {
            type: Number,
            default: 0
        }
    },
    reviews: [{
        user: {
            type: mongoose.Schema.Types.ObjectId,
            ref: 'User',
            required: true
        },
        rating: {
            type: Number,
            required: true,
            min: [1, 'Rating must be at least 1'],
            max: [5, 'Rating cannot be more than 5']
        },
        title: {
            type: String,
            required: true,
            maxlength: [100, 'Review title cannot exceed 100 characters']
        },
        comment: {
            type: String,
            required: true,
            maxlength: [500, 'Review comment cannot exceed 500 characters']
        },
        images: [String],
        helpful: {
            type: Number,
            default: 0
        },
        verified: {
            type: Boolean,
            default: false
        },
        createdAt: {
            type: Date,
            default: Date.now
        }
    }],
    tags: [String],
    badge: {
        type: String,
        enum: ['new', 'sale', 'bestseller', 'limited', 'trending', 'eco', 'pro', 'hot'],
        lowercase: true
    },
    discount: {
        type: {
            type: String,
            enum: ['percentage', 'fixed'],
            default: 'percentage'
        },
        value: {
            type: Number,
            min: [0, 'Discount value cannot be negative']
        },
        validUntil: Date
    },
    shipping: {
        free: {
            type: Boolean,
            default: false
        },
        cost: {
            type: Number,
            default: 0,
            min: [0, 'Shipping cost cannot be negative']
        },
        weight: Number,
        dimensions: {
            length: Number,
            width: Number,
            height: Number
        }
    },
    seo: {
        title: String,
        description: String,
        keywords: [String]
    },
    status: {
        type: String,
        enum: ['active', 'inactive', 'draft', 'archived'],
        default: 'active'
    },
    featured: {
        type: Boolean,
        default: false
    },
    views: {
        type: Number,
        default: 0
    },
    sales: {
        type: Number,
        default: 0
    },
    createdBy: {
        type: mongoose.Schema.Types.ObjectId,
        ref: 'User',
        required: true
    }
}, {
    timestamps: true
});

// Indexes for better performance
productSchema.index({ name: 'text', description: 'text' });
productSchema.index({ category: 1, status: 1 });
productSchema.index({ price: 1 });
productSchema.index({ 'rating.average': -1 });
productSchema.index({ sales: -1 });
productSchema.index({ views: -1 });

// Virtual for final price after discount
productSchema.virtual('finalPrice').get(function() {
    if (this.discount && this.discount.validUntil && new Date(this.discount.validUntil) > new Date()) {
        if (this.discount.type === 'percentage') {
            return this.price * (1 - this.discount.value / 100);
        } else {
            return Math.max(0, this.price - this.discount.value);
        }
    }
    return this.price;
});

// Virtual for discount percentage
productSchema.virtual('discountPercentage').get(function() {
    if (this.originalPrice && this.originalPrice > this.price) {
        return Math.round(((this.originalPrice - this.price) / this.originalPrice) * 100);
    }
    return 0;
});

// Method to update rating
productSchema.methods.updateRating = function() {
    if (this.reviews.length === 0) {
        this.rating.average = 0;
        this.rating.count = 0;
    } else {
        const totalRating = this.reviews.reduce((sum, review) => sum + review.rating, 0);
        this.rating.average = totalRating / this.reviews.length;
        this.rating.count = this.reviews.length;
    }
    return this.save();
};

// Method to check if product is in stock
productSchema.methods.isInStock = function(quantity = 1) {
    return this.available >= quantity;
};

// Method to reserve stock
productSchema.methods.reserveStock = function(quantity) {
    if (this.isInStock(quantity)) {
        this.reserved += quantity;
        return this.save();
    }
    throw new Error('Insufficient stock');
};

// Method to release reserved stock
productSchema.methods.releaseReservedStock = function(quantity) {
    this.reserved = Math.max(0, this.reserved - quantity);
    return this.save();
};

// Method to confirm sale
productSchema.methods.confirmSale = function(quantity) {
    if (this.reserved >= quantity) {
        this.reserved -= quantity;
        this.stock -= quantity;
        this.sales += quantity;
        return this.save();
    }
    throw new Error('Insufficient reserved stock');
};

// Pre-save middleware to update available stock
productSchema.pre('save', function(next) {
    this.available = this.stock - this.reserved;
    next();
});

module.exports = mongoose.model('Product', productSchema);
