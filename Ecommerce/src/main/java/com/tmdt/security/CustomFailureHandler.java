package com.tmdt.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class CustomFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String message = handlerMessage(exception);
		String url = "/login?message=" + message;
		redirectStrategy.sendRedirect(request, response, url);
	}

	private String handlerMessage(AuthenticationException exception) {
		String result = "";
		if (LockedException.class.isInstance(exception)) {
			result = "locked";
		} else if (BadCredentialsException.class.isInstance(exception)) {
			result = "failure";
		}
		return result;
	}

	public RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}
}
