package com.tmdt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmdt.entity.DepositHistory;

public interface DepositHistoryRepository extends JpaRepository<DepositHistory, Integer>{

}
