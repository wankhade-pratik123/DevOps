package com.bs.stockmasterapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bs.stockmasterapi.model.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
	
}
