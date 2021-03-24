<?php

use App\Http\Controllers\BlogController;
use App\Http\Controllers\HomeController;
use App\Http\Controllers\UserController;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::group(['middleware'=>['CheckSession']], function(){
    Route::group(['prefix'=>'user'], function(){
        Route::get('/logout', [UserController::class, 'logout']);
        Route::get('/profile', [UserController::class, 'profile']);
    });
    Route::group(['prefix'=>'blog'], function(){
        Route::get('/create', [BlogController::class, 'createGet']);
        Route::post('/create', [BlogController::class, 'createPost']);
    });
});
Route::get('/login', [UserController::class, 'loginGet']);
Route::post('/login', [UserController::class, 'loginPost']);
Route::get('/register', [UserController::class, 'registerGet']);
Route::post('/register', [UserController::class, 'registerPost']);

Route::get('/', [HomeController::class, 'index']);
Route::get('/{slug}', [HomeController::class, 'detail'])->where('slug','.*');
