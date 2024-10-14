package com.example.blogging_platform.controller;


import com.example.blogging_platform.model.Blogging;
import com.example.blogging_platform.repository.BloggingRepository;
import com.example.blogging_platform.repository.BloggingRequestPayload;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/posts")
public class BloggingController {

    @Autowired
    BloggingRepository bloggingRepository;


    @PostMapping
    public ResponseEntity<?> CreatePostBlogging(@RequestBody BloggingRequestPayload bloggingRequestPayload) {
        Blogging blogging = new Blogging(bloggingRequestPayload);
        this.bloggingRepository.save(blogging);
        return ResponseEntity.status(HttpStatus.CREATED).body(blogging);
    }


    @PutMapping("${id}")
    public ResponseEntity<?> UpdateBlogging(@RequestBody BloggingRequestPayload bloggingRequestPayload) {
        

        return null;
    }


}
