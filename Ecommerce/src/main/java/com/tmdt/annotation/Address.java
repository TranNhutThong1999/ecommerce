package com.tmdt.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = AddressConstraintValidator.class)  
@Target( { ElementType.METHOD, ElementType.FIELD } )  
@Retention(RetentionPolicy.RUNTIME) 
public @interface Address {
	// error message
	public String message() default "Not null";

//represents group of constraints     
	public Class<?>[] groups() default {};

//represents additional information about annotation  
	public Class<? extends Payload>[] payload() default {};
}
