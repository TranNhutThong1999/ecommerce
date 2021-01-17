package com.tmdt.controller;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tmdt.api.UpdateUser;
import com.tmdt.dto.UserDTO;
import com.tmdt.service.IUserService;
import com.tmdt.util.EmailService;

@Controller
public class UserController {
	@Autowired
	private IUserService userService;

	@Autowired
	private PasswordEncoder bCryptPasswordEncode;

	@Autowired
	private EmailService emailService;
	
	@GetMapping("/users/{id}")
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	public String defaults(@PathVariable int id, ModelMap modelMap) {
		UserDTO u = userService.findOneById(id);
		modelMap.addAttribute("Histories", u.getDepositHistories());
		modelMap.addAttribute("User", u);
		return "Profile";
	}

	@GetMapping("/edit_user/{id}")
	public String editUser(ModelMap modelMap, @PathVariable int id) {
		modelMap.addAttribute("User", userService.findOneById(id));
		return "editUser";
	}

	@PostMapping("/edit_user")
	public String register(@Valid @ModelAttribute("User") UpdateUser user, BindingResult error, ModelMap model,
			@RequestParam(value = "file", required = false) MultipartFile file) {
		boolean err = false;
		if (!user.getPassword().equals("")) {
			if (!user.confirmPassword()) {
				model.addAttribute("confirmPassword", "Confirm password not match");
				err = true;
			}
			if (user.getPassword().length() < 6 || user.getPassword().length() > 25) {
				System.out.println(user.getPassword() + "a");
				model.addAttribute("password", "Between 6 and 20 characters");
				err = true;
			}
		}
		if (error.hasErrors() || err) {
			return "editUser";
		}
		UserDTO u = userService.findOneById(user.getId());
		userService.save(user.convertDTO(u), file);
		return "redirect:/users/" + user.getId();
	}
	@GetMapping("/forgot-password")
	public String showForgotPassword(ModelMap modelMap, @RequestParam String token) {
		return "ForgotPassword";
	}
	
	@PostMapping("/submit-forgot-password")
	public String forgotPassword(@RequestParam String password, @RequestParam String confirmPassword,
			@RequestParam String token, ModelMap model) {
		if (password.length() < 6 || password.length() > 25) {
			model.addAttribute("password", "Between 6 and 20 characters");
			return "ForgotPassword";
		}
		if (!confirmPassword.equals(password)) {
			model.addAttribute("confirmPassword", "Confirm password not match");
			return "ForgotPassword";
		}
	
		UserDTO dto = userService.findOneByToken(token);
		if (dto == null) {
			model.addAttribute("message", "token");
			return "ForgotPassword";
		}
		dto.setPassword(bCryptPasswordEncode.encode(password));
		userService.save(dto);
		model.addAttribute("message", "ok");
		return "redirect:/login";
	}
	@GetMapping("/send-forgot-password")
	public String sendForgotPassword(HttpServletRequest r) {
		return "SendToken";
	}
	@PostMapping("/submit-token")
	public String sendToken( ModelMap model, @RequestParam String email, HttpServletRequest r) {
		if(!Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", email)) {
			model.addAttribute("email","Email không đúng định dạng");
			return "SendToken";
		}
		if(userService.sendMailToken(email,r)) {
			model.addAttribute("message","ok");
		}else {
			model.addAttribute("email","Email không tồn tại");
		}
		return "SendToken";
	}
}
