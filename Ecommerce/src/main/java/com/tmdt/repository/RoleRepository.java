package com.tmdt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmdt.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	Optional<Role> findOneByName(String name);
}
