<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Blog extends Model
{
    use HasFactory;

    public $timestamps = false;

    protected $guarded = ['id'];

    public function comments(){
        return $this->hasMany('App\Models\Comment','blog_id');
    }

    public function user(){
        return $this->hasOne('App\Models\User','id','user_id')->select('id','firstname','lastname');
    }
}
