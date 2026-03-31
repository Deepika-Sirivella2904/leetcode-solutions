// VibrantMart E-commerce Website JavaScript
class VibrantMart {
    constructor() {
        this.cart = [];
        this.products = [];
        this.filteredProducts = [];
        this.currentUser = null;
        this.init();
    }

    init() {
        this.setupLoadingScreen();
        this.setupNavigation();
        this.setupAOS();
        this.loadProducts();
        this.setupEventListeners();
        this.setupCart();
        this.setupSearch();
        this.setupFilters();
        this.setupModals();
        this.loadCartFromStorage();
    }

    // Loading Screen
    setupLoadingScreen() {
        window.addEventListener('load', () => {
            setTimeout(() => {
                const loadingScreen = document.getElementById('loading-screen');
                loadingScreen.style.opacity = '0';
                setTimeout(() => {
                    loadingScreen.style.display = 'none';
                }, 500);
            }, 2000);
        });
    }

    // Navigation Effects
    setupNavigation() {
        const navbar = document.getElementById('header');
        const navLinks = document.querySelectorAll('.navbar-nav .nav-link');

        // Navbar scroll effect
        window.addEventListener('scroll', () => {
            if (window.scrollY > 50) {
                navbar.style.background = 'rgba(255, 255, 255, 0.98)';
                navbar.style.boxShadow = '0 4px 6px rgba(0, 0, 0, 0.1)';
            } else {
                navbar.style.background = 'rgba(255, 255, 255, 0.95)';
                navbar.style.boxShadow = 'none';
            }
        });

        // Active link highlighting
        navLinks.forEach(link => {
            link.addEventListener('click', (e) => {
                navLinks.forEach(l => l.classList.remove('active'));
                link.classList.add('active');
            });
        });

        // Smooth scrolling
        document.querySelectorAll('a[href^="#"]').forEach(anchor => {
            anchor.addEventListener('click', function (e) {
                e.preventDefault();
                const target = document.querySelector(this.getAttribute('href'));
                if (target) {
                    target.scrollIntoView({
                        behavior: 'smooth',
                        block: 'start'
                    });
                }
            });
        });

        // Update active nav link on scroll
        this.updateActiveNavLink();
    }

    updateActiveNavLink() {
        const sections = document.querySelectorAll('section[id]');
        const navLinks = document.querySelectorAll('.navbar-nav .nav-link');

        window.addEventListener('scroll', () => {
            let current = '';
            sections.forEach(section => {
                const sectionTop = section.offsetTop;
                const sectionHeight = section.clientHeight;
                if (window.scrollY >= sectionTop - 100) {
                    current = section.getAttribute('id');
                }
            });

            navLinks.forEach(link => {
                link.classList.remove('active');
                if (link.getAttribute('href') === `#${current}`) {
                    link.classList.add('active');
                }
            });
        });
    }

    // AOS Initialization
    setupAOS() {
        if (typeof AOS !== 'undefined') {
            AOS.init({
                duration: 1000,
                once: true,
                offset: 100
            });
        }
    }

