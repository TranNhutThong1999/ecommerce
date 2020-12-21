package com.tmdt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmdt.entity.Evaluated;

public interface EvaluatedRepository extends JpaRepository<Evaluated, Integer>{
	List<Evaluated> findByPost_Id( int id);
}
