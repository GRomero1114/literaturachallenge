package com.aluracursos.literaturachallenge.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer nacimiento;
    private Integer deceso;
    @ManyToMany(mappedBy = "autor")
    private List<Libro> libro= new ArrayList<>();

    //Getter
    public String getNombre() {
        return nombre;
    }

    public Integer getNacimiento() {
        return nacimiento;
    }

    public Integer getDeceso() {
        return deceso;
    }

    public List<Libro> getLibro() {
        return libro;
    }

    public Long getId() {
        return id;
    }

    //Setter
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNacimiento(Integer nacimiento) {
        this.nacimiento = nacimiento;
    }

    public void setDeceso(Integer deceso) {
        this.deceso = deceso;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLibro(List<Libro> libro) {
        this.libro = libro;
    }

    //Constructor
    public Autor() {}

    public Autor(DataAutor dataAutor) {
        this.nombre = dataAutor.nombre();
        this.nacimiento = dataAutor.nacimiento();
        this.deceso = dataAutor.deceso();
    }

    //toString
    @Override
    public String toString() {
        return "***********AUTOR***************\nNombre='" + nombre + '\'' +
                ", Nacimiento=" + nacimiento +
                ", Deceso=" + deceso +
                "\n";
                //"\n******************************\n";
    }
}
