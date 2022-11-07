package com.skilldistillery.eventtracker.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.eventtracker.entities.Publisher;
import com.skilldistillery.eventtracker.services.PublisherService;

@RestController
@RequestMapping("api")
public class PublisherController {

	@Autowired
	private PublisherService pubServ;
	
	@GetMapping("publishers")
	public List<Publisher> getAllPublishers(){
		return pubServ.findAllPublishers();
	}
	
	@GetMapping("publishers/id/{id}")
	public Publisher getPublisherById(@PathVariable int id, HttpServletResponse resp) {
		Publisher pub = pubServ.findPublisherById(id);
		
		if(pub == null) {
			resp.setStatus(404);
		}
		
		return pub;
	}
	
	@GetMapping("publishers/{keyword}")
	public List<Publisher> getPublishersByKeyword(@PathVariable String keyword){
		List<Publisher> publishers = pubServ.findPublishersByKeyword(keyword);
		
		return publishers;
	}
	
	@PutMapping("publishers/id/{id}")
	public Publisher updatePublisher(@PathVariable int id,
									@RequestBody Publisher publisher,
									HttpServletResponse resp,
									HttpServletRequest req) {
		publisher = pubServ.updatePublisher(publisher, id);
		
		if(publisher == null) {
			resp.setStatus(400);
		}
		
		return publisher;
	}
	
	@PostMapping("publishers")
	public Publisher createPublisher(@RequestBody Publisher publisher, HttpServletRequest req, HttpServletResponse resp) {
		publisher = pubServ.createPublisher(publisher);
		
		if(publisher == null) {
			resp.setStatus(400);
		} else {
			resp.setStatus(201);
			
			StringBuffer url = req.getRequestURL();
			url.append("/id/").append(publisher.getId());
			
			resp.setHeader("Location", url.toString());
		}
		
		return publisher;
	}
	
	@DeleteMapping("publishers/id/{id}")
	public void deletePublisher(@PathVariable int id, HttpServletResponse resp) {
		Publisher pub = pubServ.findPublisherById(id);
		if(pub == null) {
			resp.setStatus(404);
			return;
		}
		
		pubServ.deletePublisher(id);
	}
}
