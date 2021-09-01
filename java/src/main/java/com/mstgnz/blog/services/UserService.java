package com.mstgnz.blog.services;

import com.mstgnz.blog.dto.UserDto;
import com.mstgnz.blog.entities.User;
import com.mstgnz.blog.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User create(UserDto userDto) {
        Optional<User> checkUser = userRepository.findByEmail(userDto.getEmail());
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setLastname(userDto.getLastname());
        user.setFirstname(userDto.getFirstname());
        user.setCreateDate(new Date());
        String encryptedPassword = this.passwordEncoder.encode(userDto.getPassword());
        user.setPassword(encryptedPassword);
        if(checkUser.isEmpty()){
            return this.userRepository.save(user);
        }else{
            user.setId(checkUser.get().getId());
            return user;
        }
    }

    public Page<User> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public void delete(long userId) {
        userRepository.deleteById(userId);
    }

}