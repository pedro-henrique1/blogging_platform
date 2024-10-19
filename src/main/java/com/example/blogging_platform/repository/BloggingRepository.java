package com.example.blogging_platform.repository;

import com.example.blogging_platform.model.Blogging;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface BloggingRepository extends JpaRepository<Blogging, Integer> {
    @Query("SELECT p FROM Blogging p WHERE p.title LIKE %:term% OR p.content LIKE %:term% OR p.tags LIKE %:term%")
    List<Blogging> searchByTerm(@Param("term") String term);
}

