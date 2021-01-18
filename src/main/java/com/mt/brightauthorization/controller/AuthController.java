package com.mt.brightauthorization.controller;

import com.mt.brightauthorization.dto.JwtDataResponse;
import com.mt.brightauthorization.dto.JwtResponse;
import com.mt.brightauthorization.dto.UserRequestDTO;
import com.mt.brightauthorization.service.token.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
public class AuthController {

    private TokenProvider tokenProvider;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken (@RequestBody UserRequestDTO user) throws Exception {
        return ResponseEntity.ok(tokenProvider.generateToken(user));
    }

    @GetMapping("/validate/{jwt}")
    public JwtDataResponse validateToken (@PathVariable("jwt") String jwtAccessToken) {
        return tokenProvider.validateToken(jwtAccessToken);
    }

    @Autowired
    public void setTokenProvider(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }
}