    // Product Data
    loadProducts() {
        this.products = [
            {
                id: 1,
                name: 'Wireless Headphones',
                description: 'Premium noise-cancelling wireless headphones with amazing sound quality',
                price: 199.99,
                originalPrice: 299.99,
                category: 'electronics',
                image: 'https://picsum.photos/seed/headphones/400/300.jpg',
                rating: 4.5,
                reviews: 128,
                badge: 'Best Seller'
            },
            {
                id: 2,
                name: 'Smart Watch',
                description: 'Advanced fitness tracking smartwatch with heart rate monitor',
                price: 249.99,
                originalPrice: 349.99,
                category: 'electronics',
                image: 'https://picsum.photos/seed/smartwatch/400/300.jpg',
                rating: 4.7,
                reviews: 89,
                badge: 'New'
            },
            {
                id: 3,
                name: 'Designer T-Shirt',
                description: 'Stylish cotton t-shirt with unique graphic design',
                price: 29.99,
                originalPrice: 49.99,
                category: 'fashion',
                image: 'https://picsum.photos/seed/tshirt/400/300.jpg',
                rating: 4.3,
                reviews: 56,
                badge: 'Sale'
            },
            {
                id: 4,
                name: 'Yoga Mat',
                description: 'Non-slip exercise yoga mat with carrying strap',
                price: 39.99,
                originalPrice: 59.99,
                category: 'sports',
                image: 'https://picsum.photos/seed/yogamat/400/300.jpg',
                rating: 4.6,
                reviews: 203,
                badge: 'Popular'
            },
            {
                id: 5,
                name: 'Coffee Maker',
                description: 'Automatic drip coffee maker with programmable timer',
                price: 89.99,
                originalPrice: 129.99,
                category: 'home',
                image: 'https://picsum.photos/seed/coffee/400/300.jpg',
                rating: 4.4,
                reviews: 167,
                badge: 'Limited'
            },
            {
                id: 6,
                name: 'Skincare Set',
                description: 'Complete facial skincare routine with natural ingredients',
                price: 79.99,
                originalPrice: 119.99,
                category: 'beauty',
                image: 'https://picsum.photos/seed/skincare/400/300.jpg',
                rating: 4.8,
                reviews: 294,
                badge: 'Hot'
            },
            {
                id: 7,
                name: 'Programming Book',
                description: 'Comprehensive guide to modern web development',
                price: 49.99,
                originalPrice: 69.99,
                category: 'books',
                image: 'https://picsum.photos/seed/book/400/300.jpg',
                rating: 4.9,
                reviews: 412,
                badge: 'Best Seller'
            },
            {
                id: 8,
                name: 'Laptop Stand',
                description: 'Adjustable aluminum laptop stand for better ergonomics',
                price: 34.99,
                originalPrice: 54.99,
                category: 'electronics',
                image: 'https://picsum.photos/seed/laptopstand/400/300.jpg',
                rating: 4.2,
                reviews: 78,
                badge: 'Eco'
            },
            {
                id: 9,
                name: 'Running Shoes',
                description: 'Lightweight running shoes with advanced cushioning',
                price: 119.99,
                originalPrice: 169.99,
                category: 'sports',
                image: 'https://picsum.photos/seed/shoes/400/300.jpg',
                rating: 4.7,
                reviews: 156,
                badge: 'New'
            },
            {
                id: 10,
                name: 'Desk Lamp',
                description: 'LED desk lamp with adjustable brightness and color temperature',
                price: 44.99,
                originalPrice: 64.99,
                category: 'home',
                image: 'https://picsum.photos/seed/lamp/400/300.jpg',
                rating: 4.5,
                reviews: 92,
                badge: 'Sale'
            },
            {
                id: 11,
                name: 'Makeup Palette',
                description: 'Professional makeup palette with 48 vibrant colors',
                price: 59.99,
                originalPrice: 89.99,
                category: 'beauty',
                image: 'https://picsum.photos/seed/makeup/400/300.jpg',
                rating: 4.6,
                reviews: 234,
                badge: 'Pro'
            },
            {
                id: 12,
                name: 'Backpack',
                description: 'Stylish and functional backpack with laptop compartment',
                price: 69.99,
                originalPrice: 99.99,
                category: 'fashion',
                image: 'https://picsum.photos/seed/backpack/400/300.jpg',
                rating: 4.4,
                reviews: 178,
                badge: 'Trendy'
            }
        ];

        this.filteredProducts = [...this.products];
        this.displayProducts(this.products);
    }

    // Display Products
    displayProducts(products) {
        const productsGrid = document.getElementById('products-grid');
        productsGrid.innerHTML = '';

        products.forEach((product, index) => {
            const productCard = this.createProductCard(product, index);
            productsGrid.appendChild(productCard);
        });
    }

