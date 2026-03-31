// MongoDB Connection Test Script
const mongoose = require('mongoose');

// MongoDB connection URI
const MONGODB_URI = process.env.MONGODB_URI || 'mongodb://localhost:27017/vibrantmart';

async function testMongoDBConnection() {
    console.log('🔍 Testing MongoDB Connection...');
    console.log('📍 Connection URI:', MONGODB_URI);
    
    try {
        // Connect to MongoDB
        await mongoose.connect(MONGODB_URI, {
            useNewUrlParser: true,
            useUnifiedTopology: true,
        });
        
        console.log('✅ MongoDB Connected Successfully!');
        console.log('📊 Database Info:');
        console.log(`   - Host: ${mongoose.connection.host}`);
        console.log(`   - Port: ${mongoose.connection.port}`);
        console.log(`   - Database: ${mongoose.connection.name}`);
        
        // Test database operations
        const db = mongoose.connection.db;
        
        // List collections
        const collections = await db.listCollections().toArray();
        console.log(`📁 Collections found: ${collections.length}`);
        
        if (collections.length > 0) {
            console.log('   Collections:', collections.map(c => c.name).join(', '));
        }
        
        // Test write operation
        const testCollection = db.collection('test');
        const testDoc = {
            name: 'Connection Test',
            timestamp: new Date(),
            message: 'MongoDB is working perfectly!'
        };
        
        const result = await testCollection.insertOne(testDoc);
        console.log('✅ Write test successful - Document ID:', result.insertedId);
        
        // Test read operation
        const foundDoc = await testCollection.findOne({ _id: result.insertedId });
        console.log('✅ Read test successful - Document found:', foundDoc.name);
        
        // Clean up test data
        await testCollection.deleteOne({ _id: result.insertedId });
        console.log('🧹 Test data cleaned up');
        
        console.log('\n🎉 All tests passed! MongoDB is ready for use.');
        
    } catch (error) {
        console.error('❌ MongoDB Connection Failed!');
        console.error('Error Details:', error.message);
        
        // Provide troubleshooting tips
        console.log('\n🔧 Troubleshooting Tips:');
        console.log('1. Make sure MongoDB is installed');
        console.log('2. Check if MongoDB service is running');
        console.log('3. Verify the connection URI is correct');
        console.log('4. Check if port 27017 is accessible');
        console.log('5. Run as Administrator if permission issues');
        
        // Windows specific help
        if (process.platform === 'win32') {
            console.log('\n🪟 Windows Commands:');
            console.log('   # Check MongoDB service status:');
            console.log('   sc query MongoDB');
            console.log('   # Start MongoDB service:');
            console.log('   net start MongoDB');
            console.log('   # Check if MongoDB is running:');
            console.log('   tasklist | findstr mongod');
        }
        
    } finally {
        // Close connection
        await mongoose.connection.close();
        console.log('🔌 Connection closed');
    }
}

// Run the test
testMongoDBConnection().catch(console.error);
