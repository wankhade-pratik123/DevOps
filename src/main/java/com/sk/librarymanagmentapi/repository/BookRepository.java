package com.sk.librarymanagmentapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sk.librarymanagmentapi.model.Book;
import com.sk.librarymanagmentapi.model.Library;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	List<Book> findByLibrary(Library library);
}
