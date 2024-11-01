package com.spring.boot.demo.helper;


import com.spring.boot.demo.response.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Component

public class FileUploadHelper {
    @Value("${server.port}")
    private int serverPort;

    @Value("${project.image}")
    private String path;

    public Message<String> uploadFile(MultipartFile file) throws IOException {
        String name = file.getOriginalFilename();
        String randomId = UUID.randomUUID().toString();
        String completePath = randomId + name.substring(name.lastIndexOf("."));
        String filePath = Paths.get(path, completePath).toString();

        // Ensure the directory exists
        File directory = new File(path);
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                throw new IOException("Failed to create directory: " + path);
            }
        }

        // Save the file
        Files.copy(file.getInputStream(), Paths.get(filePath));

        // Build response DTO
        Message<String> message = new Message<>();
        message.setCode(HttpStatus.OK.value());
        message.setStatus(HttpStatus.OK.name());
        message.setMessage("File uploaded successfully");
        message.setData("http://localhost:" + serverPort + "/file/get/" + completePath);
        return message;
    }

    public InputStream getFile(String fileName) throws FileNotFoundException {
        String fullPath = path + "/" + fileName; // Ensure 'path' is correctly set
        File file = new File(fullPath);

        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + fileName);
        }

        return new FileInputStream(file);
    }

    public String getFilePath(String fileName) {
        return Paths.get(path, fileName).toString();
    }


}