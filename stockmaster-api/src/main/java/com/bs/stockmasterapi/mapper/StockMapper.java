package com.bs.stockmasterapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.bs.stockmasterapi.dto.StockDto;
import com.bs.stockmasterapi.model.Category;
import com.bs.stockmasterapi.model.Sector;
import com.bs.stockmasterapi.model.Stock;
import com.bs.stockmasterapi.services.CategoryService;
import com.bs.stockmasterapi.services.SectorService;

@Mapper(componentModel = "spring")
public abstract class StockMapper {

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private SectorService sectorService;
	
	@Mapping(target = "category", expression = "java(getCategory(stockDto))")
	@Mapping(target = "sector", expression = "java(getSector(stockDto))")
	public  abstract Stock mapToStockDto(StockDto stockDto);

	Category getCategory(StockDto stockDto) {
		return categoryService.getCategory(stockDto.getCategoryId());
	}
	
	Sector getSector(StockDto stockDto) {
		return sectorService.getSector(stockDto.getSectorId());
	}
}
