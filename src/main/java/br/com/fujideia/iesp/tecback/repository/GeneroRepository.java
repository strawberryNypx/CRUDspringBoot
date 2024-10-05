package br.com.fujideia.iesp.tecback.repository;

import br.com.fujideia.iesp.tecback.model.Filme;
import br.com.fujideia.iesp.tecback.model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Long> {
    @Query("SELECT f FROM Filme f JOIN f.generos g WHERE g.nome LIKE %:genero%")
    List<Filme> buscarPorGenero(@Param("genero") String genero);
}
