package com.example.demo.controller;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.models.entity.Author;
import com.example.demo.models.entity.Libro;
import com.example.demo.models.services.GutendexService;
import com.example.demo.models.services.LibroService;

@Component
public class ConsolaApp implements CommandLineRunner {

    @Autowired
    private LibroService libroService;
    
    @Autowired
    private GutendexService gutendexService;

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
            System.out.println("5. Listar libros por idioma (es: español, en: inglés, fr: francés, pt: portugués)");
            System.out.println("6. Salir");

            try {
                int option = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer

                switch (option) {
                    case 1 -> {
                        System.out.print("Ingrese el título del libro: ");
                        String titulo = scanner.nextLine();
                        try {
                            Libro libro = libroService.buscarYGuardarLibro(titulo);
                            if (libro != null) {
                                System.out.println("Libro encontrado y guardado: " + libro.getTitulo());
                            } else {
                                System.out.println("No se encontró ningún libro con ese título.");
                            }
                        } catch (Exception e) {
                            System.out.println("Error al buscar y guardar el libro: " + e.getMessage());
                        }
                    }
                    case 2 -> {
                        try {
                            libroService.listarLibros().forEach(libro -> System.out.println(libro.getTitulo()));
                        } catch (Exception e) {
                            System.out.println("Error al listar los libros: " + e.getMessage());
                        }
                    }
                    case 3 -> {
                        try {
                            List<Author> autores = libroService.listarAutores();
                            List<String> autoresFormateados = gutendexService.formatearAutores(autores);
                            autoresFormateados.forEach(System.out::println);
                        } catch (Exception e) {
                            System.out.println("Error al listar los autores: " + e.getMessage());
                        }
                    }
                    case 4 -> {
                        System.out.print("Ingrese el año: ");
                        try {
                            int year = scanner.nextInt();
                            List<Author> aliveAuthors = libroService.findAliveAuthorsInYear(year);
                            if (!aliveAuthors.isEmpty()) {
                                for (Author author : aliveAuthors) {
                                    System.out.println("Autor vivo: " + author.getName());
                                }
                            } else {
                                System.out.println("No se encontraron autores vivos en ese año.");
                            }
                        } catch (Exception e) {
                            System.out.println("Error: Ingrese un valor numérico válido para el año.");
                            scanner.nextLine(); // Limpiar el buffer si hay un error
                        }
                    }
                    case 5 -> {
                        System.out.print("Ingrese el código de idioma (es, en, fr, pt): ");
                        String idiomaInput = scanner.nextLine();
                        try {
                            libroService.obtenerLibrosPorIdioma(idiomaInput).forEach(libro -> System.out.println("Título: " + libro.getTitulo()));
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error: " + e.getMessage());
                        } catch (Exception e) {
                            System.out.println("Error al listar los libros por idioma: " + e.getMessage());
                        }
                    }
                    case 6 -> exit = true;
                    default -> System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                System.out.println("Error: Ingrese un número válido para seleccionar una opción.");
                scanner.nextLine(); // Limpiar el buffer si la entrada es incorrecta
            }
        }
        scanner.close();
    }
}