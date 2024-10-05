package com.spring.boot.demo.service;

import com.spring.boot.demo.model.User;
import com.spring.boot.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    public String addUser(User user) {
        user.setCreatedAt(LocalDate.now());
        this.userRepository.save(user);
        return "data saved successfully";
    }


}
