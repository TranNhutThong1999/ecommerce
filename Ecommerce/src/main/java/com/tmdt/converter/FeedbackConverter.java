package com.tmdt.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tmdt.dto.FeedBackDTO;
import com.tmdt.dto.StarDTO;
import com.tmdt.entity.FeedBack;
import com.tmdt.repository.PostRepository;
import com.tmdt.repository.StarRepository;
import com.tmdt.repository.UserRepository;
import com.tmdt.security.CustomUserDetail;

@Component
public class FeedbackConverter implements IConverter<FeedBack, FeedBackDTO>{
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private PostConverter postConverter;
	
	@Autowired
	private UserConverter userConverter;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CustomUserDetail customUserDetal;
	
	@Autowired
	private StarRepository starRepository;
	
	@Override
	public FeedBackDTO toDTO(FeedBack u) {
		// TODO Auto-generated method stub
		FeedBackDTO f =  modelMapper.map(u, FeedBackDTO.class);
		f.setTime(u.getTime().getTime());
		f.setIdPost(u.getPost().getId());
		f.setIdUser(u.getPost().getId());
		f.setStar(modelMapper.map(u.getStar(), StarDTO.class));
		return f;
	}

	@Override
	public FeedBack toEntity(FeedBackDTO v) {
		// TODO Auto-generated method stub
		FeedBack f =  modelMapper.map(v, FeedBack.class);
		f.setStar(starRepository.findOneByValue(v.getStar().getValue()));
		f.setPost(postRepository.findOneById(v.getIdPost()).get());
		f.setUser(userRepository.findOneByUserName(customUserDetal.getPrinciple().getName()).get());
		return f;
	}

}
