package com.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.ApiResponse;
import com.blog.payloads.CategoryDTO;
import com.blog.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	//POST create
	@PostMapping("/")
	public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
		
		System.out.println("In createCategory");
		System.out.println("categoryDTO "+categoryDTO);
		CategoryDTO createdCategoryDTO=this.categoryService.createCategory(categoryDTO);
		return new ResponseEntity<CategoryDTO>(createdCategoryDTO,HttpStatus.CREATED);
		
	}
    
	//PUT update
	@PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable Integer categoryId){
			
		CategoryDTO updatedCategoryDTO=this.categoryService.updateCategory(categoryDTO, categoryId);
		return new ResponseEntity<CategoryDTO>(updatedCategoryDTO,HttpStatus.OK);
			
	}
	
	//DELETE delete
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId){
				
		  this.categoryService.deleteCategory(categoryId);
		  return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted successfully",true), HttpStatus.OK);	
    }
	
	//GET single category
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDTO> getCategory(@PathVariable Integer categoryId){
				
		CategoryDTO categoryDTO=this.categoryService.getCategoryById(categoryId);
		  return new ResponseEntity<CategoryDTO>(categoryDTO, HttpStatus.OK);	
    }
	
	//GET all category
	@GetMapping("/")
	public ResponseEntity<List<CategoryDTO>> getAllCategory(){
				
		List<CategoryDTO> categoryDTOList=this.categoryService.getAllCategories();
		return ResponseEntity.ok(categoryDTOList);
    }
}
