package com.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.Category;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.CategoryDTO;
import com.blog.repo.CategoryRepo;
import com.blog.services.CategoryService;

@Service
public class CategoryServicelmp implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDTO createCategory(CategoryDTO categoryDTO) {
		
		Category category=this.modelMapper.map(categoryDTO, Category.class);
		Category savedCategory=this.categoryRepo.save(category);
		return this.modelMapper.map(savedCategory, CategoryDTO.class);
	}

	@Override
	public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId) {
		
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category", " categoryId ", categoryId));
		category.setCategoryTitle(categoryDTO.getCategoryTitle());
		category.setCategoryDes(categoryDTO.getCategoryDes());
		Category updatedCategory=this.categoryRepo.save(category);
		return this.modelMapper.map(updatedCategory, CategoryDTO.class);
	}

	@Override
	public CategoryDTO getCategoryById(Integer categoryId) {
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category", " categoryId ", categoryId));
		return this.modelMapper.map(category, CategoryDTO.class);
	}

	@Override
	public List<CategoryDTO> getAllCategories() {
		List<Category> categories=this.categoryRepo.findAll();
		List<CategoryDTO> categoriesDTO=categories.stream().map((cat) -> this.modelMapper.map(cat, CategoryDTO.class)).collect(Collectors.toList());
		return categoriesDTO;
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category", " categoryId ", categoryId));
		this.categoryRepo.delete(category);
		
	}

}
