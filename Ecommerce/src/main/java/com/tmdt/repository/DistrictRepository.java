package com.tmdt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmdt.entity.District;

public interface DistrictRepository extends JpaRepository<District, Integer> {
	District findOneById(int id);
}