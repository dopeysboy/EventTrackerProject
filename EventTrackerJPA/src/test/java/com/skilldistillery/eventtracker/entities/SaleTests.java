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

class SaleTests {
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("EventTrackerJPA");
	private EntityManager em;
	private Sale sale;
	
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
		sale = em.find(Sale.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		sale = null;
	}

	@Test
	void test_Sale_mapping() {
		String expected = "Everything must go sale!";
		String actual = sale.getName();
		
		assertEquals(expected, actual);
	}

	@Test
	void test_Sale_Game_mapping() {
		int expected = 14;
		int actual = sale.getGames().size();
		
		assertEquals(expected, actual);
	}
}
