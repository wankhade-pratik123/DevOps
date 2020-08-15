package com.sk.librarymanagmentapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sk.librarymanagmentapi.model.Library;

@Repository
public interface LibraryRepositroy extends JpaRepository<Library, Long> {

}
