package com.tmdt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmdt.entity.Provincial;

public interface ProvincialRepository extends JpaRepository<Provincial, Integer>{
	Provincial findOneById(int id);
}
