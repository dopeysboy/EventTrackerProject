package com.skilldistillery.eventtracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.eventtracker.entities.Game;

public interface GameRepository extends JpaRepository<Game, Integer>{

	List<Game> findByPublishers_NameContains(String name);
	
	List<Game> findByGenres_NameContains(String name);
	
	List<Game> findByTitleContainsOrDescriptionContains(String keyword, String keywordAgain);
	
	List<Game> findByOnlineMpEquals(boolean isMultiplayer);

	List<Game> findByMsrpGreaterThanEqualAndMsrpLessThanEqual(double low, double high);
}
