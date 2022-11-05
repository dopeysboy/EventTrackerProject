package com.skilldistillery.eventtracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.eventtracker.entities.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer>{

	List<Genre> findByNameContains(String keyword);

}
