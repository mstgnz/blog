package com.mstgnz.blog.api;

import com.mstgnz.blog.anotations.CurrentUser;
import com.mstgnz.blog.dto.UserDto;
import com.mstgnz.blog.entities.User;
import com.mstgnz.blog.services.ResponseService;
import com.mstgnz.blog.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @CrossOrigin
    @GetMapping
    public Page<UserDto> getUsers(Pageable pageable) {
        return userService.getUsers(pageable).map(UserDto::new);
    }

    @CrossOrigin
    @DeleteMapping("/{userId:[0-9]+}")
    @Transactional
    public ResponseService deleteUser(@PathVariable long userId, @CurrentUser User user){
        userService.delete(userId);
        return new ResponseService(200,true,"silindi herhalde","delete",null,null);
    }
}
