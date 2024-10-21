package com.spring.boot.demo.controller;


import com.spring.boot.demo.exception.BadRequestException;
import com.spring.boot.demo.exception.EntityNotFoundException;
import com.spring.boot.demo.exception.InternalServerErrorException;
import com.spring.boot.demo.helper.FileUploadHelper;
import com.spring.boot.demo.response.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/file")
public class FileUploadController {

    @Autowired
    private FileUploadHelper fileUploadHelper;


    @PostMapping("/upload")
    public ResponseEntity<Message<String>> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {

       return ResponseEntity.ok(this.fileUploadHelper.uploadFile(file));
    }

    @GetMapping("/get/{name}")
    public ResponseEntity<InputStreamResource> getFile(@PathVariable String name) {
        try {
            InputStream inputStream = this.fileUploadHelper.getFile(name);
            InputStreamResource resource = new InputStreamResource(inputStream);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); // Set the correct media type if known
            headers.setContentDispositionFormData("attachment", name);

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(resource);
        } catch (FileNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
