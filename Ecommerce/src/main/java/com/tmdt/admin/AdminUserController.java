package com.tmdt.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tmdt.api.UpdateUser;
import com.tmdt.dto.UserDTO;
import com.tmdt.service.IUserService;

@Controller
@RequestMapping("/admin")
public class AdminUserController {
	@Autowired
	private IUserService userService;
	
	@GetMapping("/users")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN') ")
	public String showUsers(ModelMap modelMap, @RequestParam(required = false ,defaultValue = "") String message) {
		modelMap.addAttribute("message", message);
		return "Admin_Users";
	}
	@GetMapping("/add")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN') ")
	public String addUsers(ModelMap modelMap) {
		modelMap.addAttribute("User", new UserDTO());
		return "Admin_AddUser";
	}
	
	@GetMapping("/edit/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN') ")
	public String editUsers(ModelMap modelMap, @PathVariable int id) {
		modelMap.addAttribute("User", userService.findOneById(id));
		return "editUser";
	}
	
	
	
	@PostMapping("/add")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN') ")
	public String submitUsers(@Valid @ModelAttribute("User") UserDTO user, BindingResult error, ModelMap modelMap ) {
		if (error.hasErrors() || !user.confirmPassword()) {
			if (!user.confirmPassword()) {
				modelMap.addAttribute("confirmPassword", "Confirm password not match");
			}
			return "Admin_AddUser";
		}
		userService.save(user);
		return "redirect:/admin/users?message=verify_SUCCESS";
	}
}
