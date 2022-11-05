package com.skilldistillery.eventtracker.services;

import java.util.List;

import com.skilldistillery.eventtracker.entities.Game;

public interface GameService {

	List<Game> findAllGames();
	
	Game findGameById(int id);
	
	List<Game> findGamesByGenreName(String name); 
	
	List<Game> findGamesByPublisherName(String name);
	
	List<Game> findGamesByKeyword(String keyword);
	
	List<Game> findGamesByMultiplayer(boolean isMultiplayer);
	
	List<Game> findGamesByPriceRange(double low, double high);
	
	Game createGame(Game game);
	
	boolean deleteGame(Game game);
	
	Game updateGame(Game newGame, int oldId);
}
