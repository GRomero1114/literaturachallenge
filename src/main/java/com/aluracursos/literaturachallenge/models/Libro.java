package com.aluracursos.literaturachallenge.models;

import jakarta.persistence.*;
import jdk.jfr.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
   @ManyToMany(fetch = FetchType.LAZY)//cascade = CascadeType.MERGE,
    @JoinTable(
            name = "libros_autores",
            joinColumns = @JoinColumn(name = "libro_id",referencedColumnName = "titulo"),
            inverseJoinColumns = @JoinColumn(name = "autores_id",referencedColumnName = "nombre")
    )
    private List<Autor> autor = new ArrayList<>();
    private Integer descargas;
    @Enumerated(EnumType.STRING)
    private Lenguaje lenguaje;
//    @Transient
//    Optional<String> lenguajesO;
    private List<String> autorNombre;

    //Setter


    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(List <Autor> autor) {
        this.autor=autor;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }

    public void setLenguajes(Lenguaje lenguajes) {
        this.lenguaje = lenguajes;
    }

    public void setAutorNombre(List<String> autorNombre) {
        this.autorNombre = autorNombre;
    }

    //Getter

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public List<Autor> getAutor() {
        return autor;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public Lenguaje getLenguaje() {
        return lenguaje;
    }

    public List<String> getAutorNombre() {
        return autorNombre;
    }

    //Constructor
    public Libro() {
    }

    public Libro(DataLibro dataLibro) {
       // this.id = dataLibro.id().longValue();
        this.titulo = dataLibro.titulo();
        this.autor = dataLibro.autores().stream().map(a -> new Autor(a)).toList();
        System.out.println("Agregando autores");
        autor.forEach(a-> System.out.println(a.getNombre()));
        this.autorNombre = autor.stream().map(a -> a.getNombre()).toList();
        this.descargas = dataLibro.descargas();
        this.lenguaje = Lenguaje.fromString( dataLibro.lenguajes().stream().findFirst().get());
    }
    //toString

    @Override
    public String toString() {
        return   "********LIBRO********\nTitulo='" + titulo + '\'' +
                ", Autor=" + autorNombre +
                ", Lenguaje='" + lenguaje + '\'' +
                ", Descargas=" + descargas+"\n*********************";
    }
}
