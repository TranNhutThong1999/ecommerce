package com.tmdt.service;

import java.util.Map;

import org.springframework.data.domain.Page;

import com.tmdt.dto.FeedBackDTO;

public interface IFeedbackService {
	boolean save(String content, int idPost, int s);
	Page<FeedBackDTO> findAll(Map<String, String> q);
	void delete(int id);
}
