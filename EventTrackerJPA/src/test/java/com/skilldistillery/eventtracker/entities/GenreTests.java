package com.skilldistillery.eventtracker.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GenreTests {
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("EventTrackerJPA");
	private EntityManager em;
	private Genre genre;
	
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
		genre = em.find(Genre.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		genre = null;
	}

	@Test
	void test_Genre_mapping() {
		String expected = "Simulator";
		String actual = genre.getName();
		
		assertEquals(expected, actual);
	}

	@Test
	void test_Genre_Game_mapping() {
		int expected = 7;
		int actual = genre.getGames().size();
		
		assertEquals(expected, actual);
	}
}
