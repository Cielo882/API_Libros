package com.example.demo.models.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import com.example.demo.models.dao.LibroRepository;

import com.example.demo.models.entity.Libro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Optional;

@Service
public class LibroService {

	 @Autowired
	    private LibroRepository libroRepository;

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

	    public List<Libro> obtenerLibrosPorIdioma(String idioma) {
	        return gutendexService.listarLibrosPorIdioma(idioma);
	    }
	    public List<Libro> obtenerAutoresVivosEnAnio(int anio) {
	        return gutendexService.listarAutoresVivosEnAnio(anio);
	    }

	    
	    public List<String> listarAutores() {
	        return libroRepository.findDistinctAutores();
	    }

}
