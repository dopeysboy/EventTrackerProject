package com.skilldistillery.eventtracker.services;

import java.util.List;

import com.skilldistillery.eventtracker.entities.Publisher;

public interface PublisherService {

	List<Publisher> findAllPublishers();
	
	Publisher findPublisherById(int id);
	
	List<Publisher> findPublishersByKeyword(String keyword); 
	
	Publisher updatePublisher(Publisher publisher, int oldId);
	
	Publisher createPublisher(Publisher publisher);
	
	boolean deletePublisher(int id);
}
