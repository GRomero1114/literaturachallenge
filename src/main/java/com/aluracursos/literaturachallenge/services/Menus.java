package com.aluracursos.literaturachallenge.services;

import java.io.*;
import java.lang.reflect.Field;

public class Menus {

    public void mostrarMenus(String tipo) {
        if (tipo.equalsIgnoreCase("principal")) {
            System.out.println("""
                    *****MENU*****
                    {1} Buscar libro por titulo
                    {2} Mostrar lista de libros
                    {3} Mostrar lista de Autores
                    {4} Mostrar autores registrados vivos hasta un determinado año
                    {5} Mostrar libros por idioma
                    Elige una opcion...
                    """);
        }
        if (tipo.equalsIgnoreCase("idiomas")) {
            System.out.println("""
                    Ingrese el idioma de los libros que desea buscar:
                    Español
                    Frances
                    Ingles
                    Portugues
                    help>Mostrar Todos los idiomas
                    """);
        }
        if (tipo.equalsIgnoreCase("lenguajes")) {
            File lenguajes = new File("lenguajes.txt");
            String contenido;
            FileReader lector = null;
            try {
                lector = new FileReader(lenguajes);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            BufferedReader lectura =new BufferedReader(lector);
            try {
                contenido=lectura.readLine();
                while (contenido!=null){
                    System.out.println(contenido);
                    contenido=lectura.readLine();
                }
                lectura.close();
                lector.close();
                System.out.println("\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}