package com.spring.boot.demo.controller;

import com.spring.boot.demo.model.User;
import com.spring.boot.demo.response.Message;
import com.spring.boot.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/signup")
    ResponseEntity<Message<String>> addUser(@RequestBody User user){
        return ResponseEntity.ok(this.userService.addUser(user));
    }

    @GetMapping("/all")
    ResponseEntity<Message<List<User>>> getAll(){
        return ResponseEntity.ok(this.userService.getAll());
    }

    @GetMapping("/user/{id}")
    ResponseEntity<Message<User>> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.userService.get(id));
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<Message<User>> deactivate(@PathVariable("id") Long id){
        return ResponseEntity.ok(this.userService.deactivate(id));
    }

    @PutMapping("/update/{id}")
    Optional<User> update(@RequestBody User user, @PathVariable("id") Long id){
        return this.userService.update(user, id);
    }

}
