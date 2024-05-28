package com.aluracursos.literaturachallenge.principal;

import com.aluracursos.literaturachallenge.models.*;
import com.aluracursos.literaturachallenge.repository.AutorRepository;
import com.aluracursos.literaturachallenge.repository.LibroRepository;
import com.aluracursos.literaturachallenge.services.ConsumoAPI;
import com.aluracursos.literaturachallenge.services.ConvierteDatos;
import com.aluracursos.literaturachallenge.services.Menus;
import org.hibernate.LazyInitializationException;

import java.io.File;
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
    Optional<Autor> autorO;

    private String URL_BASE= "https://gutendex.com/books/?";
    private List<Autor> listaAutores= new ArrayList<>();
    private List<Libro> listaLibros = new ArrayList<>();
    private LibroRepository repositorioLibro;
    private AutorRepository repositorioAutor;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.repositorioLibro = libroRepository;
        this.repositorioAutor= autorRepository;
    }

    public void iniciarAPP(){



        Integer opcion=-1;

        while (opcion!=0){
            listaAutores=repositorioAutor.findAll();
            listaLibros= repositorioLibro.findAll();
            menu.mostrarMenus("principal");
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
                case 4:
                    buscarAutorPorAnio();
                    break;
                case 5:
                    buscarLibroPorIdioma();
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
        System.out.println("Elija una opcion!");
        Integer libroElegido = teclado.nextInt();
        teclado.nextLine();
        Libro libro = new Libro(datos.libro().get(libroElegido-1));
        libro.getAutor().forEach(autor -> {
            System.out.println("empezando el Foreach");
            Optional<Autor> autorExistente = repositorioAutor.findByNombre(autor.getNombre());
            if (autorExistente.isPresent()) {
                System.out.println("Existe!");
                autor = autorExistente.get();
            } else {
                System.out.println("NoExiste");
                repositorioAutor.save(autor);
                System.out.println("Gravado");
            }
            try {
                System.out.println("agregando libro al autor");
                autor.getLibro().add(libro);
            }catch (LazyInitializationException e){
                System.out.println("No se pudo agregar libro por\n "+e);
            }
            System.out.println("Terminando de Foreach");
        });
        System.out.println("Intentando save(libro)");
        repositorioLibro.save(libro);

        return libro;
    }

    public void mostrarListaDeLibros(){
        if (listaLibros.size()!=0) {
           listaLibros.forEach(System.out::println);
        }else{
            System.out.println("No hay libros Registrados");
        }
    }

    public void mostrarListaDeAutores(){

        if (listaAutores.size()!=0){
            listaAutores.forEach(a->{
                System.out.printf("Nombre: %s\nNacimiendo: %d\nDeceso: %d\nlibros:",a.getNombre(),a.getNacimiento(),a.getDeceso());
                //listaLibros.stream().map(l->l.getAutorNombre().stream().filter(nombre->nombre.equalsIgnoreCase(a.getNombre()))).toList().forEach(ln->ln.forEach(System.out::println));
                listaLibros.stream().filter(l->l.getAutorNombre().contains(a.getNombre())).forEach(l-> System.out.printf("-[%s]",l.getTitulo()));
                System.out.println("\n");
            });
        }else{
            System.out.println("No hay autores registrados\n");
        }
    }

    public void buscarAutorPorAnio(){
        System.out.println("Ingrese una año en el que se encontraba vivo el autor :");
        var anio = teclado.nextInt();
        teclado.nextLine();
        listaAutores=listaAutores.stream().filter(a->a.getDeceso()>anio && a.getNacimiento()<anio ).toList();
        if (listaAutores.size()!=0){
            listaAutores.forEach(a->{
                System.out.printf("Nombre: %s\nNacimiendo: %d\nDeceso: %d\nlibros:",a.getNombre(),a.getNacimiento(),a.getDeceso());
                //listaLibros.stream().map(l->l.getAutorNombre().stream().filter(nombre->nombre.equalsIgnoreCase(a.getNombre()))).toList().forEach(ln->ln.forEach(System.out::println));
                listaLibros.stream().filter(l->l.getAutorNombre().contains(a.getNombre())).forEach(l-> System.out.printf("-[%s]",l.getTitulo()));
                System.out.println("\n");
            });
        }else{
            System.out.println("No hay autores registrados\n");
        }

    }

    public void buscarLibroPorIdioma(){
        String idiomaElegido="";
       menu.mostrarMenus("idiomas");
        while (idiomaElegido.equalsIgnoreCase(""))
        {
            idiomaElegido= teclado.nextLine();
            idiomaElegido=cambiarAIdioma(idiomaElegido);
        }
        String idiomaValido=idiomaElegido;

        listaLibros.stream().filter(l->l.getLenguaje().equals(Lenguaje.fromString(idiomaValido))).forEach(System.out::println);
    }

    public String cambiarAIdioma(String idiomaIngresado){

        String idiomaValido = "";
        if (!idiomaIngresado.toLowerCase().contains("exit")){
            idiomaValido="";
        }
        if (idiomaIngresado.toLowerCase().equalsIgnoreCase("help")){
            menu.mostrarMenus("lenguajes");
            System.out.println("Escriba el codigo de un idioma que quiera elegir");
            var nuevoIdioma= teclado.nextLine();
            idiomaValido=nuevoIdioma;
        }
            if (idiomaIngresado.toLowerCase().contains("esp")){
                idiomaValido="es";
            }
            if (idiomaIngresado.toLowerCase().contains("fra")){
                idiomaValido="fr";
            }
            if (idiomaIngresado.toLowerCase().contains("por")){
                idiomaValido="pr";
            }
            if (idiomaIngresado.toLowerCase().contains("ing")){
                idiomaValido="en";
            }

        return idiomaValido;

    }

}
