package com.bs.stockmasterapi.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private long id;
	private String name;
	private String description;
	private boolean active;
	
	@OneToMany(mappedBy = "category")
	@JsonManagedReference
	private List<Stock> stocks;
}
