const mongoose = require('./db');   // Bring in DB connection
const Trip = require('./travlr');   // Bring in Trip schema
const fs = require('fs');
const path = require('path');

// Read seed data from JSON file safely
const tripsFilePath = path.join(__dirname, 'data', 'trips.json');
let trips = [];

try {
    const data = fs.readFileSync(tripsFilePath, 'utf8');
    trips = JSON.parse(data);
    console.log(`Loaded ${trips.length} trips from JSON file.`);
} catch (err) {
    console.error('Error reading trips.json:', err);
    process.exit(1);
}

// Seed function
const seedDB = async () => {
    try {
        console.log('Deleting existing trips...');
        await Trip.deleteMany({});

        console.log('Inserting seed data...');
        await Trip.insertMany(trips);

        console.log('Database seeding complete.');
    } catch (err) {
        console.error('Error seeding the database:', err);
    } finally {
        await mongoose.connection.close();
        console.log('MongoDB connection closed.');
        process.exit(0);
    }
};

// Run the seed function
seedDB();