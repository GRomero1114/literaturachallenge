package com.aluracursos.literaturachallenge.principal;

import com.aluracursos.literaturachallenge.models.Libro;
import com.aluracursos.literaturachallenge.models.SearchLibro;
import com.aluracursos.literaturachallenge.repository.LibroRepository;
import com.aluracursos.literaturachallenge.services.ConsumoAPI;
import com.aluracursos.literaturachallenge.services.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Principal {
    ConsumoAPI consumoAPI = new ConsumoAPI();
    ConvierteDatos convertidor = new ConvierteDatos();
    Scanner teclado = new Scanner(System.in);
    private String URL_BASE= "https://gutendex.com/books/?";
   // private String buscarURL= "search=";

    private LibroRepository libroRepository;

    public Principal(LibroRepository repository) {
        this.libroRepository = repository;
    }

    public void mostrarMenu(){

        String menu=("""
                *****MENU*****
                {1} Buscar libro por titulo
                
                Elige una opcion...
                """);

        Integer opcion=-1;

        while (opcion!=0){
            System.out.println(menu);
            opcion=teclado.nextInt();
            teclado.nextLine();
            switch (opcion){
                case 1 :
                    obtenerResultadosDeBusqueda();
                    break;
                case 0:
                    break;
                default:
                    break;
            }
        }

    }

//    public void buscarLibroPorNombre()
//    {
//
//    }

    public void obtenerResultadosDeBusqueda(){
        System.out.println("Escriba el titulo que desea buscar");
        var tituloLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE+"search="+tituloLibro.replace(" ","%20"));
        var datos = convertidor.obtenerDatos(json, SearchLibro.class);
        IntStream.range(0, datos.libro().size()).forEach(i-> System.out.printf("{%d} %s descargas: %d ",i+1,datos.libro().get(i).titulo(),datos.libro().get(i).descargas()));
        //datos.libro().forEach(l-> System.out.printf(""));




       // return
    }

}
