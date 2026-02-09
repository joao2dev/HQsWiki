package joao2dev.ProjetoHq.Repositorys;

import joao2dev.ProjetoHq.Revista.ComicModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComicRepository extends JpaRepository<ComicModel , Long> {

   List<ComicModel> findByTituloHqIgnoreCase(String tituloHq);
}
