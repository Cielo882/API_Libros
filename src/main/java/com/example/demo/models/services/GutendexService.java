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

import com.example.demo.models.dao.AuthorRepository;
import com.example.demo.models.dao.LibroRepository;
import com.example.demo.models.entity.Author;
import com.example.demo.models.entity.Libro;
import com.fasterxml.jackson.databind.JsonNode;


@Service
public class GutendexService {

    private final RestTemplate restTemplate;
    private final LibroRepository libroRepository;
    private final AuthorRepository authorRepository;
    @Autowired
    public GutendexService(RestTemplate restTemplate, LibroRepository libroRepository, AuthorRepository authorRepository) {
        this.restTemplate = restTemplate;
        this.libroRepository = libroRepository;
        this.authorRepository = authorRepository; 
    }

    public Libro buscarLibroPorTitulo(String titulo) {
        String url = "https://gutendex.com/books?search=" + URLEncoder.encode(titulo, StandardCharsets.UTF_8);
        ResponseEntity<JsonNode> response = restTemplate.getForEntity(url, JsonNode.class);

        JsonNode data = response.getBody().get("results");
        if (data != null && data.size() > 0) {
            JsonNode libroData = data.get(0);  
            Libro libro = new Libro();
            libro.setTitulo(libroData.get("title").asText());

            if (libroData.has("authors") && libroData.get("authors").size() > 0) {
                JsonNode authorData = libroData.get("authors").get(0);  // Usa el primer autor
                String authorName = authorData.get("name").asText();
                Author author = authorRepository.findByName(authorName);

                if (author == null) {
                    author = new Author();
                    author.setName(authorName);
                    if (authorData.has("birth_year") && !authorData.get("birth_year").isNull()) {
                        author.setBirthYear(authorData.get("birth_year").asInt());
                    }
                    if (authorData.has("death_year") && !authorData.get("death_year").isNull()) {
                        author.setDeathYear(authorData.get("death_year").asInt());
                    }
                    authorRepository.save(author);
                }

                libro.setAuthor(author);  // Asigna el autor al libro
            }

            libro.setIdioma(libroData.get("languages").get(0).asText());
            libroRepository.save(libro);

            return libro;
        } else {
            return null;
        }
    }
    
    public List<String> formatearAutores(List<Author> autores) {
        List<String> resultados = new ArrayList<>();
        for (Author autor : autores) {
            StringBuilder sb = new StringBuilder();
            sb.append("\"name\": \"").append(autor.getName()).append("\", ");
            sb.append("\"birth_year\": ").append(autor.getBirthYear() != null ? autor.getBirthYear() : "null").append(", ");
            sb.append("\"death_year\": ").append(autor.getDeathYear() != null ? autor.getDeathYear() : "null");
            resultados.add(sb.toString());
        }
        return resultados;
    }

}