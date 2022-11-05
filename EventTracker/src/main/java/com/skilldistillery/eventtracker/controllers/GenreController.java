package com.skilldistillery.eventtracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.eventtracker.entities.Genre;
import com.skilldistillery.eventtracker.services.GenreService;

@RestController
@RequestMapping("api")
public class GenreController {

	@Autowired
	private GenreService genreServ;
	
	@GetMapping("genres")
	public List<Genre> getAllGenres(){
		return genreServ.findAllGenres();
	}
}
