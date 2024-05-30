package com.aluracursos.literaturachallenge.repository;

import com.aluracursos.literaturachallenge.models.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor,Long> {
    Optional<Autor> findByNombre(String nombre);
    Optional<Autor> findByNombreContainingIgnoreCase(String nombreA);

    @Query("Select a From Autor a WHERE a.nacimiento <= :anio AND a.deceso >= :anio ")
    List<Autor> findByNacimientoWhitDeceso (@Param("anio") Integer anio);
//    @Query("SELECT a FROM Autor a LEFT JOIN FETCH a.libro WHERE a.nombre = :nombre")
//    Optional<Autor> findByNombreWithLibro(@Param("nombre") String nombre);
}
