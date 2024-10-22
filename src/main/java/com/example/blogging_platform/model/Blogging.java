package com.example.blogging_platform.model;


import com.example.blogging_platform.repository.BloggingRequestPayload;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "blogging")
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

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updated_at;

    public Blogging(BloggingRequestPayload bloggingRequestPayload) {
        this.title = bloggingRequestPayload.title();
        this.content = bloggingRequestPayload.content();
        this.category = bloggingRequestPayload.category();
        this.tags = bloggingRequestPayload.tags().toString();
        this.created_at = Timestamp.valueOf(LocalDateTime.now());
        this.updated_at = Timestamp.valueOf(LocalDateTime.now());;
    }
}
