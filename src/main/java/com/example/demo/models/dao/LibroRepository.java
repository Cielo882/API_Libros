package com.example.demo.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.models.entity.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long> {
   
	List<Libro> findByTituloContaining(String titulo);
    
	@Query("SELECT l FROM Libro l WHERE l.idioma = :idioma")
	List<Libro> findLibrosPorIdioma(@Param("idioma") String idioma);


   
	@Query("SELECT DISTINCT a.name FROM Author a JOIN a.libros l")
	List<String> findDistinctAutores();

    
   
 

}
