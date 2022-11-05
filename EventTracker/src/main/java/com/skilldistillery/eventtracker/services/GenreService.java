package com.skilldistillery.eventtracker.services;

import java.util.List;

import com.skilldistillery.eventtracker.entities.Genre;

public interface GenreService {

	List<Genre> findAllGenres();
	
	List<Genre> findByKeyword(String keyword);
	
	Genre findById(int id);
	
	Genre createGenre(Genre genre);
	
	Genre updateGenre(Genre newGenre, int oldId);
	
	boolean deleteGenre(int id);
}
