package com.example.blogging_platform.repository;

import com.example.blogging_platform.model.Blogging;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BloggingRepository extends JpaRepository<Blogging, Integer> {
}