    createProductCard(product, index) {
        const card = document.createElement('div');
        card.className = 'product-card';
        card.setAttribute('data-aos', 'fade-up');
        card.setAttribute('data-aos-delay', index * 100);
        card.innerHTML = `
            <div class="product-badge">${product.badge}</div>
            <div class="product-image">
                <img src="${product.image}" alt="${product.name}">
                <div class="product-overlay">
                    <div class="product-actions">
                        <button class="action-btn quick-view" data-id="${product.id}">
                            <i class="fas fa-eye"></i>
                        </button>
                        <button class="action-btn add-to-wishlist" data-id="${product.id}">
                            <i class="fas fa-heart"></i>
                        </button>
                    </div>
                </div>
            </div>
            <div class="product-info">
                <h3 class="product-title">${product.name}</h3>
                <p class="product-description">${product.description}</p>
                <div class="product-price">
                    <span class="price-current">$${product.price}</span>
                    <span class="price-original">$${product.originalPrice}</span>
                </div>
                <div class="product-rating">
                    <div class="stars">
                        ${this.generateStars(product.rating)}
                    </div>
                    <span class="rating-count">(${product.reviews})</span>
                </div>
                <button class="add-to-cart" data-id="${product.id}">
                    <i class="fas fa-shopping-cart me-2"></i>Add to Cart
                </button>
            </div>
        `;

        // Add event listeners
        const addToCartBtn = card.querySelector('.add-to-cart');
        const quickViewBtn = card.querySelector('.quick-view');
        const wishlistBtn = card.querySelector('.add-to-wishlist');

        addToCartBtn.addEventListener('click', () => this.addToCart(product.id));
        quickViewBtn.addEventListener('click', () => this.quickView(product.id));
        wishlistBtn.addEventListener('click', () => this.addToWishlist(product.id));

        return card;
    }

    generateStars(rating) {
        const fullStars = Math.floor(rating);
        const halfStar = rating % 1 >= 0.5 ? 1 : 0;
        const emptyStars = 5 - fullStars - halfStar;
        
        let stars = '';
        for (let i = 0; i < fullStars; i++) {
            stars += '<i class="fas fa-star"></i>';
        }
        if (halfStar) {
            stars += '<i class="fas fa-star-half-alt"></i>';
        }
        for (let i = 0; i < emptyStars; i++) {
            stars += '<i class="far fa-star"></i>';
        }
        
        return stars;
    }

    // Shopping Cart
    setupCart() {
        const cartBtn = document.getElementById('cart-btn');
        const closeCartBtn = document.getElementById('close-cart');
        const cartModal = document.getElementById('cart-modal');

        cartBtn.addEventListener('click', () => {
            cartModal.classList.add('active');
            this.updateCartDisplay();
        });

        closeCartBtn.addEventListener('click', () => {
            cartModal.classList.remove('active');
        });

        cartModal.addEventListener('click', (e) => {
            if (e.target === cartModal) {
                cartModal.classList.remove('active');
            }
        });
    }

    addToCart(productId) {
        const product = this.products.find(p => p.id === productId);
        if (!product) return;

        const existingItem = this.cart.find(item => item.id === productId);
        
        if (existingItem) {
            existingItem.quantity++;
        } else {
            this.cart.push({
                ...product,
                quantity: 1
            });
        }

        this.updateCartCount();
        this.saveCartToStorage();
        this.showNotification('Product added to cart!', 'success');
        
        // Animate cart button
        const cartBtn = document.getElementById('cart-btn');
        cartBtn.style.transform = 'scale(1.2)';
        setTimeout(() => {
            cartBtn.style.transform = 'scale(1)';
        }, 200);
    }

    removeFromCart(productId) {
        this.cart = this.cart.filter(item => item.id !== productId);
        this.updateCartCount();
        this.updateCartDisplay();
        this.saveCartToStorage();
        this.showNotification('Product removed from cart', 'info');
    }

    updateQuantity(productId, change) {
        const item = this.cart.find(item => item.id === productId);
        if (item) {
            item.quantity += change;
            if (item.quantity <= 0) {
                this.removeFromCart(productId);
            } else {
                this.updateCartDisplay();
                this.saveCartToStorage();
            }
        }
    }

    updateCartCount() {
        const count = this.cart.reduce((total, item) => total + item.quantity, 0);
        document.getElementById('cart-count').textContent = count;
    }

    updateCartDisplay() {
        const cartItems = document.getElementById('cart-items');
        const cartTotal = document.getElementById('cart-total');

        if (this.cart.length === 0) {
            cartItems.innerHTML = '<p class="text-center">Your cart is empty</p>';
            cartTotal.textContent = '0.00';
            return;
        }

        cartItems.innerHTML = '';
        let total = 0;

        this.cart.forEach(item => {
            const cartItem = document.createElement('div');
            cartItem.className = 'cart-item';
            cartItem.innerHTML = `
                <img src="${item.image}" alt="${item.name}">
                <div class="cart-item-info">
                    <div class="cart-item-title">${item.name}</div>
                    <div class="cart-item-price">$${item.price}</div>
                    <div class="cart-item-quantity">
                        <button class="quantity-btn" onclick="vibrantMart.updateQuantity(${item.id}, -1)">-</button>
                        <span>${item.quantity}</span>
                        <button class="quantity-btn" onclick="vibrantMart.updateQuantity(${item.id}, 1)">+</button>
                    </div>
                </div>
                <button class="quantity-btn" onclick="vibrantMart.removeFromCart(${item.id})">
                    <i class="fas fa-trash"></i>
                </button>
            `;
            cartItems.appendChild(cartItem);

            total += item.price * item.quantity;
        });

        cartTotal.textContent = total.toFixed(2);
    }

