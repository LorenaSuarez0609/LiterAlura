package com.aluracursos.literalura.principal;

import com.aluracursos.literalura.model.*;
import com.aluracursos.literalura.repository.AutorRepository;
import com.aluracursos.literalura.repository.LibroRepository;
import com.aluracursos.literalura.service.ConsumoAPI;
import com.aluracursos.literalura.service.ConvierteDatos;
import com.aluracursos.literalura.validacion.AllValidations;



import java.util.*;


public class Principal {
    private static final String URL_BASE = "http://gutendex.com/books/";
    private static final String URL_BASE_Lenguaje = "https://gutendex.com/books/?languages=";
    private static final String URL_BASE_Actores = "https://gutendex.com/books/?search=";;
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private Scanner teclado = new Scanner(System.in);
    private LibroRepository libroRepositorio;
    private AutorRepository autorRepositorio;
    private AllValidations validations = new AllValidations();
    public Principal(LibroRepository libroRepository1, AutorRepository autorRepository) {
            this.libroRepositorio = libroRepository1;
            this.autorRepositorio = autorRepository;
    }
    private boolean bandera = true;

    @SuppressWarnings("resource")
    public void muestraElMenu() {
        System.out.print("estoy aqui Biblioteca Alura");
        //Hacer while para mostrar el menu con un swich

        while (bandera) {
            System.out.println("Biblioteca Alura");
            System.out.println("""
                    ***********************************************
                    Elija la opcion a traves de su número:
                    1)Buscar libro por titulo
                    2)Listar libros Registrados
                    3)Buscar autor
                    4)Listar autores vivos en un determinado año
                    5)Listar libros por idioma
                    0)Salir
                    ***********************************************
                    """);
            System.out.print("INGRESE UNA OPCION VALIDA: ");
            Scanner leer = new Scanner(System.in);
            //validations.verifyMenuInputIsValid(selectedOption);
            char opcion = leer.next().charAt(0);
            switch (opcion) {
                case '0' -> {
                    System.out.println("CERRANDO PROGRAMA");
                    bandera = false;
                }
                case '1' -> {//Buscar libros por titulo
                   libroPorNombre();
                }
                case '2' -> {
                    //Listar libros Registrados
                    librosRegistrados();
                }

                case '3' -> {
                    //Listar autores por nombre
                    ListarAutoresPorNombre();
                }

                case '4' -> {
                    //Listar autores vivos en un determinado año
                    ListarAutoresVivos();
                }

                case '5' -> {//Listar libros por idioma
                    //Menu de idiomas
                    System.out.println("""
                        ****************************************
                        Elija el idioma del listado de libros:
                        1)Español
                        2)Ingles
                        3)Frances
                        4)Finlandes
                        0)Salir
                        *********************************************
                        """);
                    System.out.print("INGRESE UNA OPCION VALIDA: ");
                    Scanner leerIdioma = new Scanner(System.in);
                    char opcionIdioma = leerIdioma.next().charAt(0);
                    switch (opcionIdioma) {
                        case '0' -> {
                                System.out.println("VOLVIENDO AL MENU ANTERIOR");
                        }
                        case '1' -> {
                            //Español
                            //https://gutendex.com/books/?languages=es
                            listarLibrosPorIdioma("es");
                        }
                        case '2' -> {
                            //Ingles
                            //https://gutendex.com/books/?languages=en
                            listarLibrosPorIdioma("en");
                        }
                        case '3' -> {
                            //Frances
                            //https://gutendex.com/books/?languages=fr
                            listarLibrosPorIdioma("fr");
                        }
                        case '4' -> {
                            //filandes
                            //https://gutendex.com/books/?languages=fi
                            listarLibrosPorIdioma("fi");
                        }
                        default -> {
                            System.out.println("OPCION INCORRECTA");
                        }
                    }
                }
                default -> {
                    System.out.println("OPCION INCORRECTA");
                }
            }
        }



        }

    private void listarLibrosPorIdioma(String idioma) {
        var json = consumoAPI.obtenerDatos(URL_BASE_Lenguaje+ idioma );
        var datosBusquedaLibros = conversor.obtenerDatos(json, Datos.class);
        //System.out.println(datosBusquedaLibros );
        //System.out.println("Libros en Español Encontrados");
        if (datosBusquedaLibros.resultados() != null) {
            System.out.println("""
                                ************************************************************************************************
                                - LIBROS ENCONTRADOS -""");
            datosBusquedaLibros.resultados().stream()
                    .sorted(Comparator.comparing(DatosLibros::numeroDeDescargas).reversed())
                    .map(l -> l.titulo().toUpperCase())
                    .forEach(System.out::println);
            System.out.println("""
                                ************************************************************************************************
                                """);
        }
        if (datosBusquedaLibros.resultados() == null) {
            System.out.println("No hay libros que listar");
        }
    }

