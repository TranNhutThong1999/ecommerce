package com.tmdt.service.imp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tmdt.entity.Image;
import com.tmdt.repository.ImageRepository;
import com.tmdt.service.IImageService;

@Service
public class ImageService implements IImageService {
	@Autowired
	private ImageRepository imageRepository;
	
	@Override
	public String save(MultipartFile file) throws IOException {
		// TODO Auto-generated method stub
		String name = UUID.randomUUID() + file.getOriginalFilename();
		System.out.println(name);
		Path path = Paths.get("src/main/resources/static/images/product/" + name);
		try {
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "/images/product/" + name; 
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		Image i = imageRepository.findById(id).get();
		Path path = Paths.get("src/main/resources/static" + i.getName());
		try {
			Files.delete(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		imageRepository.deleteById(id);
	}
}
