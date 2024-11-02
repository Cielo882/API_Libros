package com.example.demo.controller;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.models.entity.Libro;

import com.example.demo.models.services.LibroService;

@Component
public class ConsolaApp implements CommandLineRunner {

    @Autowired
    private LibroService libroService;

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Buscar libro por título");
            System.out.println("2. Listar libros registrados");
            System.out.println("3. Listar autores registrados");
            System.out.println("4. Listar autores vivos en un determinado año");
            System.out.println("5. Listar libros por idioma");
            System.out.println("6. Salir");

            int option = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer

            switch (option) {
                case 1 -> {
                    System.out.print("Ingrese el título del libro: ");
                    String titulo = scanner.nextLine();
                    Libro libro = libroService.buscarYGuardarLibro(titulo);
                    if (libro != null) {
                        System.out.println("Libro encontrado y guardado: " + libro.getTitulo());
                    } else {
                        System.out.println("No se encontró ningún libro con ese título.");
                    }
                }
                case 2 -> libroService.listarLibros().forEach(libro -> System.out.println(libro.getTitulo()));
                case 3 -> libroService.listarAutores().forEach(autor -> System.out.println("Autor: " + autor));
                case 4 -> {
                    System.out.print("Ingrese el año: ");
                    int anio = scanner.nextInt();
                    libroService.obtenerAutoresVivosEnAnio(anio).forEach(libro -> System.out.println(libro.getAutor()));
                }
                case 5 -> {
                    System.out.print("Ingrese el idioma: ");
                    String idioma = scanner.nextLine();
                    libroService.obtenerLibrosPorIdioma(idioma).forEach(libro -> System.out.println(libro.getTitulo()));
                }
                case 6 -> exit = true;
                default -> System.out.println("Opción no válida.");
            }
        }
        scanner.close();
    }
}