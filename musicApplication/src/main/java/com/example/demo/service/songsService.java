package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Songs;
import com.example.demo.repo.songsRepository;

@Service
public class songsService {

	@Autowired
	private songsRepository songRepository;
	
	
	//this method is used to show/view all the songs
	public List<Songs> findAllSongs(){
		
		return songRepository.findAll();
	}
	
	//To store all the Songs into the DataBase.
	public Songs saveSongs(Songs song) {
		
		return songRepository.save(song);
	}
	
	//To update records by Using Id;
	//update songs set songName="Innisai Padivarum" where id=111;
	public Songs editById(Long id) {
		/*
		 *  to avoid unexpected errors by using Id
		 *  because Id is auto generated 
		 *  Hence Optional is required.
		 * */
		Optional<Songs> optionalSong=songRepository.findById(id);
		return optionalSong.orElse(null);//Returns the Songs or Null if not found.
	}
	
	//Delete From Songs where id=111;
	public void deleteSong(Long id) {
		songRepository.deleteById(id);
	}
	
    public List<Songs> findByLanguage(String language) {
        return songRepository.findByLanguage(language);
    }

    public List<Songs> findByCategory(String category) {
        return songRepository.findByCategory(category);
    }
    
    public List<Songs> findByArtist(String artist) {
        return songRepository.findByArtist(artist);
    }
    
    public List<Songs> searchSongs(String query, String type) {
        if (query == null || query.isEmpty()) {
            return songRepository.findAll();
        }
        switch (type) {
            case "artist":
                return songRepository.findByArtist(query);
            case "language":
                return songRepository.findByLanguage(query);
            case "category":
                return songRepository.findByCategory(query);
            default:
                return songRepository.findBySong(query);
        }
    }
}
