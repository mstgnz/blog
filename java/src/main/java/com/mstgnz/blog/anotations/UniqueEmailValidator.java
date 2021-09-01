package com.mstgnz.blog.anotations;

import com.mstgnz.blog.entities.User;
import com.mstgnz.blog.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

@RequiredArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final UserRepository userRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        Optional<User> checkUser = userRepository.findByEmail(email);
        return checkUser.isEmpty();
    }
}