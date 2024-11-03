# Gutendex Console Application

Esta es una aplicación de consola desarrollada en **Java** usando **Spring Boot**. La aplicación permite buscar y almacenar información de libros y autores utilizando la API de Gutendex, y almacena los datos en una base de datos SQL Server. El usuario puede interactuar con la aplicación para buscar libros, listar libros y autores, y realizar búsquedas específicas en función de diferentes criterios.

## Características

- **Buscar libro por título**: Consulta la API de Gutendex para obtener un libro por título y lo almacena en la base de datos si no existe.
- **Listar libros registrados**: Muestra todos los libros almacenados en la base de datos.
- **Listar autores registrados**: Muestra todos los autores almacenados en la base de datos.
- **Listar autores vivos en un año específico**: Muestra los autores que estaban vivos en un año determinado.
- **Listar libros por idioma**: Filtra y muestra los libros en la base de datos de acuerdo al idioma especificado (códigos disponibles: `es`, `en`, `fr`, `pt`).
### Ejemplo de interacción

- El usuario selecciona la opción `1` e ingresa "Pride and Prejudice".
- La aplicación consulta la API de Gutendex y muestra: `"Libro encontrado y guardado: Pride and Prejudice"`.
- Luego, el usuario selecciona la opción `2` y la aplicación muestra todos los títulos de los libros registrados en la base de datos.

## Estructura del proyecto

- **Libro** y **Author**: Clases que representan las entidades de libro y autor.
- **LibroService** y **GutendexService**: Servicios para la lógica de negocio y la interacción con la API de Gutendex.
- **ConsolaApp**: Controlador de la aplicación de consola que gestiona la interacción con el usuario.
- **application.properties**: Archivo de configuración para la conexión a la base de datos y otros ajustes de Spring Boot.
## Requisitos previos

- **Java 17 o superior**
- **Spring Boot 3.x**
- **SQL Server** para la base de datos
- Conexión a Internet para consultar la API de Gutendex

## Configuración

1. **Clonar el repositorio**:

   ```bash
   git clone https://github.com/tu-usuario/nombre-repositorio.git
   cd nombre-repositorio
