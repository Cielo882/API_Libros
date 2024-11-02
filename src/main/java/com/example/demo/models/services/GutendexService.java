package com.example.demo.models.services;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.demo.models.entity.Libro;
import com.fasterxml.jackson.databind.JsonNode;

@Service
public class GutendexService {

	 private final RestTemplate restTemplate;

	    @Autowired
	    public GutendexService(RestTemplateBuilder builder) {
	        this.restTemplate = builder.build();
	    }

	    public Libro buscarLibroPorTitulo(String titulo) {
	        String url = "https://gutendex.com/books?search=" + URLEncoder.encode(titulo, StandardCharsets.UTF_8);
	        ResponseEntity<JsonNode> response = restTemplate.getForEntity(url, JsonNode.class);

	        JsonNode data = response.getBody().get("results");
	        if (data != null && data.size() > 0) {
	            JsonNode libroData = data.get(0);  // Tomamos el primer resultado, puedes ajustarlo para manejar varios
	            Libro libro = new Libro();
	            libro.setTitulo(libroData.get("title").asText());

	            // Obtener autores
	            if (libroData.has("authors") && libroData.get("authors").size() > 0) {
	                libro.setAutor(libroData.get("authors").get(0).get("name").asText());
	            }
	            libro.setIdioma(libroData.get("languages").get(0).asText());
	            
	            return libro;
	        } else {
	            return null;  // No se encontró el libro
	        }
	    }
	    
	    public List<Libro> listarAutoresVivosEnAnio(int anio) {
	        String url = "https://gutendex.com/books?author_year_start=" + anio + "&author_year_end=" + anio;
	        ResponseEntity<JsonNode> response = restTemplate.getForEntity(url, JsonNode.class);

	        List<Libro> libros = new ArrayList<>();
	        JsonNode data = response.getBody().get("results");
	        if (data != null) {
	            for (JsonNode libroData : data) {
	                Libro libro = new Libro();
	                libro.setTitulo(libroData.get("title").asText());
	                libro.setAutor(libroData.get("authors").get(0).get("name").asText());
	                libro.setIdioma(libroData.get("languages").get(0).asText());
	                libros.add(libro);
	            }
	        }
	        return libros;
	    }


	    public List<Libro> listarLibrosPorIdioma(String idioma) {
	        String url = "https://gutendex.com/books?languages=" + idioma;
	        ResponseEntity<JsonNode> response = restTemplate.getForEntity(url, JsonNode.class);

	        List<Libro> libros = new ArrayList<>();
	        JsonNode data = response.getBody().get("results");
	        if (data != null) {
	            for (JsonNode libroData : data) {
	                Libro libro = new Libro();
	                libro.setTitulo(libroData.get("title").asText());
	                libro.setAutor(libroData.get("authors").get(0).get("name").asText());
	                libro.setIdioma(libroData.get("languages").get(0).asText());
	                libros.add(libro);
	            }
	        }
	        return libros;
	    }

}
