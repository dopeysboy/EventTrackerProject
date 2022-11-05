package com.skilldistillery.eventtracker.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PublisherTests {
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("EventTrackerJPA");
	private EntityManager em;
	private Publisher pub;
	
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
		pub = em.find(Publisher.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		pub = null;
	}

	@Test
	void test_Publisher_mapping() {
		String expected = "Mojang";
		String actual = pub.getName();
	}

	@Test
	void test_Publisher_Game_mapping() {
		String expected = "Minecraft";
		String actual = pub.getGames().get(0).getTitle();
		
		assertEquals(expected, actual);
	}
}
