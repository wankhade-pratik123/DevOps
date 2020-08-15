package com.sk.librarymanagmentapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {

	private long id;
	private String name;
	private String description;
	private String author;
	private long libraryId;
}
