<?php 

namespace App\Classes;

class CResult{

    static function success($message="Başarılı", $lang="request.success", $result=[], $code=200){
        $response = [
            "status"    => true,
            "message"   => $message,
            "lang"      => $lang,
            "result"    => $result
        ];
        return response()->json($response, $code);
    }

    static function error($message="Hata", $lang="request.error", $result=[], $code=200){
        $response = [
            "status"    => false,
            "message"   => $message,
            "lang"      => $lang,
            "result"    => $result
        ];
        return response()->json($response, $code);
    }

    static function bad($message="Hatalı istek", $lang="request.bad", $result=[], $code=400){
        $response = [
            "status"    => false,
            "message"   => $message,
            "lang"      => $lang,
            "result"    => $result
        ];
        return response()->json($response, $code);
    }
    
}
