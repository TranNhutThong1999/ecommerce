package com.tmdt.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.tmdt.dto.PostDTO;

public interface IPostService {
	PostDTO save(PostDTO post, MultipartFile[] files);

	PostDTO findOneById(int id);

	List<PostDTO> findAllRef(int ward, int district, int provincial);

	Page<PostDTO> findAll(Map<String, String> q);

	List<PostDTO> findAll();

	Page<PostDTO> findByUser_Id(Map<String, String> q);

	void deletePostCreated(int idPost);

}
