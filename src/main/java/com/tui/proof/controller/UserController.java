package com.tui.proof.controller;

import com.tui.proof.dto.UserDTO;
import com.tui.proof.model.User;
import com.tui.proof.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("new")
    public ResponseEntity newUser(@RequestBody UserDTO user) {
        Boolean newUser = userService.create(user);
        if(!newUser.equals(true)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error to save user");
        }else{
            return ResponseEntity.ok("The user was create correctly");
        }
    }

    @PostMapping("auth")
    public User login(@RequestParam("user") String username, @RequestParam("password") String pwd) {

        String token = getJWTToken(username);
        Optional<User> user = userService.findByUser(username,pwd);
        if(user.isPresent()){
            user.get().setToken(token);
            return user.get();
        }
        return null;

    }

    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}