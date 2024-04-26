package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, String> {
    Image findByName(String name);
} 
