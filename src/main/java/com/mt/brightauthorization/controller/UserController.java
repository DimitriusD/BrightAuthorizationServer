package com.mt.brightauthorization.controller;

import com.mt.brightauthorization.dto.UserRequestDTO;
import com.mt.brightauthorization.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<?> registration (@RequestBody UserRequestDTO user){

        return ResponseEntity.ok(userService.save(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody UserRequestDTO updateProfile){
        return ResponseEntity.ok(userService.update(updateProfile));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        userService.delete(id);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
