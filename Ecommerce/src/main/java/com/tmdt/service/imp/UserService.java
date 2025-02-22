package com.tmdt.service.imp;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

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
			if (!file.getOriginalFilename().equals("")) {
				System.out.println("image");
				String a = null;
				try {
					a = imageService.save(file);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				u.setImage(a);
			}
			u.setPassword(bCryptPasswordEncode.encode(u.getPassword()));
			u.setRoles(roles);
			u.setActive(false);
			u.setNonBlock(true);
			u.generateToken();
			u.setTimeTokenFuture(15);
			emailService.sendSimpleMessage(user.getEmail(), "Mã xác thực của bạn là", u.getToken());
			userRepository.save(u);
			return;
		}
		if (u.getPassword().length() <= 25) {
			u.setPassword(bCryptPasswordEncode.encode(u.getPassword()));
		}
		try {
			if (!file.getOriginalFilename().equals("")) {
				System.out.println("image");
				String a = imageService.save(file);
				u.setImage(a);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		User old  = userRepository.findOneById(u.getId()).get();
		u.setPosts(old.getPosts());
		u.setRoles(old.getRoles());
		u.setActive(old.isActive());
		u.setNonBlock(old.isNonBlock());
		u.setDepositHistories(old.getDepositHistories());
		u.setAction(old.getAction());
		u.setFeedback(old.getFeedback());
		u.setTotalMoney(old.getTotalMoney());
		u.setRoles(old.getRoles());
		u.setPosts(old.getPosts());
		userRepository.save(u);
	}

	@Override
	public UserDTO findOneById(int id) {
		// TODO Auto-generated method stub
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
	public boolean save(String token, String pw) {
		// TODO Auto-generated method stub
		User u =userRepository.findOneByToken(token).orElse(null);
		if(u==null) return false;
		
		u.setActive(true);
		u.setNonBlock(true);
		u.setPassword(bCryptPasswordEncode.encode(pw));
		userRepository.save(u);
		return true;
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
			u.setTimeTokenFuture(10);
			UriComponents value = UriComponentsBuilder.newInstance().scheme(r.getScheme()).host(r.getServerName())
					.port(r.getServerPort()).path("forgot-password").queryParam("token", u.getToken()).build(true);
			userRepository.save(u);
			emailService.sendSimpleMessage(email, "Link", value + "");
			return true;
		}
		return false;
	}

	@Override
	public Page<UserDTO> findAll(Map<String, String> q) {
		// TODO Auto-generated method stub
		int pageNumber = Integer.valueOf(q.get("pageNumber")) - 1;
		int pageSize = Integer.valueOf(q.get("pageSize"));
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		return userRepository.findAll(pageable).map(userConverter ::toDTO);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		userRepository.deleteById(id);
	}

	

}
