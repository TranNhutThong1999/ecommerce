package com.tmdt.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.tmdt.dto.PostDTO;
import com.tmdt.dto.State;
import com.tmdt.dto.UserDTO;
import com.tmdt.entity.StatePost;

public interface IPostService {
	PostDTO save(PostDTO post, MultipartFile[] files);

	PostDTO update(PostDTO post, MultipartFile[] files);
	
	PostDTO findOneById(int id);

	List<PostDTO> findAllRef(int district, int provincial);

	Page<PostDTO> findAll(Map<String, String> q);

	List<PostDTO> findAll();

	Page<PostDTO> findByUser_Id(Map<String, String> q);

	void deletePostCreated(int idPost);
	
	void save(PostDTO p);
	
	void apporve(int id);
	
	void saveView(int post_id);
	
	Page<PostDTO> findByState(Map<String, String> q, StatePost s);
	
	List<PostDTO> findAllSortByRank();
}
