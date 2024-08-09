package com.start.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.start.blog.entity.Category;
import com.start.blog.exception.ResourceNotFoundException;
import com.start.blog.payload.CategoryDto;
import com.start.blog.repository.CategoryRepository;
import com.start.blog.service.CategoryService;

public class CategoryServiceImpl implements CategoryService{
	
	CategoryRepository categoryRepository;
	ModelMapper modelMapper;

	public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
		super();
		this.categoryRepository = categoryRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = categoryDtoToCategory(categoryDto);
		Category save = categoryRepository.save(category);
		CategoryDto categoryToDto = categoryToDto(save);
		return categoryToDto;
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer id) {
		Category category = categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("categoryId", "id", id));
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		Category save = categoryRepository.save(category);
		CategoryDto categoryToDto = categoryToDto(save);	
		return categoryToDto;
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> categories = categoryRepository.findAll();
		List<CategoryDto> categoriesDto = categories.stream().map(category->categoryToDto(category)).collect(Collectors.toList());
		return categoriesDto;
	}

	@Override
	public CategoryDto getCategoryById(Integer id) {
		Category category = categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("categoryId", "id", id));
		CategoryDto categoryToDto = categoryToDto(category);	
		return categoryToDto;
	}

	@Override
	public void deleteCategory(Integer id) {
		Category category = categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("categoryId", "id", id));
		categoryRepository.delete(category);
	}
	
	private CategoryDto categoryToDto(Category category) {
		CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
		return categoryDto;
		
	}
	
	private Category categoryDtoToCategory(CategoryDto categoryDto) {
		Category category= modelMapper.map(categoryDto, Category.class);
		return category;
		
	}

}
