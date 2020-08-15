package com.sk.librarymanagmentapi.controler;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sk.librarymanagmentapi.model.Library;
import com.sk.librarymanagmentapi.service.LibraryService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/library")
@AllArgsConstructor
public class LibraryController {

	private final LibraryService libraryService;
	
	@PostMapping
	public Library createLibrary(@RequestBody Library library) {
		libraryService.createLibrary(library);
		return library;
	}
	
	@PutMapping("/{lib_id}")
	public Library updateLibrary(@PathVariable long lib_id,@RequestBody Library library) {
		libraryService.updateLibrary(lib_id,library);
		return library;
	}

	@GetMapping
	public List<Library> getAllLibrary() {
		return libraryService.getAllLibrary();
	}

	@GetMapping("/{id}")
	public Library getLibrary(@PathVariable Long id) {
		return libraryService.getLibrary(id);
	}

	@DeleteMapping("/{id}")
	public String deleteBook(@PathVariable Long id) {
		return libraryService.deleteBook(id);
	}
}
