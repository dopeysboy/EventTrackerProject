package com.skilldistillery.eventtracker.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.RepaintManager;
import javax.websocket.server.PathParam;

import org.aspectj.weaver.ast.Literal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.eventtracker.entities.Game;
import com.skilldistillery.eventtracker.services.GameService;

@RestController
@RequestMapping("api")
public class GameController {

	@Autowired
	private GameService gameServ;
	
	@GetMapping("games")
	public List<Game> getAllGames(){
		return gameServ.findAllGames();
	}
	
	@GetMapping("games/id/{id}")
	public Game getGameById(@PathVariable int id, HttpServletResponse resp) {
		Game game = gameServ.findGameById(id);
		
		if(game == null) {
			resp.setStatus(404);
		}
		
		return game;
	}
	
	@GetMapping("games/genre/{name}")
	public List<Game> getGamesByGenreName(@PathVariable String name, HttpServletResponse resp){
		List<Game> games = gameServ.findGamesByGenreName(name);
		
		//might need to do something with this later
//		if(games == null) {
//		}
		
		return games;
	}
	
	@GetMapping("games/keyword/{keyword}")
	public List<Game> getGamesByKeyword(@PathVariable String keyword){
		List<Game> games = gameServ.findGamesByKeyword(keyword);
		
		return games;
	}
	
	@GetMapping("games/mp/{isMp}")
	public List<Game> getGamesByMultiplayer(@PathVariable boolean isMp, HttpServletResponse resp){
		List<Game> games = gameServ.findGamesByMultiplayer(isMp);
		
		return games;
	}
	
	@GetMapping("games/msrp/{low}/{high}")
	public List<Game> getGamesByPriceRange(@PathVariable double low, @PathVariable double high){
		List<Game> games = gameServ.findGamesByPriceRange(low, high);
		
		return games;
	}
	
	@PostMapping("games")
	public Game createGame(@RequestBody Game game, HttpServletResponse resp, HttpServletRequest req) {
		game = gameServ.createGame(game);

		if(game == null) {
			resp.setStatus(400);
		} else {
			StringBuffer url = req.getRequestURL();
			url.append("/search/id/").append(game.getId());
			
			resp.setStatus(201);
			resp.setHeader("Location", url.toString());
		}
		
		return game;
	}
	
	@PutMapping("games/id/{id}")
	public Game updateGame(@RequestBody Game game,
							@PathVariable int id,
							HttpServletRequest req,
							HttpServletResponse resp) {
		game = gameServ.updateGame(game, id);
		
		if(game == null) {
			resp.setStatus(400);
		}
		
		StringBuffer url = req.getRequestURL();
		resp.setHeader("Location", url.toString());
		
		return game;
	}
	
	@DeleteMapping("games/id/{id}")
	public void deleteGame(@PathVariable int id, HttpServletRequest req, HttpServletResponse resp) {
		Game game = gameServ.findGameById(id);
		
		if(game == null) {
			resp.setStatus(404);
			return;
		}
		
		boolean successful = gameServ.deleteGame(game);
		
		if(successful) {
			resp.setStatus(202);
		} else {
			resp.setStatus(400);
		}
	}
}
