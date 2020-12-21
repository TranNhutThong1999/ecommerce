package com.tmdt.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface IImageService {
		String save(MultipartFile file) throws IOException;
}