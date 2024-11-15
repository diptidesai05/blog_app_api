package com.blog.services;

import java.util.List;
import com.blog.payloads.CategoryDTO;

public interface CategoryService {
	
	CategoryDTO createCategory(CategoryDTO category);
	CategoryDTO updateCategory(CategoryDTO category, Integer categoryId);
	CategoryDTO getCategoryById(Integer categoryId);
	List<CategoryDTO> getAllCategories();
	void deleteCategory(Integer categoryId);

}
