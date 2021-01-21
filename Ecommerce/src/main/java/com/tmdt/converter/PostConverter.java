package com.tmdt.converter;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.tmdt.dto.ContentDTO;
import com.tmdt.dto.PostDTO;
import com.tmdt.entity.Address;
import com.tmdt.entity.Content;
import com.tmdt.entity.Post;
import com.tmdt.entity.Role;
import com.tmdt.repository.ActionRepository;
import com.tmdt.repository.DistrictRepository;
import com.tmdt.repository.FeeRepository;
import com.tmdt.repository.ProvincialRepository;
import com.tmdt.repository.UserRepository;
import com.tmdt.repository.WardRepository;
import com.tmdt.security.CustomUserDetail;
import com.tmdt.security.MyUser;
import com.tmdt.service.IUserService;

@Component
public class PostConverter implements IConverter<Post, PostDTO> {
	@Autowired
	private CustomUserDetail customUserDetal;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AddressConverter addressConverter;
	
	@Autowired
	private ImageConverter imageConverter;
	
	@Autowired
	private FeeConverter feeConverter;
	
	@Autowired
	private UserConverter userConverter;
	
	@Autowired
	private FeeRepository feeRepository;
	
	@Autowired
	private ActionRepository actionRepository;
	
	public PostDTO toDTO(Post u) {
		// TODO Auto-generated method stub
		PostDTO dto = modelMapper.map(u, PostDTO.class);
//		System.out.println("time "+u.getTimeExpire().getTime());
		dto.setTimeExpire(u.getTimeExpire().getTime());
		dto.setAddress(addressConverter.toDTO(u.getAddress()));
		dto.setContent(modelMapper.map(u.getContent(), ContentDTO.class));
		dto.setImages(u.getImages().stream().map(imageConverter::toDTO).collect(Collectors.toList()));
		dto.setFee(feeConverter.toDTO(u.getFee()));
		dto.setUserId(u.getUser().getId());
		List<Integer> l =actionRepository.findByPost_Id(u.getId()).stream().map(e -> e.getUser().getId()).collect(Collectors.toList());
		dto.setIdUserlike(l);
		dto.setTime(u.getTime().getTime());
		return dto;
	}

	public Post toEntity(PostDTO v) {
		// TODO Auto-generated method stub
		Post dto = modelMapper.map(v, Post.class);
		dto.setTimeExpire( new Timestamp(v.getTimeExpire()));
		//dto.setImages(v.getImages().stream().map(imageConverter :: toEntity).collect(Collectors.toList()));
		if(v.getFee() !=null) {
			dto.setFee(feeRepository.findOneById(v.getFee().getId()));
		}
		dto.setContent(modelMapper.map(v.getContent(), Content.class));
		dto.setAddress(addressConverter.toEntity(v.getAddress()));
	//	MyUser u =(MyUser)customUserDetal.loadUserByUsername(customUserDetal.getPrinciple().getName());
//		boolean r= false;
//		for (GrantedAuthority i : u.getAuthorities()) {
//			if(i.getAuthority().equals("ROLE_ADMIN")) {
//				r = true;
//				break;
//			}
//		}
//		if(v.getUserId() == 0 || v.getUserId() != 0 && v.getUserId() == u.getId()) {
			dto.setUser(userRepository.findOneByUserName(customUserDetal.getPrinciple().getName()).get());
//		}else if(v.getUserId() != 0 && r)
//			dto.setUser(userRepository.findOneById(v.getUserId()).get());
		return dto;
	}

}
