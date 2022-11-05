package com.skilldistillery.eventtracker.repositories;

import java.util.List;

import org.aspectj.weaver.ast.Literal;
import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.eventtracker.entities.Game;

public interface GameRepository extends JpaRepository<Game, Integer>{

	List<Game> findByPublisherName(String name);
	
	List<Game> findByGenreName(String name);
	
	List<Game> findByTitleContainsOrDescriptionContains(String keyword, String keywordAgain);
	
	List<Game> findByMultiplayerEquals(boolean isMultiplayer);

	List<Game> findByMsrpGreaterThanEqualToAndMsrpLessThanEqualTo(double low, double high);
}