    private void ListarAutoresVivos() {
        System.out.print("INGRESE AÑO: ");
        int comienzo = Integer.parseInt(teclado.nextLine());
        if(comienzo >=0 & comienzo <=2024) {
            var json = consumoAPI.obtenerDatos(URL_BASE + "?author_year_start=" + comienzo + "&author_year_end=" + comienzo);
            var datosPorAno = conversor.obtenerDatos(json, Datos.class);
            if (datosPorAno.resultados() != null) {
                System.out.println("""
                            ************************************************************************************************
                            - Libros de Autores vivos en el año:""" + comienzo);
                datosPorAno.resultados().stream()
                        .sorted(Comparator.comparing(DatosLibros::numeroDeDescargas).reversed())
                        .map(l -> l.titulo().toUpperCase())
                        .forEach(System.out::println);
                System.out.println("""
                            ************************************************************************************************
                            """);
            }
            if (datosPorAno.resultados() == null) {
                System.out.println("No hay libros que listar");
            }
        } else {
            System.out.println("AÑO INCORREPTO");
        }
    }

    private void ListarAutoresPorNombre() {
        System.out.println("Ingrese el nombre del Autor que desee buscar");
        var autorBuscado = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE_Actores + autorBuscado.replace(" ", "+") + "%20great");
        System.out.println(json);
        Datos autorBuscadoDatos = conversor.obtenerDatos(json, Datos.class);
        System.out.println(autorBuscadoDatos.resultados());
        if (autorBuscadoDatos.resultados() != null) {
            System.out.println("""
                        ************************************************************************************************
                        - Autor Encontrado -
                        """);
            autorBuscadoDatos.resultados().stream()
                    .sorted(Comparator.comparing(DatosLibros::hashCode).reversed() )
                    .map(l -> l.titulo().toUpperCase())
                    .forEach(System.out::println);
            System.out.println("""
                        ************************************************************************************************
                        """);
        } else {
            System.out.println("Autor No Encontrado");
        }


    }

    private void librosRegistrados() {
    //Muestra todos los datos de la API
        /*
        var json = consumoAPI.obtenerDatos(URL_BASE);
        var datos = conversor.obtenerDatos(json, Datos.class);
        //System.out.println(datos.resultados());
        System.out.println("""
                    ****************************************************************************************************
                    - lIBROS REGISTRADOS -
                    """);
        datos.resultados().stream()
                .sorted(Comparator.comparing(DatosLibros::numeroDeDescargas).reversed() )
                .map(l -> l.titulo().toUpperCase())
                .forEach(System.out::println);
        System.out.println("""
                    ****************************************************************************************************
                    """);

         */
        //desde la Base de datos
        List<Libro> libros = libroRepositorio.findAll();
        libros.forEach(System.out::println);


    }

    private void libroPorNombre() {
        System.out.println("Ingrese el nombre del libro que desee buscar");
        var tituloLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ", "+"));
        //Convierto los datos para poder mostrarlos en la terminal
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);
        Optional<DatosLibros> libroBuscado = datosBusqueda.resultados().stream()
                .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                .findFirst();
        if (libroBuscado.isPresent()) {
            mostrarLibro(libroBuscado.get());
            //Guardar el libro en la base de datos
            Libro newLibro = new Libro(libroBuscado.get());
            libroRepositorio.save(newLibro);
            //tipo de dato de autor
        } else {
            System.out.println("libro no encontrado en la API gutedex");
        }

    }


    private void mostrarLibro(DatosLibros libroDatos) {
        System.out.println("""
                        ****************************************************************************************************
                                                  Libro Encontrado""");
        System.out.println("Titulo: " + libroDatos.titulo());
        System.out.println("Autor: " + libroDatos.autor());
        System.out.println("Idiomas: " + libroDatos.idiomas());
        System.out.println("Descargas: " + libroDatos.numeroDeDescargas());
        System.out.println("""
                        *****************************************************************************************************
                        """);
    }



}


