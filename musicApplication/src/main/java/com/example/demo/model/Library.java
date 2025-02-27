package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Library {

	//in library, user will load multiple songs, Hence the Songs class is loaded as list
	private List<Songs> song=new ArrayList<>();
	
	//To add songs into the Library
	public void addSongs(Songs songs) {
		
		song.add(songs);
	}
	
	
	//To List all the songs in the Library
	public List<Songs> getSongs(){
		
		return song;
	}
	
	
	//To clear the Songs From the Library
	//After checkout the Library will be cleared, will become empty
//	public void libraryClear() {
//		
//		song.clear();
//	}
	
	
	//Remove individual Song from the Library
	public void removeOneSong(Songs songs) {
		
		song.remove(songs);
	}
}
