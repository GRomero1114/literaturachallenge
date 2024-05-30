package com.aluracursos.literaturachallenge.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;

   @ManyToMany(fetch = FetchType.EAGER)//cascade = CascadeType.MERGE,
    @JoinTable(
            name = "libros_autores",
            joinColumns = @JoinColumn(name = "libro_id",referencedColumnName = "titulo"),
            inverseJoinColumns = @JoinColumn(name = "autores_id",referencedColumnName = "nombre")
    )
    private List<Autor> autor = new ArrayList<>();

    private Integer descargas;
    @Enumerated(EnumType.STRING)
    private Idioma idioma;
    private List<String> autorNombre;

    //Setter
    public void setId(Long id) { this.id = id; }

    public void setTitulo(String titulo) {this.titulo = titulo;}

    public void setAutor(List <Autor> autor) {
        this.autor=autor;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }

    public void setLenguajes(Idioma idiomas) {
        this.idioma = idiomas;
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

    public Idioma getLenguaje() {
        return idioma;
    }

    public List<String> getAutorNombre() {
        return autorNombre;
    }

    //Constructor
    public Libro() {
    }

    public Libro(DataLibro dataLibro) {
        this.titulo = dataLibro.titulo();
        this.autor = dataLibro.autores().stream().map(a -> new Autor(a)).toList();
        this.autorNombre = autor.stream().map(a -> a.getNombre()).toList();
        this.descargas = dataLibro.descargas();
        this.idioma = Idioma.fromString( dataLibro.idioma().stream().findFirst().get());
    }

    //toString
    @Override
    public String toString() {
        return   "********LIBRO********\nTitulo='" + titulo + '\n' +
                "Autor=" + autorNombre +
                "\nIdioma=" + idioma + '\n' +
                "Descargas=" + descargas+"\n*********************\n";
    }
}
