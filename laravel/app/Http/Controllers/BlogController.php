<?php

namespace App\Http\Controllers;

use App\Classes\CResult;
use App\Classes\CSession;
use App\Models\Blog;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;
use Illuminate\Support\Str;

class BlogController extends Controller
{
    public function createGet()
    {
        return view('pages.blog.create');
    }

    public function createPost(Request $request)
    {
        $validator = Validator::make($request->all(), [
            'title'     => 'required|regex:/^(.+)$/i',
            'short_text'   => 'required|regex:/^[^\'\"\`]+$/i',
            'long_text'   => 'required|regex:/^[^\'\"\`]+$/i'
        ]);
        if ($validator->fails()) {
            return CResult::bad("Hatalı Form Datası", "request.bad", $validator->errors()->all());
        } else {
            Blog::create([
                'user_id' => CSession::get('user')['id'],
                'title' => $request->title,
                'slug' => Str::slug($request->title, '-'),
                'short_text' => $request->short_text,
                'long_text' => $request->long_text,
                'create_date' => date('Y-m-d H:i:s')
            ]);
            return redirect('/');
        }
    }
}
