package com.example.blogging_platform.controller;


import com.example.blogging_platform.model.Blogging;
import com.example.blogging_platform.repository.BloggingRepository;
import com.example.blogging_platform.repository.BloggingRequestPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


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


    @PutMapping("/{id}")
    public ResponseEntity<?> UpdateBlogging(@PathVariable Integer id, @RequestBody BloggingRequestPayload bloggingRequestPayload) {
        Optional<Blogging> optionalPostBlog = this.bloggingRepository.findById(id);

        if (optionalPostBlog.isEmpty()) {
            return ResponseEntity.notFound().build(); // Retorna 404 se o blog não for encontrado
        }

        Blogging postBlog = optionalPostBlog.get();
        postBlog.setTitle(bloggingRequestPayload.title());
        postBlog.setContent(bloggingRequestPayload.content());
        postBlog.setCategory(bloggingRequestPayload.category());
        postBlog.setTags(String.valueOf(bloggingRequestPayload.tags()));
        postBlog.setUpdatedAt(LocalDateTime.now());

        this.bloggingRepository.save(postBlog);

        return ResponseEntity.ok(postBlog);
    }

    @GetMapping
    public ResponseEntity<?> GetAllBlogging() {
        List<Blogging> bloggings = this.bloggingRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(bloggings);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> DeleteBlogging(@PathVariable Integer id) {
        Optional<Blogging> blogging = this.bloggingRepository.findById(id);
        if (blogging.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        this.bloggingRepository.delete(blogging.get());
        return ResponseEntity.ok().body(blogging);
    }

    @GetMapping(params = "term")
    public ResponseEntity<List<Blogging>> searchPost(@RequestParam(value = "term") String term){
      List<Blogging> posts;
      if (term != null && !term.trim().isEmpty()) {
          posts = this.bloggingRepository.searchByTerm(term);
      }else {
          return ResponseEntity.noContent().build();
      }
      return ResponseEntity.status(HttpStatus.OK).body(posts);
    }
}
