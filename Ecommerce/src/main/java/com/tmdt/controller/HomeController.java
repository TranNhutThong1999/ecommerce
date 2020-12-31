package com.tmdt.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tmdt.dto.PostDTO;
import com.tmdt.security.CustomUserDetail;
import com.tmdt.service.IActionService;
import com.tmdt.service.IUserService;

@Controller
@RequestMapping("/home")
public class HomeController {
	@Autowired
	private CustomUserDetail customUserDetail;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IActionService actionService;
	@GetMapping
	public String defauls(ModelMap modelMap,HttpSession r, @RequestParam(required = false ,defaultValue = "") String message) {
		List<String> roles = new ArrayList<String>();
		customUserDetail.getPrinciple().getAuthorities().stream().forEach(a -> roles.add(a.getAuthority()));
		if(roles.contains("ROLE_ADMIN")) {
			return "redirect:/register";
		}
		if(message.equals("login_SUCCESS")) {
			System.out.println("vaod");
			modelMap.addAttribute("message","login");
		}
		return "Home";
	}
}
