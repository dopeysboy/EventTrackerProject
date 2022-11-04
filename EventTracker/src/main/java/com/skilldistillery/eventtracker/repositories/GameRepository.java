package com.skilldistillery.eventtracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.eventtracker.entities.Game;

public interface GameRepository extends JpaRepository<Game, Integer>{

}
