package com.tmdt.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.tmdt.dto.State;
import com.tmdt.dto.UserDTO;
import com.tmdt.entity.User;

public interface IUserService {
	Optional<User> findOneByUserName(String userName);

	void save(UserDTO user, MultipartFile file);

	UserDTO findOneById(int id);

	UserDTO findOneByToken(String token);

	boolean save(String token, String pw);
	
	void delete(int id);

	int verify(String token);

	boolean reSendMail(int id);

	void setTotalMoney(int money);

	boolean sendMailToken(String email, HttpServletRequest r);
	
	Page<UserDTO> findAll(Map<String, String> q);
	
	
}
