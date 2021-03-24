<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Comment extends Model
{
    use HasFactory;

    public $timestamps = false;

    protected $guarded = ['id'];

    public function blog(){
        return $this->hasOne('App\Models\Blog','blog_id');
    }
}
