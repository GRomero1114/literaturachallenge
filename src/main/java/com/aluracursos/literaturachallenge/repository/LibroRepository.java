package com.aluracursos.literaturachallenge.repository;

import com.aluracursos.literaturachallenge.models.Autor;
import com.aluracursos.literaturachallenge.models.Idioma;
import com.aluracursos.literaturachallenge.models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro,Long> {
    List<Libro> findByIdioma (Idioma idioma);
    List<Libro> findTop10ByOrderByDescargasDesc();
    Optional<Libro> findByTitulo(String titulo);

  // List<Libro> findByAutorNombre(Autor nombre);
}
