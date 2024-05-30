package com.aluracursos.literaturachallenge.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataLibro(
        @JsonAlias("id") Integer id,
        @JsonAlias("title") String titulo ,
        @JsonAlias("authors")List <DataAutor> autores,
        @JsonAlias("download_count") Integer descargas,
        @JsonAlias("languages") List <String> idioma
        ) {
}
