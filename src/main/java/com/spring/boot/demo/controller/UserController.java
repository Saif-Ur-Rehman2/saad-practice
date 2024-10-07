package com.spring.boot.demo.controller;

import com.spring.boot.demo.model.User;
import com.spring.boot.demo.service.UserService;
import org.apache.coyote.Response;
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
    ResponseEntity<String> addUser(@RequestBody User user){
        String response = this.userService.addUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/all")
    ResponseEntity<List<User>> getAll(){
        List<User> list = this.userService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/user/{id}")
    ResponseEntity<User> get(@PathVariable("id") Long id) {
        return this.userService.get(id);
    }

    @DeleteMapping("/delete/{id}")
    Optional<User> deactivate(@PathVariable("id") Long id){
        return this.userService.deactivate(id);
    }

    @PutMapping("/update/{id}")
    Optional<User> update(@RequestBody User user, @PathVariable("id") Long id){
        return this.userService.update(user, id);
    }

}
