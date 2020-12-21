package com.tmdt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmdt.entity.Fee;

public interface FeeRepository extends JpaRepository<Fee, Integer>{
	Fee findOneById(int id);
}
