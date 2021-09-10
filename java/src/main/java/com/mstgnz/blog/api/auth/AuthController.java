package com.mstgnz.blog.api.auth;

import com.mstgnz.blog.dto.LoginDto;
import com.mstgnz.blog.dto.UserDto;
import com.mstgnz.blog.services.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @CrossOrigin
    @PostMapping("/api/login")
    ResponseService login(@Valid @RequestBody Credentials credentials) {
        LoginDto loginDto = new LoginDto();
        loginDto.setToken(authService.authenticate(credentials));
        return new ResponseService(200, true, "giriş başarılı", "login.success", loginDto, null);
    }

    @CrossOrigin
    @PostMapping("/api/register")
    public ResponseService createUser(@Valid @RequestBody UserDto userDto) {
        userDto = new UserDto(authService.create(userDto));
        ResponseService response = new ResponseService();
        if (userDto.getId() != 0) {
            LoginDto loginDto = new LoginDto();
            loginDto.setToken(authService.register(userDto));
            return new ResponseService(200, true, "kayıt başarılı", "register.success", loginDto, null);
        }else{
            throw new AuthException();
        }
    }

}
