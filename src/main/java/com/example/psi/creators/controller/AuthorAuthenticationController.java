package com.example.psi.creators.controller;

import com.example.psi.creators.security.JwtUtils;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorAuthenticationController {

    private final JwtUtils jwtUtils;


    public AuthorAuthenticationController(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }
}
