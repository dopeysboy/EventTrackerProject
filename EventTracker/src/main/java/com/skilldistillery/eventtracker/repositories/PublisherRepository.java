package com.skilldistillery.eventtracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.eventtracker.entities.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Integer>{

	List<Publisher> findByNameContains(String keyword);
	
}
