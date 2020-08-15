package com.sk.librarymanagmentapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sk.librarymanagmentapi.dto.BookDto;
import com.sk.librarymanagmentapi.exception.BookException;
import com.sk.librarymanagmentapi.model.Book;
import com.sk.librarymanagmentapi.repository.BookRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookService {

	private final BookRepository bookRepository;
	private final LibraryService libraryService;

	public void createBook(BookDto bookDto) {
		try {
			Book book = new Book();  
			book.setName(bookDto.getName());
			book.setDescription(bookDto.getDescription());
			book.setAuthor(bookDto.getAuthor());
			book.setLibrary(libraryService.getLibrary(bookDto.getLibraryId()));
			bookRepository.save(book);	
		} catch (Exception e) {
			throw new BookException("Error while creating Book"); 
		}
	}

	public List<Book> getAllBook() {
		return bookRepository.findAll();
	}

	public Book getBook(Long id) {
		return bookRepository.findById(id)
				.orElseThrow(() -> new BookException("No Book found with id: " + id.toString()));
	}

	public String deleteBook(Long id) {
		bookRepository.delete(getBook(id));
		return "Book deleted sucessfully";
	}

	public List<Book> getAllBookByLibraryId(Long libraryId) {
		return bookRepository.findByLibrary(libraryService.getLibrary(libraryId));
	}
}
