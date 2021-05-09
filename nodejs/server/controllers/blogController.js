const Blog = require("../models/Blog");
const Comment = require("../models/Comment");
const User = require("../models/User");
const CustomError = require('../helpers/CustomError');
const Response = require('../helpers/Response');
const asyncWrapper = require('express-async-handler');

// Blog Create
const blogCreate = asyncWrapper(async (req, res, next) => {
    const blog = await Blog.create({
        user: req.user.id,
        title : req.body.title,
        content : req.body.content
    });

    if(blog){
        return new Response(res).success("Blog kaydedildi", blog);
    }else{
        return new Response(res).error("Blog Kaydedilemedi");
    }

});

// Blog List
const blogList = asyncWrapper(async (req, res, next) => {

    let blog;

    if(req.params.slug){
        blog = await User.findOne({slug:req.params.slug}).populate('user');
    }else{
        blog = await Blog.find({}).populate('user');
    }

    return new Response(res).success("Blog Listesi", blog);
});

// Blog Delete
const blogDelete = asyncWrapper(async (req, res, next) => {

    const blog = await Blog.findByIdAndDelete({
        user : req.user.id,
        _id : req.body.blogID
    });

    if(blog){
        return new Response(res).success("Blog Silindi");
    }else{
        return new Response(res).error("Blog Bulunamadı");
    }
    
});

// Blog Update
const blogUpdate = asyncWrapper(async (req, res, next) => {
    res.status(200).json({
        success: true,
        message: "blog update"
    });
});

// Yorum
const blogComment = asyncWrapper(async (req, res, next) => {
    const comment = await Comment.create({
        user: req.user.id,
        blog : req.body.blogID,
        content : req.body.content
    });

    if(comment){
        return new Response(res).success("Yorum kaydedildi", comment);
    }else{
        return new Response(res).error("Yorum Kaydedilemedi");
    }

})

module.exports = { blogCreate, blogList, blogDelete, blogUpdate, blogComment }