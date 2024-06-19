package com.aluracursos.literalura.model;
import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String titulo;
    @ManyToOne

    private Autor autor;

    @Enumerated(EnumType.STRING)
    private LenguajeOpciones idiomas;

    private int numeroDeDescargas;

    public Libro(){}

    public Libro(DatosLibros datosLibros, Autor autor){
        this.titulo = datosLibros.titulo();

        this.idiomas = datosLibros.idiomas().stream()
                .map(LenguajeOpciones::getNameByCode)
                .collect(Collectors.toList())
                .get(0);

        this.numeroDeDescargas = datosLibros.numeroDeDescargas();
        this.autor = autor;
    }




    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public LenguajeOpciones getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(LenguajeOpciones idiomas) {
        this.idiomas = idiomas;
    }

    public int getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(int numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor=" + autor +
                ", idiomas=" + idiomas +
                ", numeroDeDescargas=" + numeroDeDescargas +
                '}';
    }
}