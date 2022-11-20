package com.skilldistillery.eventtracker.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.eventtracker.entities.Genre;
import com.skilldistillery.eventtracker.services.GenreService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost/"})
public class GenreController {

	@Autowired
	private GenreService genreServ;
	
	@GetMapping("genres")
	public List<Genre> getAllGenres(){
		List<Genre> genres = genreServ.findAllGenres();
		
		return genres;
	}
	
	@GetMapping("genres/{keyword}")
	public List<Genre> getGenreByKeyword(@PathVariable String keyword){
		List<Genre> genres = genreServ.findByKeyword(keyword);
		
		return genres;
	}
	
	@GetMapping("genres/id/{id}")
	public Genre getGenreById(@PathVariable int id, HttpServletResponse resp) {
		Genre genre = genreServ.findById(id);
		
		if(genre == null) {
			resp.setStatus(404);
		}
		
		return genre;
	}
	
	@PostMapping("genres")
	public Genre createGenre(@RequestBody Genre genre, HttpServletResponse resp, HttpServletRequest req) {
		genre = genreServ.createGenre(genre);
		
		if(genre == null) {
			resp.setStatus(400);
		} else {
			StringBuffer url = req.getRequestURL();
			url.append("/id/").append(genre.getId());
			
			resp.setHeader("Location", url.toString());
			resp.setStatus(201);
		}
		
		return genre;
	}
	
	@PutMapping("genres/id/{id}")
	public Genre updateGenre(@RequestBody Genre genre,
							@PathVariable int id,
							HttpServletRequest req,
							HttpServletResponse resp) {
		genre = genreServ.updateGenre(genre, id);
		
		if(genre == null) {
			resp.setStatus(400);
		} else {
			resp.setHeader("Location", req.getRequestURL().toString());
		}
		
		return genre;
	}
	
	@DeleteMapping("genres/id/{id}")
	public void deleteGenre(@PathVariable int id, HttpServletResponse resp) {
		boolean successful = genreServ.deleteGenre(id);
		
		if(!successful) {
			resp.setStatus(400);
		}
	}
}
