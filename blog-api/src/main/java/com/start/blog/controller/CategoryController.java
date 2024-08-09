package com.start.blog.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.start.blog.payload.ApiResponse;
import com.start.blog.payload.CategoryDto;
import com.start.blog.service.CategoryService;

@RestController
public class CategoryController {
	private CategoryService categoryservice;
	
	public CategoryController(CategoryService categoryservice) {
		super();
		this.categoryservice = categoryservice;
	}

	@PostMapping
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
		CategoryDto createCategory = categoryservice.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createCategory,HttpStatus.CREATED);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,@PathVariable Integer id){
		CategoryDto createCategory = categoryservice.updateCategory(categoryDto,id);
		return ResponseEntity.ok(createCategory);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer id){
		CategoryDto categoryDto = categoryservice.getCategoryById(id);
		return ResponseEntity.ok(categoryDto);
		
	}
	
	@GetMapping
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		List<CategoryDto> categoryDto = categoryservice.getAllCategory();
		return ResponseEntity.ok(categoryDto);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer id){
		categoryservice.deleteCategory(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted successfully",true),HttpStatus.OK);
	}

}
