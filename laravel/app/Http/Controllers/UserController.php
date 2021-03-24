<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class UserController extends Controller
{
    
    public function loginGet(){
        return view('pages.user.login');
    }

    public function loginPost(){
        return view('pages.user.login');
    }

    public function registerGet(){
        return view('pages.user.register');
    }

    public function registerPost(Request $request){
        dd($request);
        //return view('pages.user.register');
    }

    public function profile(){
        return view('pages.user.profile');
    }

    public function logout(){
        dd("logout");
    }
}
