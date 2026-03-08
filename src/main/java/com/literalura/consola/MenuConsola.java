
package com.literalura.consola;

import com.literalura.dto.DatosAutorApi;
import com.literalura.dto.DatosLibroApi;
import com.literalura.dto.RespuestaGutendex;
import com.literalura.modelo.Autor;
import com.literalura.modelo.Libro;
import com.literalura.repositorio.AutorRepository;
import com.literalura.repositorio.LibroRepository;
import com.literalura.servicio.ConsumoAPI;
import com.literalura.servicio.ConvierteDatos;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class MenuConsola {
    private final Scanner scanner = new Scanner(System.in);
    private final ConsumoAPI consumoAPI;
    private final ConvierteDatos conversor;
    private final LibroRepository libroRepo;
    private final AutorRepository autorRepo;

    public MenuConsola(ConsumoAPI consumoAPI, ConvierteDatos conversor,
                       LibroRepository libroRepo, AutorRepository autorRepo) {
        this.consumoAPI = consumoAPI;
        this.conversor = conversor;
        this.libroRepo = libroRepo;
        this.autorRepo = autorRepo;
    }

    public void mostrarMenu() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n========================================");
            System.out.println("       Bienvenido a LiterAlura          ");
            System.out.println("========================================");
            System.out.println("1- Buscar libro por titulo");
            System.out.println("2- Listar libros registrados");
            System.out.println("3- Listar autores registrados");
            System.out.println("4- Listar autores vivos en un determinado anio");
            System.out.println("5- Listar libros por idioma");
            System.out.println("0- Salir");
            System.out.print("\nElija una opcion: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Opcion invalida.");
                continue;
            }

            if (opcion == 1) {
                buscarLibro();
            } else if (opcion == 2) {
                listarLibros();
            } else if (opcion == 3) {
                listarAutores();
            } else if (opcion == 4) {
                listarAutoresVivos();
            } else if (opcion == 5) {
                listarLibrosPorIdioma();
            } else if (opcion == 0) {
                System.out.println("Saliendo...");
            } else {
                System.out.println("Opcion no reconocida.");
            }
        }
    }

    private void buscarLibro() {
        System.out.print("Ingrese el titulo: ");
        String titulo = scanner.nextLine();
        String json = consumoAPI.obtenerDatos(titulo);
        RespuestaGutendex respuesta = conversor.obtenerRespuesta(json);

        if (respuesta.getResults() == null || respuesta.getResults().isEmpty()) {
            System.out.println("Libro no encontrado.");
            return;
        }

        DatosLibroApi datosLibro = respuesta.getResults().get(0);

        Optional<Libro> libroExistente = libroRepo.findByTituloIgnoreCase(datosLibro.getTitle());
        if (libroExistente.isPresent()) {
            System.out.println("El libro ya esta registrado en la base de datos.");
            return;
        }

        if (datosLibro.getAuthors() == null || datosLibro.getAuthors().isEmpty()) {
            System.out.println("El libro no tiene autor registrado.");
            return;
        }

        DatosAutorApi datosAutor = datosLibro.getAuthors().get(0);
        Optional<Autor> autorExistente = autorRepo.findByNombreIgnoreCase(datosAutor.getName());
        Autor autor;
        if (autorExistente.isPresent()) {
            autor = autorExistente.get();
        } else {
            autor = autorRepo.save(new Autor(datosAutor.getName(), datosAutor.getBirthYear(), datosAutor.getDeathYear()));
        }

        String idioma = (datosLibro.getLanguages() != null && !datosLibro.getLanguages().isEmpty())
                ? datosLibro.getLanguages().get(0) : "desconocido";

        Libro libro = new Libro(datosLibro.getTitle(), idioma, datosLibro.getDownload_count(), autor);
        libroRepo.save(libro);
        System.out.println(libro);
    }

    private void listarLibros() {
        List<Libro> libros = libroRepo.findAll();
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
        } else {
            libros.forEach(System.out::println);
        }
    }

    private void listarAutores() {
        List<Autor> autores = autorRepo.findAll();
        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados.");
        } else {
            autores.forEach(System.out::println);
        }
    }

    private void listarAutoresVivos() {
        System.out.print("Ingrese el anio: ");
        try {
            int anio = Integer.parseInt(scanner.nextLine());
            List<Autor> autores = autorRepo.autoresVivosEnDeterminadoAnio(anio);
            if (autores.isEmpty()) {
                System.out.println("No se encontraron autores vivos en ese anio.");
            } else {
                autores.forEach(System.out::println);
            }
        } catch (NumberFormatException e) {
            System.out.println("Anio invalido.");
        }
    }

    private void listarLibrosPorIdioma() {
        System.out.println("Idiomas disponibles: es, en, fr, pt");
        System.out.print("Ingrese idioma: ");
        String idioma = scanner.nextLine().toLowerCase().trim();
        List<Libro> libros = libroRepo.findByIdioma(idioma);
        if (libros.isEmpty()) {
            System.out.println("No hay libros en ese idioma.");
        } else {
            libros.forEach(System.out::println);
        }
    }
}
