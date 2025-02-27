package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.example.demo.model.User;
import com.example.demo.repo.userRepository;

@Service
public class userService {

	@Autowired
	private userRepository UserRepo;
	
	//1. checks whether it contains validation Error
		public List<String> registerUser(User user,BindingResult bindingResult){
			List<String> errors=new ArrayList<>();
			
			//a. checking if the user_name already exits
			if(UserRepo.findByUsername(user.getUsername()) != null) {
				errors.add("User name already exits");
			}
			
			//checks whether it contains validation Error
			if(bindingResult.hasErrors()) {
				
				for(ObjectError error:bindingResult.getAllErrors()) {
					errors.add(error.getDefaultMessage());
				}
			}
	        //b. password and confirmPassword match validation
			if(!user.ispasswordmatched()) {
				errors.add("Password and confirmPassword must be same!!!");
			}
			if(errors.isEmpty()) {
				UserRepo.save(user);
			}
				return errors;
		}

	//2. to Perform the success login 
		public User loginUser(String username, String password) {
			
			User user= UserRepo.findByUsername(username);
			if(user != null && user.getPassword().equals(password)) {
				return user;
			}
			return null;
		}
}
