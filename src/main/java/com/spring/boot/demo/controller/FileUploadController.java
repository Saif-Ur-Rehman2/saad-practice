package com.spring.boot.demo.controller;


import com.spring.boot.demo.exception.BadRequestException;
import com.spring.boot.demo.exception.EntityNotFoundException;
import com.spring.boot.demo.exception.InternalServerErrorException;
import com.spring.boot.demo.helper.FileUploadHelper;
import com.spring.boot.demo.response.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileUploadController {

    @Autowired
    private FileUploadHelper fileUploadHelper;


    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file){
        try {
            //validation
            if (file.isEmpty()) {
                throw new BadRequestException("File is Empty.");
            }

            if (!file.getContentType().equals("image/jpeg")) {
                throw new InternalServerErrorException("File cannot be uploaded. Only jpeg files are allowed");
            }

            boolean f=fileUploadHelper.uploadFile(file);
            if(f){
                return ResponseEntity.ok("file uploaded.");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong. Try again.");
    }
}
