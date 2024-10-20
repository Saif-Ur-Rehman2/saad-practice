package com.spring.boot.demo.repository;

import com.spring.boot.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByIdAndStatus(Long id, Boolean status);
}
