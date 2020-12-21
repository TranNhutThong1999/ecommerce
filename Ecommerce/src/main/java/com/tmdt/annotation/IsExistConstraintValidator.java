package com.tmdt.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.tmdt.entity.User;
import com.tmdt.service.IUserService;

public class IsExistConstraintValidator implements ConstraintValidator<IsExist, String> {
	@Autowired
	private IUserService userService;

	public boolean isValid(String value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		if (value != null) {
			User user = userService.findOneByUserName(value).orElse(null);
			return user != null ? false : true;
		}
		return true;
	}

}
