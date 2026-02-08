package joao2dev.ProjetoHq.Repositorys;

import joao2dev.ProjetoHq.Revista.CharacterModel;
import joao2dev.ProjetoHq.Revista.ComicModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ComicRepository extends JpaRepository<ComicModel , Long> {
    @Query("""
    SELECT c FROM ComicModel c
    JOIN FETCH c.editora
    WHERE c.idHQ = :id
""")
    Optional<ComicModel> buscarCompleto(@Param("id") Long id);
    List<ComicModel> findByTituloHq(String nomePersonagem);
}
