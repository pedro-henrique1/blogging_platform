package com.example.blogging_platform.model;


import com.example.blogging_platform.repository.BloggingRequestPayload;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Blogging")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Blogging {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "tags", nullable = false)
    private String tags;

    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updatedAt", nullable = false)
    private LocalDateTime updatedAt;

    public Blogging(BloggingRequestPayload bloggingRequestPayload) {
        this.title = bloggingRequestPayload.title();
        this.content = bloggingRequestPayload.content();
        this.category = bloggingRequestPayload.category();
        this.tags = bloggingRequestPayload.tags().toString();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
