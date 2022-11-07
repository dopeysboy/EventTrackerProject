package com.skilldistillery.eventtracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.eventtracker.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Integer>{
	
	List<Sale> findByPercentDiscountGreaterThanEqualAndPercentDiscountLessThanEqual(double low, double high);
	
	List<Sale> findByNameContainsOrDescriptionContains(String keyword);
	 
}