    saveCartToStorage() {
        localStorage.setItem('vibrantmart-cart', JSON.stringify(this.cart));
    }

    loadCartFromStorage() {
        const savedCart = localStorage.getItem('vibrantmart-cart');
        if (savedCart) {
            this.cart = JSON.parse(savedCart);
            this.updateCartCount();
        }
    }

    // Search Functionality
    setupSearch() {
        const searchInput = document.querySelector('.search-input');
        const searchBtn = document.querySelector('.search-btn');

        const performSearch = () => {
            const query = searchInput.value.toLowerCase();
            if (query) {
                const filtered = this.products.filter(product => 
                    product.name.toLowerCase().includes(query) ||
                    product.description.toLowerCase().includes(query)
                );
                this.displayProducts(filtered);
            } else {
                this.displayProducts(this.products);
            }
        };

        searchBtn.addEventListener('click', performSearch);
        searchInput.addEventListener('keyup', (e) => {
            if (e.key === 'Enter') {
                performSearch();
            }
        });
    }

    // Filters
    setupFilters() {
        const categoryFilter = document.getElementById('category-filter');
        const sortFilter = document.getElementById('sort-filter');

        categoryFilter.addEventListener('change', () => {
            this.applyFilters();
        });

        sortFilter.addEventListener('change', () => {
            this.applyFilters();
        });
    }

    applyFilters() {
        const category = document.getElementById('category-filter').value;
        const sortBy = document.getElementById('sort-filter').value;

        let filtered = [...this.products];

        // Category filter
        if (category !== 'all') {
            filtered = filtered.filter(product => product.category === category);
        }

        // Sort
        switch (sortBy) {
            case 'price-low':
                filtered.sort((a, b) => a.price - b.price);
                break;
            case 'price-high':
                filtered.sort((a, b) => b.price - a.price);
                break;
            case 'rating':
                filtered.sort((a, b) => b.rating - a.rating);
                break;
            case 'newest':
                filtered.sort((a, b) => b.id - a.id);
                break;
        }

        this.filteredProducts = filtered;
        this.displayProducts(filtered);
    }

    // Modals
    setupModals() {
        this.setupUserModal();
        this.setupProductModal();
        this.setupNewsletter();
    }

    setupUserModal() {
        const userBtn = document.getElementById('user-btn');
        const userModal = document.getElementById('user-modal');
        const closeUserModal = document.getElementById('close-user-modal');

        userBtn.addEventListener('click', () => {
            userModal.classList.add('active');
        });

        closeUserModal.addEventListener('click', () => {
            userModal.classList.remove('active');
        });

        userModal.addEventListener('click', (e) => {
            if (e.target === userModal) {
                userModal.classList.remove('active');
            }
        });
    }

    setupProductModal() {
        const productModal = document.getElementById('product-modal');
        const closeProductModal = document.getElementById('close-product-modal');

        closeProductModal.addEventListener('click', () => {
            productModal.classList.remove('active');
        });

        productModal.addEventListener('click', (e) => {
            if (e.target === productModal) {
                productModal.classList.remove('active');
            }
        });
    }

