const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const CommentSchema = new Schema({
    user        : { type:Schema.ObjectId, ref:"User", required:[true ,"Please provide a user"] },
    blog        : { type:Schema.ObjectId, ref:"Blog", required:[true ,"Please provide a blog"] },
    content     : { type:String, required:[true ,"Please provide a blog content"] },
    createdAt   : { type:Date, default:Date.now }
});

module.exports = mongoose.model("Comment", CommentSchema);