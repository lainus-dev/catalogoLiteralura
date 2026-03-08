
package com.literalura.modelo;

import jakarta.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nombre;
    private Integer nacimiento;
    private Integer fallecimiento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros;

    public Autor() {}
    public Autor(String nombre, Integer nacimiento, Integer fallecimiento) {
        this.nombre = nombre;
        this.nacimiento = nacimiento;
        this.fallecimiento = fallecimiento;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public Integer getNacimiento() { return nacimiento; }
    public Integer getFallecimiento() { return fallecimiento; }
    public List<Libro> getLibros() { return libros; }

    @Override
    public String toString() {
        String fin = (fallecimiento != null) ? fallecimiento.toString() : "Presente";
        String titulosLibros = (libros != null && !libros.isEmpty())
            ? libros.stream().map(Libro::getTitulo).collect(Collectors.joining(", "))
            : "Sin libros registrados";
        return "\n----- AUTOR -----" +
               "\nAutor: " + nombre +
               "\nFecha de nacimiento: " + nacimiento +
               "\nFecha de fallecimiento: " + fin +
               "\nLibros: " + titulosLibros +
               "\n-----------------";
    }
}
