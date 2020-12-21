package com.tmdt.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tmdt.dto.FeedBackDTO;
import com.tmdt.entity.FeedBack;
import com.tmdt.repository.PostRepository;
import com.tmdt.repository.UserRepository;
import com.tmdt.security.CustomUserDetail;

@Component
public class FeedbackConverter implements IConverter<FeedBack, FeedBackDTO>{
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CustomUserDetail customUserDetal;
	
	@Override
	public FeedBackDTO toDTO(FeedBack u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FeedBack toEntity(FeedBackDTO v) {
		// TODO Auto-generated method stub
		FeedBack f =  modelMapper.map(v, FeedBack.class);
		f.setPost(postRepository.findOneById(v.getPost().getId()).get());
		f.setUser(userRepository.findOneByUserName(customUserDetal.getPrinciple().getName()).get());
		return f;
	}

}
