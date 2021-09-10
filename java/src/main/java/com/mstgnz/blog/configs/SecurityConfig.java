package com.mstgnz.blog.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // username yerine email kullanılacak
        http.formLogin().usernameParameter("email");
        // csrf token disabled
        http.csrf().disable();
        // www-authenticate in response header for popup disabled
        http.exceptionHandling().authenticationEntryPoint((request, response, e) -> response.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase()));
        // authenticated
        http
            .authorizeRequests()
            .antMatchers(HttpMethod.GET, "/api/users").authenticated()
            .antMatchers(HttpMethod.DELETE, "/api/users/{[0-9]+}").authenticated()
            .antMatchers(HttpMethod.POST, "/api/blogs").authenticated()
            .antMatchers(HttpMethod.DELETE, "/api/blogs/{blogId:[0-9]+}").authenticated()
            .antMatchers(HttpMethod.POST, "/api/blogs/{blogId:[0-9]+}/comment").authenticated()
            .antMatchers(HttpMethod.DELETE, "/api/blogs/{blogId:[0-9]+}/comment/{commentId:[0-9]+}").authenticated()
            .and()
            .authorizeRequests().anyRequest().permitAll();
        // Session disable
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // All request use tokenFilter
        http.addFilterBefore(tokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    TokenFilter tokenFilter() {
        return new TokenFilter();
    }
}