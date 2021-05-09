const mongoose = require('mongoose');
const Schema = mongoose.Schema;
const slug = require('mongoose-slug-updater');
mongoose.plugin(slug);

const BlogSchema = new Schema({
    user        : { type:Schema.ObjectId, ref:"User", required:[true ,"Please provide a user"] },
    title       : { type:String, required:[true ,"Please provide a blog title"] },
    slug        : { type:String, unique:true, slug:"title" },
    content     : { type:String, required:[true ,"Please provide a blog content"] },
    image       : { type:String },
    createdAt   : { type:Date, default:Date.now }
});

module.exports = mongoose.model("Blog", BlogSchema);