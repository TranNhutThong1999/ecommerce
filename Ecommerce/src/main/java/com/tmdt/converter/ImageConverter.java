package com.tmdt.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tmdt.dto.ImageDTO;
import com.tmdt.entity.Image;

@Component
public class ImageConverter  implements IConverter<Image, ImageDTO>{
	@Autowired
	private ModelMapper modelMapper;
	
	public ImageDTO toDTO(Image u) {
		// TODO Auto-generated method stub
		ImageDTO i =modelMapper.map(u, ImageDTO.class);
		i.setPostId(u.getId());
		return i;
	}

	public Image toEntity(ImageDTO v) {
		// TODO Auto-generated method stub
		return modelMapper.map(v, Image.class);
	}

}
