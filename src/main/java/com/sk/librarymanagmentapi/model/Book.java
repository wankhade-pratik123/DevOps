package com.sk.librarymanagmentapi.model;

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
@Entity
@Builder
public class Book {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private long id;
	private String name;
	private String description;
	private String author;

	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "library_id")
	@JsonBackReference
    private Library library;
}
