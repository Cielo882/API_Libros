package com.example.demo.models.services;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.demo.models.dao.AuthorRepository;
import com.example.demo.models.dao.LibroRepository;
import com.example.demo.models.entity.Author;
import com.example.demo.models.entity.Libro;


@Service
public class LibroService {

	 @Autowired
	    private LibroRepository libroRepository;
	 @Autowired
	    private AuthorRepository authorRepository;

	    @Autowired
	    private GutendexService gutendexService;

	    public Libro buscarYGuardarLibro(String titulo) {
	        List<Libro> librosExistentes = libroRepository.findByTituloContaining(titulo);
	        if (!librosExistentes.isEmpty()) {
	            return librosExistentes.get(0);  // Ya existe en la BD
	        }

	        Libro libro = gutendexService.buscarLibroPorTitulo(titulo);
	        if (libro != null) {
	            libroRepository.save(libro);
	        }
	        return libro;
	    }

	    public List<Libro> listarLibros() {
	        return libroRepository.findAll();
	    }

	    public List<Libro> obtenerLibrosPorIdioma(String idiomaInput) {
	        // Mapeo de los códigos de idioma
	        Map<String, String> idiomaMap = Map.of(
	            "es", "es",
	            "en", "en",
	            "fr", "fr",
	            "pt", "pt"
	        );

	        // Verificar si el idioma ingresado es válido
	        String idioma = idiomaMap.get(idiomaInput);
	        if (idioma == null) {
	            throw new IllegalArgumentException("Idioma no válido. Use 'es' para español, 'en' para inglés, 'fr' para francés o 'pt' para portugués.");
	        }

	        return libroRepository.findLibrosPorIdioma(idioma);
	    }
	    
	    public List<Author> findAliveAuthorsInYear(Integer year) {
	        return authorRepository.findByDeathYearIsNullAndBirthYearLessThanEqual(year);
	    }
	    
	    public List<Author> listarAutores() {
	        return authorRepository.findAll();
	    }

}
