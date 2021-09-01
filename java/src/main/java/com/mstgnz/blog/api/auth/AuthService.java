package com.mstgnz.blog.api.auth;

import com.mstgnz.blog.entities.User;
import com.mstgnz.blog.repositories.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String authenticate(Credentials credentials) {
        Optional<User> user = userRepository.findByEmail(credentials.getEmail());
        if (user.isEmpty()) {
            throw new AuthException();
        }
        boolean matches = passwordEncoder.matches(credentials.getPassword(), user.get().getPassword());
        if (!matches) {
            throw new AuthException();
        }
        Map<String,Object> claims = new HashMap<>();
        claims.put("email", user.get().getEmail());
        claims.put("userId", user.get().getId());
        return Jwts.builder()
                .setClaims(claims)
                .setHeaderParam("typ","JWT")
                .signWith(SignatureAlgorithm.HS512, "Hak.Geldi.Batil.Zail.Oldu!")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 12*60*60*1000))
                .compact();
    }

    @Transactional
    public UserDetails getUserDetails(String token) {
        JwtParser parser = Jwts.parser().setSigningKey("Hak.Geldi.Batil.Zail.Oldu!");
        try{
            parser.parse(token);
            Claims claims = parser.parseClaimsJws(token).getBody();
            long userId = ((Number) claims.get("userId")).longValue();
            User user = new User();
            user.setId(userId);
            user.setEmail((String) claims.get("email"));
            return user;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}