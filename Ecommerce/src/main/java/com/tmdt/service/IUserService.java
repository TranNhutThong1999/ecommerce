package com.tmdt.service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.tmdt.dto.UserDTO;
import com.tmdt.entity.User;

public interface IUserService {
	Optional<User> findOneByUserName(String userName);
	void save(UserDTO user,MultipartFile file);
	UserDTO findOneById(int id);
	UserDTO findOneByToken(String token);
	void save(UserDTO user);
	int verify(String token);
	boolean reSendMail(int id);
	void setTotalMoney(int money);
	boolean sendMailToken(String email, HttpServletRequest r);
}
