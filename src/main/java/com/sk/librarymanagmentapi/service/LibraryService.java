package com.sk.librarymanagmentapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sk.librarymanagmentapi.exception.LibraryException;
import com.sk.librarymanagmentapi.model.Library;
import com.sk.librarymanagmentapi.repository.LibraryRepositroy;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LibraryService {

	private final LibraryRepositroy libraryRepositroy;

	public void createLibrary(Library library) {
		libraryRepositroy.save(library);
	}
	
	public void updateLibrary(long lib_id,Library library) {
		library.setId(lib_id); 
		libraryRepositroy.save(library);
	}

	public List<Library> getAllLibrary() {
		return libraryRepositroy.findAll();
	}

	public Library getLibrary(Long id) {
		return libraryRepositroy.findById(id)
				.orElseThrow(() -> new LibraryException("No Library found with id: " + id.toString()));
	}

	public String deleteBook(Long id) {
		libraryRepositroy.delete(getLibrary(id));
		return "Library deleted sucessfully";
	}
}
