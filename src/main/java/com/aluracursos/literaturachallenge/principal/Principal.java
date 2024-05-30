package com.aluracursos.literaturachallenge.principal;

import com.aluracursos.literaturachallenge.models.*;
import com.aluracursos.literaturachallenge.repository.AutorRepository;
import com.aluracursos.literaturachallenge.repository.LibroRepository;
import com.aluracursos.literaturachallenge.services.ConsumoAPI;
import com.aluracursos.literaturachallenge.services.ConvierteDatos;
import com.aluracursos.literaturachallenge.services.Menus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Principal {
    ConsumoAPI consumoAPI = new ConsumoAPI();
    ConvierteDatos convertidor = new ConvierteDatos();
    Scanner teclado = new Scanner(System.in);
    Menus menu = new Menus();
    Optional<Autor> autorExistente;
    Optional<Libro> libroExistente;

    private String URL_BASE = "https://gutendex.com/books/?";
    private List<Autor> listaAutores = new ArrayList<>();
    private List<Libro> listaLibros = new ArrayList<>();
    private LibroRepository repositorioLibro;
    private AutorRepository repositorioAutor;
    private Integer stage = 0;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.repositorioLibro = libroRepository;
        this.repositorioAutor = autorRepository;
    }

    public void principal() {
        Integer opcion=-1;
        while (stage >= 0 && stage <= 2) {
            switch (stage) {
                case 0:
                    opcion=menu.validarOpcionCorrecta("Inicio",2);
                    stage = opcion;
                    if (stage==0){stage=99;}
                    break;
                case 1://Menu para agregar un libro
                    opcion = -1;
                    while (opcion != 0) {
                        opcion=menu.validarOpcionCorrecta("agregar",3);
                        teclado.nextLine();
                        switch (opcion) {
                            case 1:
                                buscarLibroPorTitulo();
                                break;
                            case 2:
                                buscarLibroPorIdioma();
                                break;
                            case 3:
                                buscarLibroPorFecha();
                                break;
                            case 0:
                                stage = 0;
                                break;
                            default:
                                System.out.println("\n\nPor favor elija una opción correcta\n\n");
                                stage=1;
                                break;
                        }
                    }
                    break;
                case 2://Menu para mostrar los registros
                    opcion = -1;
                    while (opcion != 0) {
                        listaAutores = repositorioAutor.findAll();
                        listaLibros = repositorioLibro.findAll();
                        opcion=menu.validarOpcionCorrecta("enlistar",6);
                        switch (opcion) {
                            case 1:
                                mostrarListaDeLibros();
                                break;
                            case 2:
                                mostrarListaDeAutores();
                                break;
                            case 3:
                                enlistarAutorPorAnio();
                                break;
                            case 4:
                                enlistarLibroPorIdioma();
                                break;
                            case 5:
                                top10Libros();
                                break;
                            case 6:
                                mostrarAutorPorNombre();
                                break;
                            case 0:
                                stage=0;
                                break;
                            default:
                                stage=2;
                                System.out.println("\n\nPor favor elija una opción correcta\n\n");
                                break;
                        }
                    }
                    break;
            }
        }
    }

    public void buscarLibroPorTitulo() {
        System.out.println("Escriba el titulo que desea buscar");
        var tituloLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + "search=" + tituloLibro.replace(" ", "%20"));
        resultadosDeBusqueda(json);
    }

    public void buscarLibroPorIdioma() {
        String idiomaBuscado = validarIdioma();
        var json = consumoAPI.obtenerDatos(URL_BASE + "languages=" + idiomaBuscado);
        resultadosDeBusqueda(json);
    }

    public void buscarLibroPorFecha() {
        System.out.println("Escriba un Rango de año que desea buscar\nDesde:");
        var nacimiento = teclado.nextInt();
        teclado.nextLine();
        System.out.println("Hasta");
        var deceso = teclado.nextInt();
        teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + "author_year_start=" + nacimiento + "&author_year_end=" + deceso);
        resultadosDeBusqueda(json);
    }

    public void resultadosDeBusqueda(String json) {
        var datos = convertidor.obtenerDatos(json, SearchLibro.class);
        if (datos.resultados() == 0) {
            System.out.println("No se encontro ningun libro\n");
        } else {
            IntStream.range(0, datos.libro().size()).forEach(i -> System.out.printf("{%d} %s descargas: %d \n", i + 1, datos.libro().get(i).titulo(), datos.libro().get(i).descargas()));
            System.out.println("Elija un Libro...");
            Integer libroElegido = menu.validarOpcionCorrecta("",32);
            teclado.nextLine();
            if (libroElegido!=0){
                Libro libro = new Libro(datos.libro().get(libroElegido - 1));
                libroExistente = repositorioLibro.findByTitulo(libro.getTitulo());
                if (!libroExistente.isPresent()) {
                    repositorioLibro.save(libro);
                    libro.getAutor().forEach(autor -> {
                        autorExistente = repositorioAutor.findByNombre(autor.getNombre());
                        if (!autorExistente.isPresent()) {
                            repositorioAutor.save(autor);
                        }
                    });
                }else {
                    System.out.println("\nEste Libro ya existe en los registros:");
                }
                System.out.println(libro);
            }else{
                System.out.println("\nError, la opción ingresada no es correcta\n");
            }
        }
    }

    public void mostrarListaDeLibros() {
        if (listaLibros.size() != 0) {
            listaLibros.forEach(System.out::println);
        } else {
            System.out.println("No hay libros Registrados");
        }
    }

    public void mostrarListaDeAutores() {

        if (listaAutores.size() != 0) {
            listaAutores.forEach(a -> {
                System.out.printf("*********AUTOR**********\nNombre: %s\nNacimiendo: %d\nDeceso: %d\nlibros:", a.getNombre(), a.getNacimiento(), a.getDeceso());
                listaLibros.stream().filter(l -> l.getAutorNombre().contains(a.getNombre())).forEach(l -> System.out.printf("-[%s]", l.getTitulo()));
                System.out.println("\n******************\n");
            });
        } else {
            System.out.println("No hay autores registrados\n");
        }
    }

    public void enlistarAutorPorAnio() {
        System.out.println("Ingrese una año en el que se encontraba vivo el autor :");
        var anio = teclado.nextInt();
        teclado.nextLine();
        listaAutores = repositorioAutor.findByNacimientoWhitDeceso(anio);//listaAutores.stream().filter(a->a.getDeceso()>anio && a.getNacimiento()<anio ).toList();
        if (listaAutores.size() != 0) {
           listaAutores.forEach(System.out::println);
        } else {
            System.out.println("No hay autores registrados vivos en ese año\n");
        }
    }

    public void enlistarLibroPorIdioma() {//Las funciones que usa idioma se encuentran al final del codigo
        listaLibros = repositorioLibro.findByIdioma(Idioma.fromString(validarIdioma()));
        if (listaLibros.size() != 0) {
            listaLibros.forEach(System.out::println);
        } else {
            System.out.println("No hay libros registrado con ese idioma");
        }
    }

    public void top10Libros(){
        listaLibros = repositorioLibro.findTop10ByOrderByDescargasDesc();
        listaLibros.forEach(System.out::println);
    }

    public void  mostrarAutorPorNombre(){
        System.out.println("Indique el nombre del autor que desea encontrar:");
        String nombreAutor = teclado.nextLine();
        autorExistente = repositorioAutor.findByNombreContainingIgnoreCase(nombreAutor);
        if (autorExistente.isPresent()){
            System.out.println(autorExistente.get()+"Libros registrados que escribió:");
            //System.out.println(repositorioLibro.findByA(autorExistente.get().getNombre()));
            listaLibros.stream().filter(l -> l.getAutorNombre().contains(autorExistente.get().getNombre())).forEach(l -> System.out.printf("-[%s]", l.getTitulo()));
        }
    }

    public String validarIdioma() {
        String idiomaElegido = "";
        menu.mostrarMenus("idiomas");
        while (idiomaElegido.equalsIgnoreCase("")) {
            System.out.println("(\"exit\" para salir \"help\" para ver todos los idiomas)\nPor favor Ingrese un Idioma valido");
            idiomaElegido = teclado.nextLine();
            idiomaElegido = cambiarAIdioma(idiomaElegido);
        }
        String idiomaValido = idiomaElegido;
        return idiomaValido;
        // listaLibros.stream().filter(l->l.getIdioma().equals(Idioma.fromString(idiomaValido))).forEach(System.out::println);
    }

    public String cambiarAIdioma(String idiomaIngresado) {

        String idiomaValido = "exit";
        if (!idiomaIngresado.toLowerCase().startsWith("exit")) {
            idiomaValido = "";
        }
        if (idiomaIngresado.toLowerCase().equalsIgnoreCase("help")) {
            menu.mostrarMenus("lenguajes");
            System.out.println("Escriba el codigo de un idioma que quiera elegir");
            var nuevoIdioma = teclado.nextLine();
            idiomaValido = nuevoIdioma;
        }
        if (idiomaIngresado.toLowerCase().startsWith("esp") || idiomaIngresado.toLowerCase().equalsIgnoreCase("es")) {
            idiomaValido = "es";
        }
        if (idiomaIngresado.toLowerCase().startsWith("fra") || idiomaIngresado.toLowerCase().equalsIgnoreCase("fr")) {
            idiomaValido = "fr";
        }
        if (idiomaIngresado.toLowerCase().startsWith("por") || idiomaIngresado.toLowerCase().equalsIgnoreCase("pt")) {
            idiomaValido = "pr";
        }
        if (idiomaIngresado.toLowerCase().startsWith("ing") || idiomaIngresado.toLowerCase().equalsIgnoreCase("en")) {
            idiomaValido = "en";
        }
        return idiomaValido;

    }

}
