package com.tmdt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmdt.entity.Fee;
import com.tmdt.entity.FeedBack;

public interface FeedbackRepository extends JpaRepository<FeedBack, Integer>{
	
}
