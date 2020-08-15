package com.sk.librarymanagmentapi.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Library {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private long id;
	private String name;
	private String description;
	private String city;

	@OneToMany(mappedBy = "library")
	@JsonManagedReference
	private List<Book> books;
}
