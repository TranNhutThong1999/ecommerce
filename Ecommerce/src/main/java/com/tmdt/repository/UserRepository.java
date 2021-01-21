package com.tmdt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tmdt.dto.UserDTO;
import com.tmdt.entity.Post;
import com.tmdt.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findOneByUserName(String userName);

	Optional<User> findOneById(int id);

	Optional<User> findOneByToken(String token);

	List<User> findAll();

	Optional<User> findOneByEmail(String email);
	
}
