package com.bs.stockmasterapi.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bs.stockmasterapi.dto.StockDto;
import com.bs.stockmasterapi.exceptions.StockException;
import com.bs.stockmasterapi.mapper.StockMapper;
import com.bs.stockmasterapi.model.Stock;
import com.bs.stockmasterapi.repositories.StockRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StockService {

	private final StockRepository stockRepository;
	private final StockMapper stockMapper;

	public void createStock(StockDto stockDto) {
		stockRepository.save(stockMapper.mapToStockDto(stockDto));
	}

	public List<Stock> getAllStock() {
		return stockRepository.findAll();
	}

	public Stock getStock(Long id) {
		return stockRepository.findById(id)
				.orElseThrow(() -> new StockException("No Stock found with id: " + id.toString()));
	}

	public String deleteStock(Long id) {
		stockRepository.delete(getStock(id));
		return "Stock deleted sucessfully";
	}

	public void updateStockPrice(StockDto stockDto) { 
		Stock stock = getStock(stockDto.getId()); 
		stock.setDayBeforYesturdayClosing(stock.getYesturdayClosing());
		stock.setYesturdayClosing(stock.getTodayClosing());
		stock.setTodayClosing(stockDto.getTodayClosing());
		stockRepository.save(stock);
	}

}
