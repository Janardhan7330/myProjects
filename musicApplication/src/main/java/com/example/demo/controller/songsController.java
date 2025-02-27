package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Library;
import com.example.demo.model.Songs;
import com.example.demo.service.songsService;

import jakarta.servlet.http.HttpSession;


@Controller
public class songsController {

	@Autowired
	private songsService songService;

	//To load the added songs from the user side
	@GetMapping("/songs")
	public String showSongs(Model model) {
		
		List<Songs> songs=songService.findAllSongs();
	    model.addAttribute("songs", songs);
	    return "userLoginHome";
	}
	
	
	//A method to add Songs into the library
	@PostMapping("/add-to-library")
	public String addToLibrary(Long songId,HttpSession session) {
		
		Library library=(Library) session.getAttribute("library");
		
		if(library==null) {
			library=new Library();
			session.setAttribute("library", library);
		}
		
		//Find the song by its Id and add the song into the library
		Songs songs=songService.editById(songId);
		if(songs != null) {
			library.addSongs(songs);
		}
		return "redirect:/songs";
	}
	
	
	//To show the library
	
	@GetMapping("/library")
	public String viewLibrary(HttpSession session,Model model) {
		
		Library library=(Library) session.getAttribute("library");
		
		if(library==null) {
		library=new Library();
		}
		
		model.addAttribute("librarySongs", library.getSongs());
		
		return "library";
	}
	
	//To proceed the checkout
	@PostMapping("/checkout")
	public String checkout(HttpSession session) {
		session.removeAttribute("library");
		return "library-deleted";
	}
	
	
	//To show the Search Page
	@GetMapping("/search")
	public String showSearchPage(@RequestParam(required = false) String query,
            @RequestParam(required = false) String type, 
            Model model) {
List<Songs> searchResults = songService.searchSongs(query, type);
model.addAttribute("songs", searchResults);
return "search";
	}
	
	//get profile page
    @GetMapping("/profile")
    public String showProfile() {
    	return "profile";
    }
				 
	
}
