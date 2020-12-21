package com.tmdt.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;




public class AddressConstraintValidator implements ConstraintValidator<Address, Integer> {


	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		if (value != null) {
			return value == 2? false : true;
		}
		return true;
	}

}
