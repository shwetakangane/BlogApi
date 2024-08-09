package com.start.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.start.blog.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
