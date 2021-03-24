<?php

namespace App\Http\Controllers;

use App\Classes\CSession;
use Illuminate\Foundation\Auth\Access\AuthorizesRequests;
use Illuminate\Foundation\Bus\DispatchesJobs;
use Illuminate\Foundation\Validation\ValidatesRequests;
use Illuminate\Routing\Controller as BaseController;
use Illuminate\Support\Facades\Session;
use Illuminate\Support\Facades\View;

class Controller extends BaseController
{
    use AuthorizesRequests, DispatchesJobs, ValidatesRequests;

    protected $user;

    public function __construct()
    {

        Session::start();

        $this->user = CSession::get('user');

        $this->set('user', $this->user);

    }

    // Set View Args
    public function set($key=null, $value=null){
        View::share($key, $value);
    }

}
