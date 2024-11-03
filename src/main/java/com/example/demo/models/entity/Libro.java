package com.example.demo.models.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
//import lombok.Data;
import jakarta.persistence.ManyToOne;

@Entity
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

   
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = true)  // Añade la columna author_id a la tabla libro
    private Author author;  // Cambia List<Author> a Author, ya que estamos usando ManyToOne

    
   
	public Author getAuthor() {
		return author;
	}



	public void setAuthor(Author author) {
		this.author = author;
	}
	private String idioma;
   


    public Libro() {}

   
   
    public Long getId() { return id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }


    public String getIdioma() { return idioma; }
    public void setIdioma(String idioma) { this.idioma = idioma; }

  
}