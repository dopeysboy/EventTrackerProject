package com.skilldistillery.eventtracker.services;

import java.time.LocalDateTime;
import java.util.List;

import com.skilldistillery.eventtracker.entities.Sale;

public interface SaleService {

	List<Sale> findAllSales();
	
	List<Sale> findSalesByKeyword(String keyword);
	
	Sale findSaleById(int id);
	
	List<Sale> findSalesByDiscountRange(double low, double high);
	
	Sale createSale(Sale sale);
	
	Sale updateSale(Sale sale, int oldId);
	
	boolean deleteSale(int id);
	
	Sale addGameToSale(int gId, int sId);
	
	Sale removeGameFromSale(int gId, int sId);
	
	List<Sale> findSalesDuringLDT(LocalDateTime dateTimeToCheck);
}
