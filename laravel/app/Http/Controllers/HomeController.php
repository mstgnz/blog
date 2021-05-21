<?php

namespace App\Http\Controllers;

use App\Models\Blog;
use Illuminate\Http\Request;

class HomeController extends Controller
{
    public function index(){
        $blogs = Blog::select('*')->with('user')->get();
        return view('pages.home', ['blogs'=>$blogs]);
    }

    public function detail($slug){
        $blog = Blog::select('*')->where([
            'slug' => $slug
        ])->with('user')->first();
        return view('pages.blog.detail', ['blog'=>$blog]);
    }
}
