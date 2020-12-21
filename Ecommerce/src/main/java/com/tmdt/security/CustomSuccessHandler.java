package com.tmdt.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.ui.ModelMap;


public class CustomSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication,
			ModelMap m) throws IOException, ServletException {
		System.out.println("thanh cong");
		String targetUrl = determineTargetUrl(authentication);
		if (response.isCommitted()) {
			return;
		}
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	private String determineTargetUrl(Authentication authentication) {
		// TODO Auto-generated method stub
		List<String> roles = new ArrayList();
		authentication.getAuthorities().stream().forEach(a -> roles.add(a.getAuthority()));
		String url = "/homesdasd";
//		if (roles.contains("ROLE_ADMIN")) {
//			url = "/Admin-SanPham/";
//		} else if (roles.contains("ROLE_USER")) {
//			url = "/Home";
//		}
		System.out.println("thanh cong");
		return url;
	}

	public RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

}
