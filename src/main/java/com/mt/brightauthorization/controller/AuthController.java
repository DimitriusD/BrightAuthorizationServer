package com.mt.brightauthorization.controller;

import com.mt.brightauthorization.dto.UserDataDTO;
import com.mt.brightauthorization.service.token.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthController {

    private TokenProvider tokenProvider;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken (@RequestBody UserDataDTO user){
        return ResponseEntity.ok(tokenProvider.generateToken(user.getPhone()));
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registration (@RequestBody UserDataDTO user){
        return null;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody UserDataDTO user){

        return null;
    }

    @Autowired
    public void setTokenProvider(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }
}
