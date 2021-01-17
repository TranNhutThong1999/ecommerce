package com.tmdt.validateAPI;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tmdt.dto.PostDTO;
import com.tmdt.entity.Post;
import com.tmdt.repository.PostRepository;
import com.tmdt.repository.UserRepository;

@Component
public class Validate {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	public boolean checkPostUser(PostDTO post, int idPrincical) {
		Optional<Post> p = postRepository.findOneById(post.getUserId());
		if (!p.isPresent()) {
			return false;
		}
		return p.get().getUser().getId() == idPrincical ? true : false;
	}

	public boolean checkIdPostUser(int idP, int idPrincical) {
		Optional<Post> p = postRepository.findOneById(idP);
		if (!p.isPresent()) {
			return false;
		}
		return p.get().getUser().getId() == idPrincical ? true : false;
	}
}
