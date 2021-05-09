const express = require('express');
const router = express.Router();
const authRoute = require("./authRoute");
const userRoute = require("./userRoute");
const blogRoute = require("./blogRoute");

router.use("/auth", authRoute);
router.use("/user", userRoute);
router.use("/blog", blogRoute);

module.exports = router;