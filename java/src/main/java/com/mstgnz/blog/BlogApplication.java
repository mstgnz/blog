package com.mstgnz.blog;

import com.mstgnz.blog.api.auth.AuthService;
import com.mstgnz.blog.dto.UserDto;
import com.mstgnz.blog.entities.User;
import com.mstgnz.blog.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

@SpringBootApplication
@EnableJpaRepositories
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class BlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

    @Bean
    CommandLineRunner createInitialAdmin(AuthService authService, UserRepository userRepository) {
        return args -> {
            Optional<User> checkUser = userRepository.findByEmail("mesutgenez@hotmail.com");
            if (checkUser.isEmpty()) {
                UserDto userDto = new UserDto();
                userDto.setEmail("mesutgenez@hotmail.com");
                userDto.setPassword("asdf1234");
                userDto.setFirstname("Mesut");
                userDto.setLastname("GENEZ");
                authService.create(userDto);
            }
        };
    }

}
