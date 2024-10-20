package com.spring.boot.demo;

import com.spring.boot.demo.model.Address;
import com.spring.boot.demo.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SaadspringApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaadspringApplication.class, args);
		Address address = (Address) new User();
		address.PrintData();

	}

}
