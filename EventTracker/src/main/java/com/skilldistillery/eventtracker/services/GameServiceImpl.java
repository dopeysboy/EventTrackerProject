package com.skilldistillery.eventtracker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.eventtracker.repositories.GameRepository;

@Service
public class GameServiceImpl implements GameService{
	@Autowired
	private GameRepository gameRepo;
}
