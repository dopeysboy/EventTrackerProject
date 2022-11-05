package com.skilldistillery.eventtracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.eventtracker.entities.Genre;
import com.skilldistillery.eventtracker.repositories.GenreRepository;

@Service
public class GenreServiceImpl implements GenreService{

	@Autowired
	private GenreRepository genreRepo;
	
	@Override
	public List<Genre> findAllGenres() {
		return genreRepo.findAll();
	}

	@Override
	public List<Genre> findByKeyword(String keyword) {
		return genreRepo.findByNameContains(keyword);
	}

	@Override
	public Genre findById(int id) {
		Optional<Genre> genreOpt = genreRepo.findById(id);
		
		if(genreOpt.isEmpty()) {
			return null;
		} else {
			return genreOpt.get();
		}
	}

	@Override
	public Genre createGenre(Genre genre) {
		if(genre == null || genre.getName() == null) {
			return null;
		}
		
		genre = genreRepo.saveAndFlush(genre);
		return genre;
	}

	@Override
	public Genre updateGenre(Genre newGenre, int oldId) {
		Optional<Genre> genreOpt = genreRepo.findById(oldId);
		
		if(genreOpt.isEmpty() || newGenre.getName() == null) {
			return null;
		}
		
		newGenre.setId(oldId);
		newGenre = genreRepo.saveAndFlush(newGenre);
		
		return newGenre;
	}

	@Override
	public boolean deleteGenre(int id) {
		Optional<Genre> genreOpt = genreRepo.findById(id);
		
		if(genreOpt.isEmpty()) {
			return false;
		}
		
		genreRepo.delete(genreOpt.get());
		
		return true;
	}

	
}
