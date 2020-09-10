package com.bs.stockmasterapi.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Stock {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private long id;
	private String name;
	private String symbol;
	
	private int todayClosing;
	private int yesturdayClosing;
	private int dayBeforYesturdayClosing;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
	@JsonBackReference
    private Category category;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sector_id")
	@JsonBackReference
    private Sector sector;
}
