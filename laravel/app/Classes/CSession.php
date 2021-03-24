<?php 

namespace App\Classes;

use Illuminate\Support\Facades\Session;

class CSession{

    public static function get($key){
        return Session::get($key);
    }

    public static function exists($key){
        return Session::exists($key);
    }

    public static function put($key, $value){
        Session::put($key, $value);
        Session::save();
    }

    public static function remove($key){
        Session::remove($key);
        Session::save();
    }

}
