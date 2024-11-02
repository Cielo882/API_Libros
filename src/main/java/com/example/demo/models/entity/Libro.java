package com.example.demo.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
//import lombok.Data;
import jakarta.persistence.ManyToOne;


@Entity

public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Libro() {}

   

    public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getIdioma() {
		return idioma;
	}
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	public Integer getAnioPublicacion() {
		return anioPublicacion;
	}
	public void setAnioPublicacion(Integer anioPublicacion) {
		this.anioPublicacion = anioPublicacion;
	}
	public Boolean getVivoEnAnio() {
		return vivoEnAnio;
	}
	public void setVivoEnAnio(Boolean vivoEnAnio) {
		this.vivoEnAnio = vivoEnAnio;
	}
	

	private String titulo;
    public String getAutor() {
		return autor;
	}



	public void setAutor(String autor) {
		this.autor = autor;
	}


	private String autor;
    private String idioma;
    private Integer anioPublicacion;
    private Boolean vivoEnAnio;

   

    


}
