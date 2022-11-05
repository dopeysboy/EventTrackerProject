package com.skilldistillery.eventtracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.eventtracker.entities.Game;
import com.skilldistillery.eventtracker.repositories.GameRepository;

@Service
public class GameServiceImpl implements GameService{
	@Autowired
	private GameRepository gameRepo;

	@Override
	public List<Game> findAllGames() {
		return gameRepo.findAll();
	}

	@Override
	public Game findGameById(int id) {
		Optional<Game> optGame = gameRepo.findById(id);
	
		if(optGame.isPresent()) {
			return optGame.get();
		} else {
			return null;
		}
	}

	@Override
	public List<Game> findGamesByGenreName(String name) {
		List<Game> games = gameRepo.findByGenres_Name(name);
		return games;
	}

	@Override
	public List<Game> findGamesByPublisherName(String name) {
		return gameRepo.findByPublishers_Name(name);
	}

	@Override
	public List<Game> findGamesByKeyword(String keyword) {
		return gameRepo.findByTitleContainsOrDescriptionContains(keyword, keyword);
	}

	@Override
	public List<Game> findGamesByMultiplayer(boolean isMultiplayer) {
		return gameRepo.findByOnlineMpEquals(isMultiplayer);
	}

	@Override
	public List<Game> findGamesByPriceRange(double low, double high) {
		return gameRepo.findByMsrpGreaterThanEqualAndMsrpLessThanEqual(low, high);
	}

	@Override
	public Game createGame(Game game) {
		if(game == null) return null;
		if(game.getTitle() == null) return null;
		if(game.getMsrp() == null) game.setMsrp(59.99);
		if(game.getOnlineMp() == null) game.setOnlineMp(false);
		
		gameRepo.saveAndFlush(game);
		return game;
	}

	@Override
	public boolean deleteGame(Game game) {
		if(game == null) return false;
		gameRepo.delete(game);
		gameRepo.flush();
		return true;
	}

	@Override
	public Game updateGame(Game newGame, int oldId) {
		Optional<Game> gameOpt = gameRepo.findById(oldId);
		if(gameOpt.isEmpty()) {
			return null;
		} else {
			if(newGame.getTitle() == null || newGame.getMsrp() == null 
					|| newGame.getOnlineMp() == null) {
				return null;
			}
			
			gameRepo.saveAndFlush(newGame);
			return newGame;
		}
	}
}
