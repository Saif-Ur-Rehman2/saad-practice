package com.spring.boot.demo.service;

import com.spring.boot.demo.exception.EntityNotFoundException;
import com.spring.boot.demo.model.Article;
import com.spring.boot.demo.model.User;
import com.spring.boot.demo.repository.UserRepository;
import com.spring.boot.demo.response.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
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
    public Message<List<User>> getAll(Integer pageNumber, Integer pageSize) {

        Pageable p = PageRequest.of(pageNumber, pageSize);
        Page<User> page = this.userRepository.findAll(p);
        List<User> list = page.getContent();
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
        User user = this.userRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Data not found."));

            user.setStatus(false);
            this.userRepository.save(user);
            Message message = new Message();
            message.setCode(HttpStatus.OK.value());
            message.setStatus(HttpStatus.OK.name());
            message.setMessage("User Deactivated.");
            message.setData("User Deactivated");
            return message;

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
