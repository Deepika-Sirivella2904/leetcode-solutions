# VibrantMart E-commerce Website

A vibrant, full-stack e-commerce website with Node.js backend and modern frontend.

## 🚀 Quick Start

### Prerequisites
- Node.js (v14 or higher)
- MongoDB (installed and running)
- Git

### Installation

1. **Install Dependencies**
   ```bash
   npm install
   ```

2. **Start MongoDB**
   - **Option 1: MongoDB Community Server**
     - Download and install from [mongodb.com](https://www.mongodb.com/try/download/community)
     - Start MongoDB service
     ```bash
     # On Windows (as Administrator)
     net start MongoDB
     
     # Or start manually
     "C:\Program Files\MongoDB\Server\7.0\bin\mongod.exe" --dbpath="C:\data\db"
     ```

   - **Option 2: MongoDB Atlas (Cloud)**
     - Sign up at [cloud.mongodb.com](https://cloud.mongodb.com/)
     - Create a free cluster
     - Get connection string and update `.env` file

   - **Option 3: Docker**
     ```bash
     docker run -d -p 27017:27017 --name mongodb mongo:latest
     ```

3. **Configure Environment**
   - Copy `.env` file and update MongoDB URI:
   ```env
   MONGODB_URI=mongodb://localhost:27017/vibrantmart
   ```

4. **Start the Server**
   ```bash
   # Development mode
   npm run dev
   
   # Production mode
   npm start
   ```

5. **Access the Application**
   - Frontend: http://localhost:5000
   - User Dashboard: http://localhost:5000/dashboard
   - Admin Panel: http://localhost:5000/admin

## 📋 MongoDB Setup Guide

### Windows Installation

1. **Download MongoDB Community Server**
   - Go to [MongoDB Download Center](https://www.mongodb.com/try/download/community)
   - Select Windows version
   - Choose MSI package
   - Download and run installer

2. **Installation Steps**
   - Run the MSI installer as Administrator
   - Choose "Complete" installation
   - Install "MongoDB Compass" (GUI tool)
   - Install as a Windows Service
   - Leave default settings

3. **Verify Installation**
   ```bash
   # Check MongoDB version
   mongod --version
   
   # Check if service is running
   sc query MongoDB
   ```

4. **Start MongoDB Service**
   ```bash
   # Start service
   net start MongoDB
   
   # Stop service
   net stop MongoDB
   ```

### Testing MongoDB Connection

1. **Using MongoDB Compass**
   - Open MongoDB Compass
   - Connection string: `mongodb://localhost:27017`
   - Click "Connect"
   - You should see the connection successful

2. **Using Command Line**
   ```bash
   # Open MongoDB shell
   mongo
   
   # Or with newer versions
   mongosh
   
   # Test commands
   show dbs
   use vibrantmart
   db.test.insertOne({test: "connection"})
   db.test.find()
   ```

3. **Using Node.js Script**
   ```bash
   # Test connection with Node
   node test-mongodb.js
   ```

## 🔧 Environment Variables

Create a `.env` file with the following:

```env
NODE_ENV=development
PORT=5000
MONGODB_URI=mongodb://localhost:27017/vibrantmart
JWT_SECRET=your_super_secret_jwt_key_here_change_in_production
JWT_EXPIRE=7d
```

## 📁 Project Structure

```
2048/
├── server.js              # Main server file
├── package.json           # Dependencies
├── .env                  # Environment variables
├── models/               # Database models
│   ├── User.js
│   ├── Product.js
│   └── Order.js
├── routes/               # API routes
│   ├── auth.js
│   ├── products.js
│   ├── users.js
│   ├── orders.js
│   └── admin.js
├── middleware/           # Custom middleware
├── uploads/             # File uploads
├── index.html           # Main frontend
├── dashboard.html       # User dashboard
├── styles.css          # Styling
└── script.js          # Frontend logic
```

## 🛠 API Endpoints

### Authentication
- `POST /api/auth/register` - Register user
- `POST /api/auth/login` - Login user
- `GET /api/auth/profile` - Get user profile
- `PUT /api/auth/profile` - Update profile

### Products
- `GET /api/products` - Get all products
- `GET /api/products/:id` - Get product by ID
- `POST /api/products` - Create product (admin)
- `PUT /api/products/:id` - Update product (admin)
- `DELETE /api/products/:id` - Delete product (admin)

### Orders
- `GET /api/orders` - Get orders (admin)
- `POST /api/orders` - Create order
- `GET /api/orders/:id` - Get order by ID
- `PUT /api/orders/:id/status` - Update order status

### Users
- `GET /api/users/cart` - Get user cart
- `POST /api/users/cart` - Add to cart
- `PUT /api/users/cart/:productId` - Update cart item
- `DELETE /api/users/cart/:productId` - Remove from cart

### Admin
- `GET /api/admin/dashboard` - Dashboard stats
- `GET /api/admin/products` - Manage products
- `GET /api/admin/users` - Manage users
- `GET /api/admin/analytics` - Get analytics

## 🎨 Features

### Frontend
- Responsive design with vibrant colors
- Product catalog with filtering
- Shopping cart functionality
- User authentication
- Order tracking
- Wishlist management
- User dashboard

### Backend
- RESTful API with Express.js
- MongoDB database with Mongoose
- JWT authentication
- File upload support
- Order processing
- Admin panel
- Analytics dashboard

## 🐛 Troubleshooting

### MongoDB Connection Issues

1. **Check if MongoDB is running**
   ```bash
   # Windows
   sc query MongoDB
   
   # Check process
   tasklist | findstr mongod
   ```

2. **Common Issues**
   - **Port 27017 already in use**: Kill existing MongoDB process
   - **Permission denied**: Run as Administrator
   - **Service not found**: Reinstall MongoDB as service

3. **Reset MongoDB**
   ```bash
   # Stop service
   net stop MongoDB
   
   # Clear data directory (optional)
   rmdir /s "C:\data\db"
   
   # Restart service
   net start MongoDB
   ```

### Server Issues

1. **Port 5000 already in use**
   ```bash
   # Find process using port 5000
   netstat -ano | findstr :5000
   
   # Kill process
   taskkill /PID <PID> /F
   ```

2. **Module not found**
   ```bash
   # Reinstall dependencies
   npm install
   
   # Clear cache
   npm cache clean --force
   ```

## 📞 Support

For issues and questions:
1. Check the troubleshooting section
2. Verify MongoDB is running
3. Check environment variables
4. Review server logs

## 📄 License

MIT License
