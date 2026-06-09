package com.jsp.QuickBite.user_module.controller;

import com.jsp.QuickBite.user_module.dto.LoginRequest;
import com.jsp.QuickBite.user_module.dto.UserRequest;
import com.jsp.QuickBite.user_module.service.UserService;
import com.jsp.QuickBite.user_module.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/QuickBite/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest userRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(userRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody LoginRequest userRequest){
        return ResponseEntity.status(HttpStatus.OK).body(userService.login(userRequest));
    }

    @GetMapping("/profile")
    public ResponseEntity<UserResponse> profile(@RequestParam Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.profile(id));
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestParam Integer id) {
        userService.deleteUser(id);
    }

    @PutMapping("/changepassword")
    public ResponseEntity<UserResponse> changePassword(@RequestParam String email, @RequestParam String oldPassword, @RequestParam String newPassword) {
        UserResponse response = userService.changePassword(email, oldPassword, newPassword);
        return ResponseEntity.ok(response);
    }
}