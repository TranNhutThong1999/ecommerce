package com.tmdt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmdt.entity.Ward;


public interface WardRepository extends JpaRepository<Ward, Integer>{
	Ward findOneById(int id);
}
