package com.tmdt.service.imp;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tmdt.converter.UserConverter;
import com.tmdt.dto.UserDTO;
import com.tmdt.entity.DepositHistory;
import com.tmdt.entity.Role;
import com.tmdt.entity.State;
import com.tmdt.entity.User;
import com.tmdt.repository.DepositHistoryRepository;
import com.tmdt.repository.RoleRepository;
import com.tmdt.repository.UserRepository;
import com.tmdt.security.CustomUserDetail;
import com.tmdt.service.IUserService;
import com.tmdt.util.ConvertMoneyToXu;
import com.tmdt.util.EmailService;

@Service
public class UserService implements IUserService {
	@Autowired
	private UserConverter userConverter;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder bCryptPasswordEncode;

	@Autowired
	private ImageService imageService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private CustomUserDetail customUserDetail;

	@Autowired
	private ConvertMoneyToXu convertMoneyToXu;

	@Override
	public Optional<User> findOneByUserName(String userName) {
		// TODO Auto-generated method stub
		return userRepository.findOneByUserName(userName);
	}

	@Override
	public void save(UserDTO user, MultipartFile file) {
		// TODO Auto-generated method stub
		User u = userConverter.toEntity(user);
		if (u.getId() == 0) {
			List<Role> roles = new ArrayList<Role>();
			if (u.getRoles().size() > 0) {
				u.getRoles().stream().forEach(e -> {
					roles.add(roleRepository.findOneByName(e.getName()).get());
				});
			} else {
				roles.add(roleRepository.findOneByName("ROLE_USER").get());
			}
			u.setRoles(roles);
			u.setActive(false);
			u.setNonBlock(true);
			u.generateToken();
			u.setTimeTokenFuture(15);
			emailService.sendSimpleMessage(user.getEmail(), "Mã xác thực của bạn là", u.getToken());
		}
		if (u.getPassword().length() <= 25) {
			u.setPassword(bCryptPasswordEncode.encode(user.getPassword()));
		}
		try {
			if (!file.isEmpty()) {
				System.out.println("image");
				String a = imageService.save(file);
				u.setImage(a);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userRepository.save(u);
	}

	@Override
	public UserDTO findOneById(int id) {
		// TODO Auto-generated method stub
		System.out.println(id);
		return userConverter.toDTO(userRepository.findOneById(id).get());
	}

	@Override
	public UserDTO findOneByToken(String token) {
		// TODO Auto-generated method stub
		User a = userRepository.findOneByToken(token).orElse(null);
		if (a == null || a.isAfterTime()) {
			return null;
		}
		return userConverter.toDTO(a);
	}

	@Override
	public void save(UserDTO user) {
		// TODO Auto-generated method stub
		userRepository.save(userConverter.toEntity(user));
	}

	@Override
	public int verify(String token) {
		// TODO Auto-generated method stub
		User u = userRepository.findOneByToken(token).orElse(null);
		if (u == null || u.isAfterTime())
			return 0;

		u.setActive(true);
		u.setToken("");
		u.setExpire(null);
		userRepository.save(u);
		return u.getId();
	}

	@Override
	public boolean reSendMail(int id) {
		// TODO Auto-generated method stub
		User u = userRepository.findOneById(id).orElse(null);
		if (u == null)
			return false;
		emailService.sendSimpleMessage(u.getEmail(), "Mã xác thực của bạn là", u.getToken());
		return true;
	}

	@Override
	public void setTotalMoney(int money) {
		// TODO Auto-generated method stub
		User u = userRepository.findOneByUserName(customUserDetail.getPrinciple().getName()).get();
		int total = u.getTotalMoney() + convertMoneyToXu.handle(money);
		u.setTotalMoney(total);
		userRepository.save(u);
	}

	@Override
	public boolean sendMailToken(String email, HttpServletRequest r) {
		// TODO Auto-generated method stub
		User u = userRepository.findOneByEmail(email).orElse(null);
		if (u != null) {
			u.generateToken();
			u.setTimeTokenFuture(5);
			emailService.sendSimpleMessage(email, "Quên mật khẩu",
					r.getContextPath() + "/forgot-password?token=" + u.getToken());
			return true;
		}
		return false;
	}

}
