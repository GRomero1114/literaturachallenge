package com.aluracursos.literaturachallenge.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SearchLibro(
        @JsonAlias("count") Integer resultados,
        @JsonAlias("results") List<DataLibro> libro
) {
}
