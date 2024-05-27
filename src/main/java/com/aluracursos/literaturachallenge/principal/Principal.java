package com.aluracursos.literaturachallenge.principal;

import com.aluracursos.literaturachallenge.models.Autor;
import com.aluracursos.literaturachallenge.models.Libro;
import com.aluracursos.literaturachallenge.models.SearchLibro;
import com.aluracursos.literaturachallenge.repository.AutorRepository;
import com.aluracursos.literaturachallenge.repository.LibroRepository;
import com.aluracursos.literaturachallenge.services.ConsumoAPI;
import com.aluracursos.literaturachallenge.services.ConvierteDatos;
import kotlin.ranges.IntRange;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Principal {
    ConsumoAPI consumoAPI = new ConsumoAPI();
    ConvierteDatos convertidor = new ConvierteDatos();
    Scanner teclado = new Scanner(System.in);
    Optional<Autor> autor;

    private String URL_BASE= "https://gutendex.com/books/?";
    private List<Autor> listaAutores= new ArrayList<>();
    private List<Libro> listaLibros = new ArrayList<>();
    private LibroRepository repositorioLibro;
    private AutorRepository repositorioAutor;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.repositorioLibro = libroRepository;
        this.repositorioAutor= autorRepository;
    }
    // private String buscarURL= "search=";

    public void mostrarMenu(){

        String menu=("""
                *****MENU*****
                {1} Buscar libro por titulo
                {2} Mostrar lista de libros
                {3} Mostrar lista de Autores
                {4} Buscar autores vivos en un determinado año
                {5} 
                Elige una opcion...
                """);

        Integer opcion=-1;

        while (opcion!=0){
            System.out.println(menu);
            opcion=teclado.nextInt();
            teclado.nextLine();
            switch (opcion){
                case 1 :
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    mostrarListaDeLibros();
                    break;
                case 3:
                    mostrarListaDeAutores();
                    break;
                case 0:
                    break;
                default:
                    break;
            }
        }
    }

    public void buscarLibroPorTitulo(){
        Libro libro =obtenerResultadosDeBusqueda();
        System.out.println(libro);
    }
    public Libro obtenerResultadosDeBusqueda(){
        System.out.println("Escriba el titulo que desea buscar");
        var tituloLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE+"search="+tituloLibro.replace(" ","%20"));
        var datos = convertidor.obtenerDatos(json, SearchLibro.class);
        IntStream.range(0, datos.libro().size()).forEach(i-> System.out.printf("{%d} %s descargas: %d \n",i+1,datos.libro().get(i).titulo(),datos.libro().get(i).descargas()));
        //datos.libro().forEach(l-> System.out.printf(""));
        System.out.println("Elija una opcion!");
        Integer libroElegido = teclado.nextInt();
        teclado.nextLine();
        Libro libro = new Libro(datos.libro().get(libroElegido-1));
        listaAutores=repositorioAutor.findAll();
        //IntStream.range(0,libro.getAutor().size()).forEach(i->listaAutores.);
        if (!listaAutores.stream().anyMatch(l->l.getNombre().equalsIgnoreCase(libro.getAutor().get(0).getNombre()))){
            System.out.println("No hay autores iguales");
            repositorioAutor.saveAll(libro.getAutor());
        }else{
            System.out.println("mismos Actores");
            // if(libro.getAutor().equals(listaAutores))
            //autor= libro.getAutor().stream()
            //Autor autor = new Autor(libro.getAutor().get(0));
            //listaAutores= libro.getAutor().stream().map(a->new Autor(a)).collect(Collectors.toList());
        }
        listaLibros=repositorioLibro.findAll();
        if (!listaLibros.stream().anyMatch(l->l.getTitulo().equalsIgnoreCase(libro.getTitulo()))) {
            repositorioLibro.save(libro);
        }else{
            System.out.println("Ya hay un libro con este nombre");
        }
        return libro;
    }

    public void mostrarListaDeLibros(){
        listaLibros=repositorioLibro.findAll();
        if (listaLibros.size()!=0) {
           listaLibros.forEach(System.out::println);
           // listaLibros.forEach(l->l.getAutor().forEach(System.out::println));
        }else{
            System.out.println("No hay libros Registrados");
        }
    }

    public void mostrarListaDeAutores(){
        listaAutores=repositorioAutor.findAll();
        listaLibros= repositorioLibro.findAll();
        if (listaAutores.size()!=0){
            listaAutores.forEach(a->{
                System.out.printf("Nombre: %s\nNacimiendo: %d\nDeceso: %d\nlibros:",a.getNombre(),a.getNacimiento(),a.getDeceso());
                //listaLibros.stream().map(l->l.getAutorNombre().stream().filter(nombre->nombre.equalsIgnoreCase(a.getNombre()))).toList().forEach(ln->ln.forEach(System.out::println));
                listaLibros.stream().filter(l->l.getAutorNombre().contains(a.getNombre())).forEach(l-> System.out.printf("-[%s]",l.getTitulo()));
                System.out.println("\n");
            });
        }else{
            System.out.println("No hay autores registrados");
        }
    }


}
