package com.skilldistillery.eventtracker.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.annotations.UpdateTimestamp;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameTests {
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("EventTrackerJPA");
	private EntityManager em;
	private Game game;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		game = em.find(Game.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		game = null;
	}

	@Test
	void test_Game_mapping() {
		String expected = "Minecraft";
		String actual = game.getTitle();
		
		assertEquals(expected, actual);
	}
	
	@Test
	void test_Game_Genre_mapping() {
		int expected = 5;
		int actual = game.getGenres().size();
		
		assertEquals(expected, actual);
	}

	@Test
	void test_Game_Publisher_mapping() {
		String expected = "Mojang";
		String actual = game.getPublishers().get(0).getName();
		
		assertEquals(expected, actual);
	}
	
	@Test
	void test_Game_Sale_mapping() {
		String expected = "Everything must go sale!";
		String actual = game.getSales().get(0).getName();
		
		assertEquals(expected, actual);
	}
}
