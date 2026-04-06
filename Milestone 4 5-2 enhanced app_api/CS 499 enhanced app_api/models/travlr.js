const mongoose = require('mongoose');

// Define the trip schema with validation and defaults
const tripSchema = new mongoose.Schema({
  code: { 
    type: String, 
    required: [true, 'Trip code is required'], 
    unique: true, 
    trim: true, 
    index: true 
  },
  name: { 
    type: String, 
    required: [true, 'Trip name is required'], 
    trim: true, 
    index: true 
  },
  length: { 
    type: String, 
    required: [true, 'Trip length is required'], 
    trim: true 
  },
  start: { 
    type: Date, 
    required: [true, 'Start date is required'] 
  },
  resort: { 
    type: String, 
    required: [true, 'Resort name is required'], 
    trim: true 
  },
  perPerson: { 
    type: Number, 
    required: [true, 'Price per person is required'], 
    min: [0, 'Price must be positive'] 
  },
  image: { 
    type: String, 
    required: [true, 'Image URL is required'], 
    trim: true 
  },
  description: { 
    type: String, 
    required: [true, 'Description is required'], 
    trim: true 
  }
}, {
  timestamps: true // Adds createdAt and updatedAt fields
});

// Add a virtual field for formatted start date (optional)
tripSchema.virtual('formattedStartDate').get(function() {
  return this.start.toDateString();
});

const Trip = mongoose.model('Trip', tripSchema);

module.exports = Trip;