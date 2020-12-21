package com.tmdt.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tmdt.dto.EvaluatedDTO;
import com.tmdt.dto.StarDTO;
import com.tmdt.entity.Evaluated;
import com.tmdt.repository.PostRepository;
import com.tmdt.repository.StarRepository;
import com.tmdt.repository.UserRepository;
import com.tmdt.security.CustomUserDetail;

@Component
public class EvaluatedConverter implements IConverter<Evaluated, EvaluatedDTO>{
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CustomUserDetail customUserDetal;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private StarRepository starRepository;
	
	@Autowired
	private UserConverter userConverter;
	
	public EvaluatedDTO toDTO(Evaluated u) {
		// TODO Auto-generated method stub
		EvaluatedDTO e = new EvaluatedDTO();
		e.setStar(modelMapper.map(u.getStar(), StarDTO.class));
	//	e.setUser(userConverter.toDTO(u.getUser()));
		e.setPostId(u.getPost().getId());
		return null;
	}

	public Evaluated toEntity(EvaluatedDTO v) {
		// TODO Auto-generated method stub
		Evaluated e = modelMapper.map(v, Evaluated.class);
	//	e.setUser(userRepository.findOneByUserName(customUserDetal.getPrinciple().getName()).get());
		e.setPost(postRepository.findOneById(v.getPostId()).get());
		e.setStar(starRepository.findOneById(v.getStar().getId()));
		return e;
	}

}
