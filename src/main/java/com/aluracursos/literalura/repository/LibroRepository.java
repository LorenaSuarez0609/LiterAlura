package com.aluracursos.literalura.repository;
import com.aluracursos.literalura.model.LenguajeOpciones;
import org.springframework.data.jpa.repository.JpaRepository;
import com.aluracursos.literalura.model.Libro;
import java.util.List;

public interface LibroRepository extends JpaRepository<Libro,Long>{

   // List<Libro> buscarPorTituloSegunCadena(String titulo);

   //List<Libro> buscarLibroPorIdiomas(LenguajeOpciones idiomas);

    //Libro buscarPorTituloLibro(String titulo);


}

