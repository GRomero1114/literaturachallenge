package com.aluracursos.literaturachallenge.repository;

import com.aluracursos.literaturachallenge.models.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor,Long> {

}
