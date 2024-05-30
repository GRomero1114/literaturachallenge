package com.aluracursos.literaturachallenge.services;

import java.io.*;
import java.lang.reflect.Field;
import java.util.Scanner;

public class Menus {
    Scanner teclado = new Scanner(System.in);
    public void mostrarMenus(String tipo) {
        if (tipo.equalsIgnoreCase("Inicio")) {
            System.out.println("""
                    *****MENU*****
                    {1} Buscar libros para agregar
                    {2} Mostrar libros registrados
                    
                    {0} Salir
                    """);
        }

        if (tipo.equalsIgnoreCase("agregar")) {
            System.out.println("""
                    *****MENU*****
                    {1} Buscar libro por titulo
                    {2} Buscar libros por Idioma
                    {3} Buscar libros por Fecha
                    
                    {0} Volver al menú anterior
                    """);
        }

        if (tipo.equalsIgnoreCase("enlistar")) {
            System.out.println("""
                    \n *****MENU*****
                    {1} Mostrar lista de libros
                    {2} Mostrar lista de Autores
                    {3} Mostrar autores registrados vivos en un determinado año
                    {4} Mostrar libros por idioma
                    {5} Mostrar Top 10 libros
                    {6} Buscar algun autor registrado por Nombre
                    
                    {0} Volver al menú anterior""");
        }

        if (tipo.equalsIgnoreCase("idiomas")) {
            System.out.println("""
                    Ingrese el idioma de los libros que desea buscar:
                    Español
                    Frances
                    Ingles
                    Portugues""");
        }
            if (tipo.equalsIgnoreCase("lenguajes")) {
            File lenguajes = new File("src/main/java/com/aluracursos/literaturachallenge/services/lenguajes.txt");
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

    public Integer validarOpcionCorrecta(String menu,Integer opcionesDelMenu){
        boolean entradaValida = false;
        Integer opcion=-1;
        while (!entradaValida) {
            mostrarMenus(menu);
            System.out.print("Ingrese su opción: ");
            if (teclado.hasNextInt()) {
                opcion = teclado.nextInt();
                if (opcion >= 0 && opcion <= opcionesDelMenu) {
                    entradaValida = true;
                    break;
                }
                if (!entradaValida) {
                    System.out.println("\nOpción no válida. Por favor, ingrese una de las opciones en pantalla");
                }
            } else {
                System.out.println("\nOpción no válida. Por favor, ingrese el numero de una de las opciones en pantalla");
                teclado.next(); // Consumir la entrada no válida
            }
        }
        return opcion;
    }


}