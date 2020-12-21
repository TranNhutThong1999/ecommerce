package com.tmdt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tmdt.dto.PostDTO;
import com.tmdt.security.CustomUserDetail;
import com.tmdt.service.IUserService;

@Controller
@RequestMapping("/home")
public class HomeController {
	@Autowired
	private CustomUserDetail customUserDetail;
	
	@Autowired
	private IUserService userService;
	
	@GetMapping
	public String defauls(ModelMap modelMap) {
		List<String> roles = new ArrayList<String>();
		customUserDetail.getPrinciple().getAuthorities().stream().forEach(a -> roles.add(a.getAuthority()));
		if(roles.contains("ROLE_ADMIN")) {
			return "redirect:/register";
		}
		
		
		return "Home";
	}
}
