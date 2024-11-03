package com.example.demo.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.entity.Author;

//AuthorRepository.java
public interface AuthorRepository extends JpaRepository<Author, Long> {
	
	@Query("SELECT a FROM Author a WHERE (a.deathYear IS NULL OR a.deathYear > :year) AND a.birthYear <= :year")
	List<Author> findByDeathYearIsNullAndBirthYearLessThanEqual(Integer year);

	Author findByName(String authorName);
}