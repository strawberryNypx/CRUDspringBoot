package br.com.fujideia.iesp.tecback.repository;

import br.com.fujideia.iesp.tecback.model.Filme;
import br.com.fujideia.iesp.tecback.model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import br.com.fujideia.iesp.tecback.model.Produtor;

import java.util.List;

@Repository
public interface ProdutorRepository extends JpaRepository<Produtor, Long> {
    @Query("SELECT f FROM Filme f JOIN f.produtor p WHERE p.nome LIKE %:produtor%")
    List<Filme> buscarPorProdutor(@Param("Produtor") String produtor);
}
