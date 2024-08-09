package com.start.blog.service;

import java.util.List;

import com.start.blog.payload.CategoryDto;

public interface CategoryService {
	
	CategoryDto createCategory(CategoryDto categoryDto);
	CategoryDto updateCategory(CategoryDto categoryDto,Integer id);
	List<CategoryDto> getAllCategory();
	CategoryDto getCategoryById(Integer id);
	void deleteCategory(Integer id);
	
	

}
