package com.skilldistillery.eventtracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.eventtracker.entities.Publisher;
import com.skilldistillery.eventtracker.repositories.PublisherRepository;

@Service
public class PublisherServiceImpl implements PublisherService{
	@Autowired
	private PublisherRepository pubRepo;
	
	@Override
	public List<Publisher> findAllPublishers() {
		List<Publisher> publishers = pubRepo.findAll();
		return publishers;
	}

	@Override
	public Publisher findPublisherById(int id) {
		Optional<Publisher> pubOpt = pubRepo.findById(id);
		
		if(pubOpt.isEmpty()) {
			return null;
		}
		
		return pubOpt.get();
	}

	@Override
	public List<Publisher> findPublishersByKeyword(String keyword) {
		List<Publisher> publishers = pubRepo.findByNameContains(keyword);
		
		return publishers;
	}

	@Override
	public Publisher updatePublisher(Publisher publisher, int oldId) {
		Optional<Publisher> pubOpt = pubRepo.findById(oldId);
		
		if(pubOpt.isEmpty() || publisher == null || publisher.getName() == null) {
			return null;
		}
		
		publisher.setId(oldId);
		
		pubRepo.saveAndFlush(publisher);
		return publisher;
	}

	@Override
	public Publisher createPublisher(Publisher publisher) {
		if(publisher == null || publisher.getName() == null) {
			return null;
		}
		
		return pubRepo.saveAndFlush(publisher);
	}

	@Override
	public boolean deletePublisher(int id) {
		Optional<Publisher> pubOpt = pubRepo.findById(id);
		
		if(pubOpt.isEmpty()) {
			return false;
		}
		
		Publisher publisher = pubOpt.get();
		pubRepo.delete(publisher);
		return true;
	}

}
