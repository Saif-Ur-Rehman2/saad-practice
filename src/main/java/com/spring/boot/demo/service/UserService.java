package com.spring.boot.demo.service;

import com.spring.boot.demo.model.User;
import com.spring.boot.demo.repository.UserRepository;
import com.spring.boot.demo.response.Message;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public Message<String> addUser(User user) {
        user.setCreatedAt(LocalDate.now());
        user.setStatus(true);
        this.userRepository.save(user);
        Message response = new Message();
        response.setCode(HttpStatus.OK.value());
        response.setStatus(HttpStatus.OK.name());
        response.setMessage("User added successfully");
        response.setData("User added successfully");
        return response;
    }



    //Get all users
    public Message<List<User>> getAll() {
        List<User> list = this.userRepository.findAll();
        if(!list.isEmpty()){
            Message response = new Message();
            response.setCode(HttpStatus.OK.value());
            response.setStatus(HttpStatus.OK.name());
            response.setMessage("User added successfully");
            response.setData(list);
            return response;
        }
        throw new EntityNotFoundException("Data not found");

    }


    //Get a specific user through id
    public Message<User> get(Long id) {
        Optional<User> userOpt = this.userRepository.findById(id);
        if (userOpt.isPresent()){
            Message message = new Message();
            message.setCode(HttpStatus.OK.value());
            message.setStatus(HttpStatus.OK.name());
            message.setMessage("User Found");
            message.setData(userOpt);
            return message;
        } else {
            throw new EntityNotFoundException("not found");
        }
    }


    //Set the user status to false (SOFT DELETE)
    public Message<User> deactivate(Long id) {
        Optional<User> userOpt = this.userRepository.findById(id);

        if(userOpt.isPresent()){
            Message message = new Message();
            message.setCode(HttpStatus.OK.value());
            message.setStatus(HttpStatus.OK.name());
            message.setMessage("User Deactivated.");
            message.setData("User Deactivated");
            return message;
        }else{
            throw new EntityNotFoundException("User not found.");
        }
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

    public User findByIdAndStatus(Long id, Boolean status){
        return this.userRepository.findByIdAndStatus(id, status).orElse(null);
    }
}
