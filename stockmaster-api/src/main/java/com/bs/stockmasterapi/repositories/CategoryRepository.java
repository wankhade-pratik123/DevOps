package com.bs.stockmasterapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bs.stockmasterapi.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
