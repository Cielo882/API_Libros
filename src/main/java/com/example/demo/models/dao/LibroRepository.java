package com.example.demo.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.entity.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long> {
   
	List<Libro> findByTituloContaining(String titulo);
    //List<Libro> findByIdioma(String idioma);
    @Query("SELECT l FROM Libro l WHERE l.idioma = :idioma")
    List<Libro> findLibrosByIdioma(String idioma);

    List<Libro> findByAnioPublicacionAndVivoEnAnio(Integer anio, Boolean vivoEnAnio);
   
    @Query("SELECT DISTINCT l.autor FROM Libro l")
    List<String> findDistinctAutores();

}
