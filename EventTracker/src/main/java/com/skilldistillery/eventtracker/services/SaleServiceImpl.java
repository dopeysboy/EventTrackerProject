package com.skilldistillery.eventtracker.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.eventtracker.entities.Game;
import com.skilldistillery.eventtracker.entities.Sale;
import com.skilldistillery.eventtracker.repositories.GameRepository;
import com.skilldistillery.eventtracker.repositories.SaleRepository;

@Service
public class SaleServiceImpl implements SaleService{

	@Autowired
	private SaleRepository saleRepo;
	@Autowired
	private GameRepository gameRepo;
	
	@Override
	public List<Sale> findAllSales() {
		List<Sale> sales = saleRepo.findAll(); 
		return sales;
	}

	@Override
	public List<Sale> findSalesByKeyword(String keyword) {
		return saleRepo.findByNameContainsOrDescriptionContains(keyword);
	}

	@Override
	public Sale findSaleById(int id) {
		Optional<Sale> saleOpt = saleRepo.findById(id);
		
		if(saleOpt.isEmpty()) {
			return null;
		}
		
		return saleOpt.get();
	}

	@Override
	public List<Sale> findSalesByDiscountRange(double low, double high) {
		List<Sale> sales = saleRepo.findByPercentDiscountGreaterThanEqualAndPercentDiscountLessThanEqual(low, high);
		
		return sales;
	}

	@Override
	public Sale createSale(Sale sale) {
		if(sale.getDateStart() == null) {
			sale.setDateStart(LocalDateTime.now());
		}
		if(sale.getDateEnd() == null) {
			sale.setDateEnd(LocalDateTime.now().plusDays(7));
		}
		if(sale.getPercentDiscount() == null) {
			sale.setPercentDiscount(25.0);
		}
		
		sale = saleRepo.saveAndFlush(sale);
		return sale;
	}

	@Override
	public Sale updateSale(Sale sale, int oldId) {
		if(saleRepo.findById(oldId) == null) {
			return null;
		}
		
		if(sale.getDateStart() == null) {
			sale.setDateStart(LocalDateTime.now());
		}
		if(sale.getDateEnd() == null) {
			sale.setDateEnd(LocalDateTime.now().plusDays(7));
		}
		if(sale.getPercentDiscount() == null) {
			sale.setPercentDiscount(25.0);
		}
		
		sale.setId(oldId);
		sale = saleRepo.saveAndFlush(sale);
		return sale;
	}

	@Override
	public boolean deleteSale(int id) {
		Optional<Sale> saleOpt = saleRepo.findById(id);
		
		if(saleOpt.isEmpty()) {
			return false;
		}
		Sale sale = saleOpt.get();
		saleRepo.delete(sale);
		return true;
	}

	@Override
	public Sale addGameToSale(int gId, int sId) {
		Optional<Sale> saleOpt = saleRepo.findById(sId);
		Optional<Game> gameOpt = gameRepo.findById(gId);
		
		if(saleOpt.isEmpty() || gameOpt.isEmpty()) {
			return null;
		}
		
		Sale sale = saleOpt.get();
		Game game = gameOpt.get();
		
		sale.addGame(game);
		
		gameRepo.saveAndFlush(game);
		saleRepo.saveAndFlush(sale);
		
		return sale;
	}

	@Override
	public Sale removeGameFromSale(int gId, int sId) {
		Optional<Sale> saleOpt = saleRepo.findById(sId);
		Optional<Game> gameOpt = gameRepo.findById(gId);
		
		if(saleOpt.isEmpty() || gameOpt.isEmpty()) {
			return null;
		}
		
		Sale sale = saleOpt.get();
		Game game = gameOpt.get();
		
		sale.removeGame(game);
		
		gameRepo.saveAndFlush(game);
		saleRepo.saveAndFlush(sale);
		
		return sale;
	}
	
	
}
