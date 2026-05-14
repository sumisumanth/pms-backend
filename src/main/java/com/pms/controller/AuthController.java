package com.pms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pms.entity.User;
import com.pms.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    /* REGISTER */

    @PostMapping("/register")
    public User register(@RequestBody User user){
        return authService.register(user);
    }

    /* LOGIN */

    @PostMapping("/login")
    public User login(@RequestBody User loginRequest){
        return authService.login(loginRequest);
    }

}