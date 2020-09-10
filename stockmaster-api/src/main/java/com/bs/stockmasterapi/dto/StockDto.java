package com.bs.stockmasterapi.dto;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockDto {
	
	private long id;
	private String name;
	private String symbol;
	private int todayClosing;
	@Column(name="category_id")
	private long categoryId;
	@Column(name="sector_id")
	private long sectorId;
}
