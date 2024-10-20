package com.spring.boot.demo.exception;

import com.spring.boot.demo.response.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<Message<Object>> badRequestException(BadRequestException ex){
        Message<Object> message = new Message<>();
        message.setMessage(ex.getMessage());
        message.setStatus(HttpStatus.BAD_REQUEST.name());
        message.setCode(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(message.getCode()).body(message);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    protected ResponseEntity<Message<Object>> internalServerException(InternalServerErrorException ex){
        Message<Object> message = new Message<>();
        message.setMessage(ex.getMessage());
        message.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.name());
        message.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseEntity.status(message.getCode()).body(message);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Message<Object>> entityNotFoundException(EntityNotFoundException ex){
        Message<Object> message = new Message<>();
        message.setMessage(ex.getMessage());
        message.setStatus(HttpStatus.NOT_FOUND.name());
        message.setCode(HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(message.getCode()).body(message);
    }
}
