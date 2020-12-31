package com.tmdt.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tmdt.dto.UserDTO;
import com.tmdt.service.IUserService;
import com.tmdt.util.EmailService;
import com.tmdt.util.MessagesUtil;

@Controller
public class LoginController {
	@Autowired
	private IUserService userService;

	@Autowired
	private MessagesUtil messageUtil;

	@GetMapping("/login")
	public String loginDefaul(@RequestParam(required = false, defaultValue = "") String message, ModelMap modelMap) {
		if (!message.equals("")) {
			Map<String, String> m = messageUtil.Messages(message);
			modelMap.addAttribute("message", m.get("message"));
		}
		return "login";
	}

	@GetMapping("/register")
	public String register(ModelMap modelMap) {
		modelMap.addAttribute("User", new UserDTO());
		return "register";
	}

	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("User") UserDTO user, BindingResult error,
			@RequestParam(value = "file", required = false) MultipartFile file, Model model) {
		if (error.hasErrors() || !user.confirmPassword()) {
			if (!user.confirmPassword()) {
				model.addAttribute("confirmPassword", "Confirm password not match");
			}
			return "Register";
		}
		userService.save(user, file);
		model.addAttribute("run", 1);
		model.addAttribute("id", user.getId());
		return "verify";
	}

	@PostMapping("/verify")
	public String verify(@RequestParam String token, ModelMap modelMap) {
		int id= userService.verify(token);
		if ( id == 0) {
			modelMap.addAttribute("err", "Code không đúng hoặc đã hết hạn");
			modelMap.addAttribute("run", 1);
			modelMap.addAttribute("id", id);
			return "verify";
		}
		return "redirect:/login?messages=verify_SUCCESS";
	}
}
