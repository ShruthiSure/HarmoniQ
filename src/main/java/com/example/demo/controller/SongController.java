package com.example.demo.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entities.Song;
import com.example.demo.services.SongService;

@Controller
public class SongController {
	
	@Autowired
	SongService service;
	
	@PostMapping("/newSong")
	public String addSong(@ModelAttribute Song song) {
		boolean songStatus = service.songExists(song.getName());
		if(songStatus == false) {
			service.addSong(song);
			System.out.println("Song added successfully");
		}else {
			System.out.println("Song already Exists");
		}

		return "adminhome";
	}
	
	@GetMapping("/viewsongs")
	public String viewSongs(Model model) {
		List<Song> songlist = service.fetchAllSongs();
		
		model.addAttribute("songs",songlist);
		return "displaysongs";
		
	}
	@GetMapping("/playSongs")
	public String playSongs(Model model) {
		
		boolean premiumUser = false;
		if(premiumUser == true) {
			List<Song> songlist = service.fetchAllSongs();
			model.addAttribute("songs",songlist);
			return "displaysongs";
		}
		else {
		return "makepayment";
		}
		
	}

}
