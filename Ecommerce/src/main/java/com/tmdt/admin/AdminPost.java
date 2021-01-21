package com.tmdt.admin;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminPost {
	@GetMapping("/approve")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN') ")
	public String getPostApprove(ModelMap modelMap, @RequestParam(required = false, defaultValue = "") String message) {
		return "Admin_Approve";
	}
	
	@GetMapping("/posts")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN') ")
	public String getPost(ModelMap modelMap, @RequestParam(required = false, defaultValue = "") String message) {
		return "Admin_Post";
	}
	
	@GetMapping("/feedback")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN') ")
	public String getFeedback(ModelMap modelMap, @RequestParam(required = false, defaultValue = "") String message) {
		return "Admin_Feedback";
	}
}
