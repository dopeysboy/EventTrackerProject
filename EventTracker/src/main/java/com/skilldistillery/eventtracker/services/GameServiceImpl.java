package com.skilldistillery.eventtracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.eventtracker.entities.Game;
import com.skilldistillery.eventtracker.entities.Genre;
import com.skilldistillery.eventtracker.entities.Publisher;
import com.skilldistillery.eventtracker.repositories.GameRepository;
import com.skilldistillery.eventtracker.repositories.GenreRepository;
import com.skilldistillery.eventtracker.repositories.PublisherRepository;

@Service
public class GameServiceImpl implements GameService{
	@Autowired
	private GameRepository gameRepo;
	@Autowired
	private GenreRepository genreRepo;
	@Autowired
	private PublisherRepository pubRepo;

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
		List<Game> games = gameRepo.findByGenres_NameContains(name);
		return games;
	}

	@Override
	public List<Game> findGamesByPublisherName(String name) {
		return gameRepo.findByPublishers_NameContains(name);
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

			newGame.setId(oldId);
			gameRepo.saveAndFlush(newGame);
			
			return newGame;
		}
	}

	@Override
	public Game addGenreToGame(int gameId, int genreId) {
		Optional<Genre> genreOpt = genreRepo.findById(genreId);
		Optional<Game> gameOpt = gameRepo.findById(gameId);
		
		if(genreOpt.isEmpty() || gameOpt.isEmpty()) {
			return null;
		}

		Game game = gameOpt.get();
		Genre genre = genreOpt.get();
		
		game.addGenre(genre);
		
		genreRepo.saveAndFlush(genre);
		gameRepo.saveAndFlush(game);
		
		return game;
	}

	@Override
	public Game addPublisherToGame(int gameId, int publisherId) {
		Optional<Game> gameOpt = gameRepo.findById(gameId);
		Optional<Publisher> pubOpt = pubRepo.findById(publisherId);
		
		if(gameOpt.isEmpty() || pubOpt.isEmpty()) {
			return null;
		}
		
		Game game = gameOpt.get();
		Publisher pub = pubOpt.get();
		
		game.addPublisher(pub);
		
		pubRepo.saveAndFlush(pub);
		gameRepo.saveAndFlush(game);
		
		return game;
	}

	@Override
	public Game removeGenreFromGame(int gameId, int genreId) {
		Optional<Genre> genreOpt = genreRepo.findById(genreId);
		Optional<Game> gameOpt = gameRepo.findById(gameId);
		
		if(genreOpt.isEmpty() || gameOpt.isEmpty()) {
			return null;
		}

		Game game = gameOpt.get();
		Genre genre = genreOpt.get();
		
		game.removeGenre(genre);
		
		genreRepo.saveAndFlush(genre);
		gameRepo.saveAndFlush(game);
		
		return game;
	}

	@Override
	public Game removePublisherFromGame(int gameId, int publisherId) {
		Optional<Game> gameOpt = gameRepo.findById(gameId);
		Optional<Publisher> pubOpt = pubRepo.findById(publisherId);
		
		if(gameOpt.isEmpty() || pubOpt.isEmpty()) {
			return null;
		}
		
		Game game = gameOpt.get();
		Publisher pub = pubOpt.get();
		
		game.removePublisher(pub);
		
		pubRepo.saveAndFlush(pub);
		gameRepo.saveAndFlush(game);
		
		return game;
	}
}
