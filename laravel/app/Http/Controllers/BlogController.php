<?php

namespace App\Http\Controllers;

use App\Classes\CResult;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;

class BlogController extends Controller
{
    public function createGet(){
        return view('pages.blog.create');
    }

    public function createPost(Request $request){
        $validator = Validator::make($request->all(), [
            'email'         => 'required|regex:/^([\w\.-]+@[\w]+\.[\w]+(\.[\w]+)?)$/i',
            'password'      => 'required|regex:/^[^\'\"<>;,]+$/i'
        ]);
        if($validator->fails()){
            return CResult::bad("Hatalı Form Datası","request.bad",$validator->errors()->all());
        }else{
            dd($request->toArray());
        }
    }
}
