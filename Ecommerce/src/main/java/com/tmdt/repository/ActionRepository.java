package com.tmdt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tmdt.entity.Action;
import com.tmdt.entity.StatePost;

public interface ActionRepository extends JpaRepository<Action, Integer> {
	Page<Action> findAllByUser_IdAndPost_State(Pageable p, int idUser, StatePost b);
	Optional<Action> findOneByUser_IdAndPost_Id(int idUser, int idPost);
	List<Action> findByUser_Id(int id);
	List<Action> findByPost_Id(int id);
}
