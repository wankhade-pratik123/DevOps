package com.sk.librarymanagmentapi.controler;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sk.librarymanagmentapi.dto.BookDto;
import com.sk.librarymanagmentapi.model.Book;
import com.sk.librarymanagmentapi.service.BookService;

import lombok.AllArgsConstructor;



@RestController
@RequestMapping("/api/books")
@AllArgsConstructor
public class BookController {
	
	private final BookService bookService;
	
	@PostMapping
	public String createBook(@RequestBody BookDto bookDto) {
		bookService.createBook(bookDto);
		return "Book created successfully";
	}

	@GetMapping
	public List<Book> getAllBook() {
		return bookService.getAllBook();
	}

	@GetMapping("/{id}")
	public Book getBook(@PathVariable Long id) {
		return bookService.getBook(id);
	}

	@DeleteMapping("/{id}")
	public String deleteBook(@PathVariable Long id) {
		return bookService.deleteBook(id);
	}
	
	@GetMapping("/library/{libraryId}")
	public List<Book> getAllBookByLibraryId(@PathVariable Long libraryId) {
		return bookService.getAllBookByLibraryId(libraryId);
	}
	
}
