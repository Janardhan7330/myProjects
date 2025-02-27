package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Songs;
import com.example.demo.model.User;
import com.example.demo.service.songsService;
import com.example.demo.service.userService;

import jakarta.validation.Valid;

@Controller
public class userController {

	@Autowired
	private userService UserService;
	@Autowired
	private songsService songService;
	
	@GetMapping("/index")
	public String showFrontPage() {
		
		return "index";
	}
	@GetMapping("/login")
	public String showUserLoginPage() {
		return "login";
	}
	@GetMapping("/signup")
	public String showRegisterPage(Model model) {
		model.addAttribute("user",new User());
		return "signup";
	}
	
	//this method helps to implement the registering authorized users and throws validation errors. 
	
	@PostMapping("/signup")
	public String registerUser(@Valid User user,BindingResult bindingResult,Model model){
		List<String> errors=UserService.registerUser(user, bindingResult);
		
		if(!errors.isEmpty()) {
			model.addAttribute("errors", errors);
			return "signup";
		}
		return "redirect:/login";
	}
	
	//login process success
	@PostMapping("/login")
	public String loginUser(String username,String password,Model model) {
		
		User user=UserService.loginUser(username, password);
		if(user != null) {
			model.addAttribute(user);
			List<Songs> songs=songService.findAllSongs();
		    model.addAttribute("songs", songs);
			return "userLoginHome";
		}
		
		model.addAttribute("error", "Invalid UserName and Password!!!!");
		return "login";
	}
}
