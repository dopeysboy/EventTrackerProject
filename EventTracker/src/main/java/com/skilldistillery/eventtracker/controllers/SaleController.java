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

import com.skilldistillery.eventtracker.entities.Sale;
import com.skilldistillery.eventtracker.services.SaleService;

@RestController
@RequestMapping("api")
public class SaleController {

	@Autowired
	private SaleService saleServ;
	
	@GetMapping("sales")
	public List<Sale> getAllSales(){
		return saleServ.findAllSales();
	}
	
	@GetMapping("sales/id/{id}")
	public Sale getSaleById(@PathVariable int id, HttpServletResponse resp) {
		Sale sale = saleServ.findSaleById(id);
		
		if(sale == null) {
			resp.setStatus(404);
		}
		
		return sale;
	}
	
	@GetMapping("sales/{keyword}")
	public List<Sale> getSalesByKeyword(@PathVariable String keyword){ 
		List<Sale> sales = saleServ.findSalesByKeyword(keyword);
		
		return sales;
	}
	
	@GetMapping("sales/discount/{low}/{high}")
	public List<Sale> getSalesByPercentDiscountRange(@PathVariable double low,
													@PathVariable double high){
		List<Sale> sales = saleServ.findSalesByDiscountRange(low, high);
		
		return sales;
	}
	
	@PostMapping("sales")
	public Sale createSale(@RequestBody Sale sale, HttpServletResponse resp, HttpServletRequest req) {
		sale = saleServ.createSale(sale);
		
		if(sale == null) {
			resp.setStatus(404);
		} else {
			resp.setStatus(201);
			
			StringBuffer url = req.getRequestURL();
			url.append("/id/").append(sale.getId());
			
			resp.setHeader("Location", url.toString());
		}
		
		return sale;
	}
	
	@PutMapping("sales/id/{id}")
	public Sale updateSale(@RequestBody Sale sale, @PathVariable int id,
							HttpServletRequest req, HttpServletResponse resp) {
		
		if(saleServ.findSaleById(id) == null) {
			resp.setStatus(404);
			return null;
		}
		
		sale = saleServ.updateSale(sale, id);
		
		if(sale == null) {
			resp.setStatus(400);
		} else {
			resp.setHeader("Location", req.getRequestURL().toString());
		}
		
		return sale;
	}
	
	@DeleteMapping("sales/id/{id}")
	public void deleteSale(@PathVariable int id, HttpServletResponse resp) {
		if(saleServ.findSaleById(id) == null) {
			resp.setStatus(404);
			return;
		}
		
		boolean successful = saleServ.deleteSale(id);
		if(!successful) {
			resp.setStatus(400);
		}
	}
	
	@PutMapping("sales/id/{sId}/add/game/{gId}")
	public Sale addGameToSale(@PathVariable int sId,
							@PathVariable int gId,
							HttpServletResponse resp,
							HttpServletRequest req) {
		
		Sale sale = saleServ.addGameToSale(gId, sId);
		
		if(sale == null) {
			resp.setStatus(400);
		}
		
		return sale;
	}
	
	@PutMapping("sales/id/{sId}/remove/game/{gId}")
	public Sale removeGameFromSale(@PathVariable int sId,
			@PathVariable int gId,
			HttpServletResponse resp,
			HttpServletRequest req) {
		
		Sale sale = saleServ.removeGameFromSale(gId, sId);
		
		if(sale == null) {
			resp.setStatus(400);
		}
		
		return sale;
	}
}
