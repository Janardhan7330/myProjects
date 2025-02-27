package com.example.demo.validation;

import com.example.demo.model.User;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class passwordMatcherValidation implements ConstraintValidator<passwordMatcher, User> {

	@Override
	public void initialize(passwordMatcher constraintAnnotation) {
		
	}
	@Override
	public boolean isValid(User user,ConstraintValidatorContext context) {
		return user.ispasswordmatched();
	}
}
