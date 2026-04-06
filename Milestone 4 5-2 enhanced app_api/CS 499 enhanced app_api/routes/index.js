const express = require("express");
const router = express.Router();

// Import controllers
const { tripsList, tripsFindByCode } = require("../controllers/trips");

// Routes for trips
// GET /trips - List all trips
router.get("/trips", tripsList);

// GET /trips/:tripCode - Get trip details by trip code
router.get("/trips/:tripCode", tripsFindByCode);

// Export the router
module.exports = router;