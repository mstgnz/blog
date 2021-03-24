<?php

namespace App\Http\Middleware;

use Closure;
use App\Classes\CSession;
use Illuminate\Support\Facades\Session;

class CheckSession
{
    /**
     * Handle an incoming request.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \Closure  $next
     * @return mixed
     */

    public function handle($request, Closure $next)
    {
        $user = CSession::get("user");
        if(!$user){
            return redirect('/');
        }
        return $next($request);
    }

}
