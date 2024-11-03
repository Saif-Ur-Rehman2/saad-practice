package com.spring.boot.demo.controller;
import com.spring.boot.demo.helper.FileUploadHelper;
import com.spring.boot.demo.response.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

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
    public ResponseEntity<byte[]> getFile(@PathVariable("name") String name) throws IOException {
        // Get the full path to the file
        String fullPath = this.fileUploadHelper.getFilePath(name); // You may need to add a method to get the full path

        File file = new File(fullPath);
        if (!file.exists()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        // Read file content into a byte array
        byte[] content = Files.readAllBytes(file.toPath());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=" + file.getName());

        String contentType = Files.probeContentType(file.toPath());
        headers.add("Content-Type", contentType != null ? contentType : "application/octet-stream");

        return ResponseEntity.ok()
                .headers(headers)
                .body(content);
    }
}

