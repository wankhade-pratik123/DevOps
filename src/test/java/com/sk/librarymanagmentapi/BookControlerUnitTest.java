package com.sk.librarymanagmentapi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sk.librarymanagmentapi.controler.BookController;
import com.sk.librarymanagmentapi.dto.BookDto;
import com.sk.librarymanagmentapi.model.Book;
import com.sk.librarymanagmentapi.service.BookService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BookControlerUnitTest {

	@Autowired
	private BookController bookController;
	@MockBean
	private BookService bookService;

	private List<Book> books;

	@Test
	public void createBookTest() {
		BookDto bookDto = BookDto.builder().name("Book 1").author("Pratik 1").description("Demo Desc 1").libraryId(1)
				.build();

		doNothing().when(bookService).createBook(bookDto);

		String expected = bookController.createBook(bookDto);

		assertThat(expected).isEqualTo("Book created successfully");
		verify(bookService).createBook(bookDto);
	}

	@Test
	public void getAllBookTest() {
		Book book1 = Book.builder().name("Book 1").author("Pratik 1").description("Demo Desc 1").build();
		Book book2 = Book.builder().name("Book 2").author("Pratik 2").description("Demo Desc 2").build();
		Book book3 = Book.builder().name("Book 3").author("Pratik 3").description("Demo Desc 3").build();
		books = new ArrayList<Book>();
		books.add(book1);
		books.add(book2);
		books.add(book3);

		when(bookService.getAllBook()).thenReturn(books);

		assertThat(bookController.getAllBook()).isNotEmpty();

		verify(bookService).getAllBook();
	}

	@Test
	public void getBookTest() {
		Book book = Book.builder().id(1).name("Book 1").author("Pratik 1").description("Demo Desc 1").build();

		when(bookService.getBook(1L)).thenReturn(book);

		assertThat(bookController.getBook(1L)).isEqualTo(book);

		verify(bookService).getBook(1L);
	}

	@Test
	public void deleteBookTest() {

		when(bookService.deleteBook(1L)).thenReturn("Book deleted sucessfully");

		assertThat(bookController.deleteBook(1L)).isEqualTo("Book deleted sucessfully");

		verify(bookService).deleteBook(1L);
	}

}
