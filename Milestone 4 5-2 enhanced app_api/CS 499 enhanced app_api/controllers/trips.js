const mongoose = require('mongoose');
const Trip = require('../models/travlr'); // Mongoose model

// GET: /trips - lists all trips
const tripsList = async (req, res) => {
    try {
        const trips = await Trip.find({}).exec();

        if (!trips.length) {
            return res.status(404).json({
                success: false,
                message: "No trips found",
                data: []
            });
        }

        return res.status(200).json({
            success: true,
            message: "Trips retrieved successfully",
            data: trips
        });
    } catch (error) {
        return res.status(500).json({
            success: false,
            message: "Server error",
            error: error.message
        });
    }
};

// GET: /trips/:tripCode - get trip by code
const tripsFindByCode = async (req, res) => {
    try {
        const trip = await Trip.findOne({ code: req.params.tripCode }).exec();

        if (!trip) {
            return res.status(404).json({
                success: false,
                message: `Trip with code '${req.params.tripCode}' not found`
            });
        }

        return res.status(200).json({
            success: true,
            message: "Trip retrieved successfully",
            data: trip
        });
    } catch (error) {
        return res.status(500).json({
            success: false,
            message: "Server error",
            error: error.message
        });
    }
};

module.exports = {
    tripsList,
    tripsFindByCode
};