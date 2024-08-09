package com.start.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.start.blog.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
