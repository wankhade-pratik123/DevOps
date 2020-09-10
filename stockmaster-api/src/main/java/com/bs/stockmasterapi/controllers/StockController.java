package com.bs.stockmasterapi.controllers;

import static org.springframework.http.ResponseEntity.status;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bs.stockmasterapi.dto.StockDto;
import com.bs.stockmasterapi.model.Stock;
import com.bs.stockmasterapi.services.StockService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/stock")
@AllArgsConstructor
public class StockController {

	private final StockService stockService; 
	
	@PostMapping 
    public ResponseEntity<Void> createStock(@RequestBody StockDto stockDto) {
		stockService.createStock(stockDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
	
	@PostMapping("/updateStockPrice") 
    public ResponseEntity<Void> updateStockPrice(@RequestBody StockDto stockDto) {
		stockService.updateStockPrice(stockDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Stock>> getAllStock() {
        return status(HttpStatus.OK).body(stockService.getAllStock());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stock> getStock(@PathVariable Long id) {
        return status(HttpStatus.OK).body(stockService.getStock(id));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStock(@PathVariable Long id) {
        return status(HttpStatus.OK).body(stockService.deleteStock(id));
    }
}
