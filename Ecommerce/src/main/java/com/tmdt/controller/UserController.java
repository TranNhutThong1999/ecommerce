package com.tmdt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tmdt.dto.UserDTO;
import com.tmdt.service.IUserService;

@Controller
@RequestMapping("/users")
public class UserController {
	@Autowired
	private IUserService userService;
	
		@GetMapping("/{id}")
		public String defaults(@PathVariable int id,ModelMap modelMap) {
			UserDTO u= userService.findOneById(id);
			modelMap.addAttribute("Histories", u.getDepositHistories());
			modelMap.addAttribute("User", u);
			return "Profile";
		}
}
