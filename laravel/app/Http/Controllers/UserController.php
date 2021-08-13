<?php

namespace App\Http\Controllers;

use App\Classes\CResult;
use App\Classes\CSession;
use App\Jobs\JLog;
use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Facades\Validator;

class UserController extends Controller
{

    public function loginGet()
    {
        if (CSession::get("user")) return redirect("/");
        return view('pages.user.login');
    }

    public function loginPost(Request $request)
    {
        $validator = Validator::make($request->all(), [
            'email'         => 'required|regex:/^([\w\.-]+@[\w]+\.[\w]+(\.[\w]+)?)$/i',
            'password'      => 'required|regex:/^[^\'\"<>;,]+$/i'
        ]);
        if ($validator->fails()) {
            return CResult::bad("Hatalı Form Datası", "request.bad", $validator->errors()->all());
        } else {
            $user = $this->checkMail($request->email);
            if ($user) {
                if (Hash::check($request->password, $user->password)) {
                    JLog::dispatch($user);
                    $this->setSession($user->toArray());
                    return redirect("/");
                } else {
                    dd("parola hatalı");
                }
            } else {
                dd("user bulunamadı");
            }
        }
    }

    public function registerGet()
    {
        if (CSession::get("user")) return redirect("/");
        return view('pages.user.register');
    }

    public function registerPost(Request $request)
    {
        $validator = Validator::make($request->all(), [
            'firstname'     => 'required|max:24|regex:/^[a-zA-ZöçşüğıÖÇŞÜĞİ\s]+$/i',
            'lastname'      => 'required|max:24|different:firstname|regex:/^[a-zA-ZöçşüğıÖÇŞÜĞİ\s]+$/i',
            'email'         => 'required|regex:/^([\w\.-]+@[\w]+\.[\w]+(\.[\w]+)?)$/i',
            'password'      => 'required|regex:/^[^\'\"<>;,]+$/i'
        ]);
        if ($validator->fails()) {
            return CResult::bad("Hatalı Form Datası", "request.bad", $validator->errors()->all());
        } else {
            if ($this->checkMail($request->email)) {
                dd("Bu mail kayıtlı");
            }
            $data = [
                "firstname" => $request->firstname,
                "lastname" => $request->lastname,
                "email" => $request->email,
                "password" => Hash::make($request->password),
                "create_date" => date("Y-m-d H:i:s")
            ];
            User::create($data);
            $this->setSession($data);
            return redirect("/");
        }
    }

    public function profile()
    {
        return view('pages.user.profile');
    }

    public function logout()
    {
        CSession::remove("user");
        return redirect("/");
    }

    private function checkMail($email)
    {
        $user = User::select('*')->where('email', '=', $email)->first();
        if ($user) return $user;
        return false;
    }
}
