package joao2dev.ProjetoHq.Repositorys;

import joao2dev.ProjetoHq.Revista.ComicModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComicRepository extends JpaRepository<ComicModel , Long> {
}
