package com.bs.stockmasterapi.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bs.stockmasterapi.exceptions.CategoryException;
import com.bs.stockmasterapi.model.Category;
import com.bs.stockmasterapi.repositories.CategoryRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryService {

	private final CategoryRepository categoryRepository;

	public void createCategory(Category category) {
		categoryRepository.save(category);
	}

	public List<Category> getAllCategory() {
		return categoryRepository.findAll();
	}

	public Category getCategory(Long id) {
		return categoryRepository.findById(id).orElseThrow(() -> new CategoryException("No category found with id: "+id.toString()));
	}

	public String deleteCategory(Long id) {
		categoryRepository.delete(getCategory(id));
		return "Category deleted sucessfully";
	}
	
}
