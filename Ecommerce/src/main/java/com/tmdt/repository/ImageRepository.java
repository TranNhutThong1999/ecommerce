package com.tmdt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmdt.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Integer>{

}
