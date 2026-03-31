const mongoose = require('mongoose');

const orderSchema = new mongoose.Schema({
    user: {
        type: mongoose.Schema.Types.ObjectId,
        ref: 'User',
        required: true
    },
    orderNumber: {
        type: String,
        unique: true,
        required: true
    },
    items: [{
        product: {
            type: mongoose.Schema.Types.ObjectId,
            ref: 'Product',
            required: true
        },
        name: {
            type: String,
            required: true
        },
        price: {
            type: Number,
            required: true
        },
        quantity: {
            type: Number,
            required: true,
            min: [1, 'Quantity must be at least 1']
        },
        image: String,
        subtotal: {
            type: Number,
            required: true
        }
    }],
    shippingAddress: {
        type: {
            type: String,
            enum: ['home', 'work', 'other'],
            required: true
        },
        street: {
            type: String,
            required: true
        },
        city: {
            type: String,
            required: true
        },
        state: {
            type: String,
            required: true
        },
        zipCode: {
            type: String,
            required: true
        },
        country: {
            type: String,
            required: true
        }
    },
    billingAddress: {
        type: {
            type: String,
            enum: ['home', 'work', 'other'],
            required: true
        },
        street: {
            type: String,
            required: true
        },
        city: {
            type: String,
            required: true
        },
        state: {
            type: String,
            required: true
        },
        zipCode: {
            type: String,
            required: true
        },
        country: {
            type: String,
            required: true
        }
    },
    payment: {
        method: {
            type: String,
            enum: ['credit_card', 'debit_card', 'paypal', 'stripe', 'cash_on_delivery'],
            required: true
        },
        status: {
            type: String,
            enum: ['pending', 'processing', 'completed', 'failed', 'refunded'],
            default: 'pending'
        },
        transactionId: String,
        paidAt: Date,
        amount: {
            type: Number,
            required: true
        }
    },
    subtotal: {
        type: Number,
        required: true
    },
    tax: {
        type: Number,
        default: 0
    },
    shipping: {
        type: Number,
        default: 0
    },
    discount: {
        type: Number,
        default: 0
    },
    total: {
        type: Number,
        required: true
    },
    currency: {
        type: String,
        default: 'USD'
    },
    status: {
        type: String,
        enum: ['pending', 'confirmed', 'processing', 'shipped', 'delivered', 'cancelled', 'refunded'],
        default: 'pending'
    },
    tracking: {
        number: String,
        carrier: String,
        url: String,
        estimatedDelivery: Date
    },
    notes: String,
    statusHistory: [{
        status: {
            type: String,
            enum: ['pending', 'confirmed', 'processing', 'shipped', 'delivered', 'cancelled', 'refunded'],
            required: true
        },
        timestamp: {
            type: Date,
            default: Date.now
        },
        note: String,
        updatedBy: {
            type: mongoose.Schema.Types.ObjectId,
            ref: 'User'
        }
    }],
    reviews: [{
        product: {
            type: mongoose.Schema.Types.ObjectId,
            ref: 'Product'
        },
        rating: {
            type: Number,
            min: 1,
            max: 5
        },
        comment: String,
        createdAt: {
            type: Date,
            default: Date.now
        }
    }]
}, {
    timestamps: true
});

// Generate unique order number
orderSchema.pre('save', async function(next) {
    if (this.isNew && !this.orderNumber) {
        const orderCount = await this.constructor.countDocuments();
        this.orderNumber = `VM${String(orderCount + 1).padStart(6, '0')}${Date.now().toString().slice(-4)}`;
    }
    next();
});

// Calculate totals
orderSchema.pre('save', function(next) {
    this.subtotal = this.items.reduce((total, item) => total + item.subtotal, 0);
    this.total = this.subtotal + this.tax + this.shipping - this.discount;
    next();
});

// Method to update order status
orderSchema.methods.updateStatus = function(status, note, updatedBy) {
    this.status = status;
    this.statusHistory.push({
        status,
        note,
        updatedBy,
        timestamp: new Date()
    });
    return this.save();
};

// Method to add tracking information
orderSchema.methods.addTracking = function(trackingNumber, carrier, url, estimatedDelivery) {
    this.tracking = {
        number: trackingNumber,
        carrier,
        url,
        estimatedDelivery
    };
    return this.save();
};

// Method to mark as delivered
orderSchema.methods.markAsDelivered = function() {
    this.status = 'delivered';
    this.statusHistory.push({
        status: 'delivered',
        timestamp: new Date(),
        note: 'Order delivered successfully'
    });
    return this.save();
};

// Method to cancel order
orderSchema.methods.cancel = function(reason) {
    if (['shipped', 'delivered'].includes(this.status)) {
        throw new Error('Cannot cancel shipped or delivered order');
    }
    this.status = 'cancelled';
    this.statusHistory.push({
        status: 'cancelled',
        note: reason,
        timestamp: new Date()
    });
    return this.save();
};

// Virtual for formatted order number
orderSchema.virtual('formattedOrderNumber').get(function() {
    return this.orderNumber.replace(/(\d{4})(\d{4})(\d{4})/, '$1-$2-$3');
});

// Virtual for status display
orderSchema.virtual('statusDisplay').get(function() {
    const statusMap = {
        'pending': 'Order Pending',
        'confirmed': 'Order Confirmed',
        'processing': 'Processing',
        'shipped': 'Shipped',
        'delivered': 'Delivered',
        'cancelled': 'Cancelled',
        'refunded': 'Refunded'
    };
    return statusMap[this.status] || this.status;
});

module.exports = mongoose.model('Order', orderSchema);
