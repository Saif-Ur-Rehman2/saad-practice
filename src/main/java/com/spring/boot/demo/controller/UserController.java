package com.spring.boot.demo.controller;

import com.spring.boot.demo.model.User;
import com.spring.boot.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/signup")
    ResponseEntity<String> addUser(@RequestBody User user){
        String response = this.userService.addUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
