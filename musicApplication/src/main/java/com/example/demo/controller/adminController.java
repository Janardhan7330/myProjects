package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Songs;
import com.example.demo.service.songsService;

@Controller
public class adminController {

    // Admin credentials (Consider storing in application.properties for better security)
    private static final String ADMIN_USERNAME = "Admin";
    private static final String ADMIN_PASSWORD = "Jana@7330";

    @Autowired
    private songsService songsService;

    /**
     * Displays the Admin Login Page.
     */
    @GetMapping("/admin/login")
    public String showAdminLogin() {
        return "admin/adminLogin";
    }

    /**
     * Handles Admin Login Authentication.
     * Redirects to the song management page if login is successful, 
     * otherwise returns to the login page with an error message.
     */
    @PostMapping("/admin/login")
    public String adminLogin(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             Model model) {
        if (ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)) {
            return "redirect:/admin/songs";
        } else {
            model.addAttribute("error", "Invalid Username and Password!");
            return "admin/adminLogin";
        }
    }

    /**
     * Displays the Add Song Form.
     */
    @GetMapping("/admin/songs/add")
    public String showAddSongForm(Model model) {
        model.addAttribute("song", new Songs()); // Empty song object for form binding
        // Fetch songs by language
        model.addAttribute("tamilSongs", songsService.findByLanguage("Tamil"));
        model.addAttribute("teluguSongs", songsService.findByLanguage("Telugu"));
        model.addAttribute("englishSongs", songsService.findByLanguage("English"));
        model.addAttribute("hindiSongs", songsService.findByLanguage("Hindi"));

        // Fetch songs by category (Browse All Section)
        model.addAttribute("loveSongs", songsService.findByCategory("Love"));
        model.addAttribute("sadSongs", songsService.findByCategory("Sad"));
        model.addAttribute("motivationalSongs", songsService.findByCategory("Motivation"));
        model.addAttribute("romanticSongs", songsService.findByCategory("Romantic"));
        model.addAttribute("partySongs", songsService.findByCategory("Party"));

        // Fetch songs by artist 
        model.addAttribute("sidsriramSongs", songsService.findByArtist("Sid Sriram"));
        model.addAttribute("arRahmanSongs", songsService.findByArtist("A.R. Rahman"));
        model.addAttribute("anirudhSongs", songsService.findByArtist("Anirudh"));
        model.addAttribute("billieeilishSongs", songsService.findByArtist("Billie Eilish"));
        model.addAttribute("sonunigamSongs", songsService.findByArtist("Sonu Nigam"));

        return "admin/upload";
    }

    /**
     * Handles Adding a New Song.
     * Redirects to the song list after successful addition.
     */
    @PostMapping("/admin/songs/add")
    public String addSong(@ModelAttribute Songs song) {
        songsService.saveSongs(song);
        return "redirect:/admin/songs";
    }
    
    /**
     * Displays the list of all songs available for Admin.
     */
    @GetMapping("/admin/songs")
    public String showAdminSongs(Model model) {
        List<Songs> songs = songsService.findAllSongs();
        model.addAttribute("songs", songs);
        return "admin/songs";
    }

    /**
     * Handles Song Deletion by Admin.
     * Redirects back to the song list after deletion.
     */
    @GetMapping("/admin/songs/delete")
    public String deleteSong(@RequestParam("id") Long id) {
        songsService.deleteSong(id);
        return "redirect:/admin/songs";
    }
}
