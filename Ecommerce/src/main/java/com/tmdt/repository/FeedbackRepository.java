package com.tmdt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmdt.entity.Fee;
import com.tmdt.entity.FeedBack;

public interface FeedbackRepository extends JpaRepository<FeedBack, Integer>{
	Optional<FeedBack> findOneByUser_IdAndPost_Id(int idUser, int idPost);
	List<FeedBack> findByPost_Id(int idPost); 
	
}
