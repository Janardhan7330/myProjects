package com.example.demo.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

//custom annotation for password matching
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=passwordMatcherValidation.class)
public @interface passwordMatcher{
	String message() default "Passwords do not matched";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

}
