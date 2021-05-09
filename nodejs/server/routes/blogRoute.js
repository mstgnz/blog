const express = require('express');
const router = express.Router();
const { blogCreate, blogList, blogDelete, blogUpdate, blogComment } = require('../controllers/blogController');
const accessRouteMiddleware = require('../middlewares/accessRouteMiddleware');

router.post("/create", accessRouteMiddleware, blogCreate);

router.get("/list/:slug?", accessRouteMiddleware, blogList);

router.delete("/delete", accessRouteMiddleware, blogDelete);

router.put("/update", accessRouteMiddleware, blogUpdate);

router.post("/comment", accessRouteMiddleware, blogComment);

module.exports = router;