package com.tmdt.repository;


import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tmdt.entity.Star;



public interface StarRepository extends JpaRepository<Star, Integer> {
	List<Star> findAll(Sort s);
	Star findOneById(int id);
	Star findOneByValue(int value);
}
