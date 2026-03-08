
package com.literalura.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    private String idioma;
    private Double descargas;

    @ManyToOne
    private Autor autor;

    public Libro() {}
    public Libro(String titulo, String idioma, Double descargas, Autor autor) {
        this.titulo = titulo;
        this.idioma = idioma;
        this.descargas = descargas;
        this.autor = autor;
    }

    public String getTitulo() { return titulo; }
    public String getIdioma() { return idioma; }
    public Double getDescargas() { return descargas; }
    public Autor getAutor() { return autor; }

    @Override
    public String toString() {
        String nombreAutor = (autor != null) ? autor.getNombre() : "Desconocido";
        return "\n----- LIBRO -----" +
               "\nTitulo: " + titulo +
               "\nAutor: " + nombreAutor +
               "\nIdioma: " + idioma +
               "\nDescargas: " + descargas +
               "\n-----------------";
    }
}
