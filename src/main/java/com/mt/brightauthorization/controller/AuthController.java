package com.mt.brightauthorization.controller;

import com.mt.brightauthorization.dto.UserDataDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthController {

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate (@RequestBody UserDataDTO user){
        return null;
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registration (@RequestBody UserDataDTO user){
        return null;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody UserDataDTO user){

        return null;
    }
}
