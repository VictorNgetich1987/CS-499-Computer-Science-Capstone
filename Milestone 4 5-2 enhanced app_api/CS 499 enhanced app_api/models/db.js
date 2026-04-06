const mongoose = require('mongoose');
const readline = require('readline');

const host = process.env.DB_HOST || '127.0.0.1';
const dbName = 'travlr';
const dbURI = `mongodb://${host}/${dbName}`;

// Connect to MongoDB with retry on initial failure
const connect = async () => {
    try {
        await mongoose.connect(dbURI, {
            useNewUrlParser: true,
            useUnifiedTopology: true,
            // optional settings
            serverSelectionTimeoutMS: 5000, // timeout after 5s
        });
        console.log(`Mongoose connected to ${dbURI}`);
    } catch (err) {
        console.error(`Mongoose connection error: ${err.message}`);
        console.log('Retrying connection in 5 seconds...');
        setTimeout(connect, 5000);
    }
};

// Graceful shutdown function
const gracefulShutdown = (msg, callback) => {
    mongoose.connection.close(() => {
        console.log(`Mongoose disconnected through ${msg}`);
        if (callback) callback();
    });
};

// Event listeners for termination and restarts
process.once('SIGUSR2', () => {
    gracefulShutdown('nodemon restart', () => {
        process.kill(process.pid, 'SIGUSR2');
    });
});

process.on('SIGINT', () => {
    gracefulShutdown('app termination', () => process.exit(0));
});

process.on('SIGTERM', () => {
    gracefulShutdown('app shutdown', () => process.exit(0));
});

// Windows-specific handling
if (process.platform === 'win32') {
    const rl = readline.createInterface({
        input: process.stdin,
        output: process.stdout
    });
    rl.on('SIGINT', () => process.emit('SIGINT'));
}

// Make initial connection
connect();

// Import Mongoose schemas
require('./travlr');

module.exports = mongoose;