    quickView(productId) {
        const product = this.products.find(p => p.id === productId);
        if (!product) return;

        const modalBody = document.getElementById('product-modal-body');
        modalBody.innerHTML = `
            <div class="row">
                <div class="col-md-6">
                    <img src="${product.image}" alt="${product.name}" class="img-fluid rounded">
                </div>
                <div class="col-md-6">
                    <h3>${product.name}</h3>
                    <p>${product.description}</p>
                    <div class="product-price mb-3">
                        <span class="price-current">$${product.price}</span>
                        <span class="price-original">$${product.originalPrice}</span>
                    </div>
                    <div class="product-rating mb-3">
                        <div class="stars">
                            ${this.generateStars(product.rating)}
                        </div>
                        <span class="rating-count">(${product.reviews} reviews)</span>
                    </div>
                    <div class="d-flex gap-3 mb-3">
                        <button class="btn btn-primary btn-lg flex-fill" onclick="vibrantMart.addToCart(${product.id})">
                            <i class="fas fa-shopping-cart me-2"></i>Add to Cart
                        </button>
                        <button class="btn btn-outline-danger btn-lg">
                            <i class="fas fa-heart"></i>
                        </button>
                    </div>
                    <div class="product-details">
                        <h5>Product Details</h5>
                        <ul>
                            <li>Premium quality materials</li>
                            <li>1 year warranty included</li>
                            <li>Free shipping on orders over $50</li>
                            <li>30-day return policy</li>
                        </ul>
                    </div>
                </div>
            </div>
        `;

        document.getElementById('product-modal').classList.add('active');
    }

    addToWishlist(productId) {
        const product = this.products.find(p => p.id === productId);
        if (!product) return;

        let wishlist = JSON.parse(localStorage.getItem('vibrantmart-wishlist') || '[]');
        
        if (!wishlist.find(item => item.id === productId)) {
            wishlist.push(product);
            localStorage.setItem('vibrantmart-wishlist', JSON.stringify(wishlist));
            this.showNotification('Added to wishlist!', 'success');
        } else {
            this.showNotification('Already in wishlist', 'info');
        }
    }

    setupNewsletter() {
        const newsletterForm = document.querySelector('.newsletter-form');
        
        newsletterForm.addEventListener('submit', (e) => {
            e.preventDefault();
            const email = e.target.querySelector('input[type="email"]').value;
            
            if (email) {
                this.showNotification('Successfully subscribed to newsletter!', 'success');
                e.target.reset();
            }
        });
    }

    // Event Listeners
    setupEventListeners() {
        // Category cards
        document.querySelectorAll('.category-card').forEach(card => {
            card.addEventListener('click', () => {
                const category = card.getAttribute('data-category');
                document.getElementById('category-filter').value = category;
                this.applyFilters();
                
                // Scroll to products
                document.getElementById('products').scrollIntoView({
                    behavior: 'smooth'
                });
            });
        });

        // Load more button
        document.getElementById('load-more').addEventListener('click', () => {
            this.showNotification('More products coming soon!', 'info');
        });

        // Checkout button
        document.querySelector('.checkout-btn').addEventListener('click', () => {
            if (this.cart.length === 0) {
                this.showNotification('Your cart is empty!', 'error');
                return;
            }
            this.showNotification('Proceeding to checkout...', 'success');
        });
    }

    // Notification System
    showNotification(message, type = 'info') {
        // Remove existing notifications
        const existingNotification = document.querySelector('.notification');
        if (existingNotification) {
            existingNotification.remove();
        }

        const notification = document.createElement('div');
        notification.className = `notification notification-${type}`;
        notification.textContent = message;

        const colors = {
            success: 'linear-gradient(135deg, #11998e 0%, #38ef7d 100%)',
            error: 'linear-gradient(135deg, #eb3349 0%, #f45c43 100%)',
            info: 'linear-gradient(135deg, #4facfe 0%, #00f2fe 100%)'
        };

        notification.style.cssText = `
            position: fixed;
            top: 100px;
            right: 20px;
            padding: 15px 20px;
            border-radius: 10px;
            color: white;
            font-weight: 500;
            z-index: 10000;
            transform: translateX(100%);
            transition: transform 0.3s ease;
            max-width: 300px;
            background: ${colors[type] || colors.info};
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.2);
        `;

        document.body.appendChild(notification);

        setTimeout(() => {
            notification.style.transform = 'translateX(0)';
        }, 100);

        setTimeout(() => {
            notification.style.transform = 'translateX(100%)';
            setTimeout(() => notification.remove(), 300);
        }, 3000);
    }
}

// Initialize VibrantMart when DOM is loaded
document.addEventListener('DOMContentLoaded', () => {
    window.vibrantMart = new VibrantMart();
});

// Add CSS for notification
const notificationStyles = document.createElement('style');
notificationStyles.textContent = `
    .notification {
        font-family: 'Poppins', sans-serif;
    }
`;
document.head.appendChild(notificationStyles);
