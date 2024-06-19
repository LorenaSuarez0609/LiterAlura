package com.aluracursos.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibros(
        @JsonAlias ("title") String titulo,
        @JsonAlias ("authors") List<DatosAutor> autor,
        @JsonAlias ("languages") List<String> idiomas,
        @JsonAlias ("download_count") int numeroDeDescargas

) {

   }
