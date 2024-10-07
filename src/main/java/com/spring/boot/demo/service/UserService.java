package com.spring.boot.demo.service;

import com.spring.boot.demo.model.User;
import com.spring.boot.demo.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    //User signing up
    public String addUser(User user) {
        user.setCreatedAt(LocalDate.now());
        user.setStatus(true);
        this.userRepository.save(user);
        return "data saved successfully";
    }



    //Get all users
    public List<User> getAll() {
        List<User> list = this.userRepository.findAll();
        return list;
    }


    //Get a specific user through id
    public ResponseEntity<User> get(Long id) {
        Optional<User> userOpt = this.userRepository.findById(id);
        if (userOpt.isPresent()) {
            return ResponseEntity.ok(userOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    //Set the user status to false (SOFT DELETE)
    public Optional<User> deactivate(Long id) {
        Optional<User> userOpt = this.userRepository.findById(id);
        if(userOpt.isPresent()){
            User user = userOpt.get();
            user.setStatus(false);
            this.userRepository.save(user);
        }else{
            throw new EntityNotFoundException("User not found with id: "+id);
        }
        return userOpt;
    }


    //Update the user info
    public Optional<User> update(User user, Long id) {
        Optional<User> userOpt = this.userRepository.findById(id);
        if(userOpt.isPresent()){
            User u = userOpt.get();
            u.setName(user.getName());
            u.setEmail(user.getEmail());
            u.setPassword(user.getPassword());
            u.setStatus(user.getStatus());
            this.userRepository.save(u);
        }else{
            throw new EntityNotFoundException("User not found with id: "+id);
        }
        return userOpt;
    }
}
