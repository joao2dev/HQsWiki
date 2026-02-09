package joao2dev.ProjetoHq.Repositorys;

import joao2dev.ProjetoHq.Revista.AutorModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutorRepository extends JpaRepository<AutorModel, Long> {
    List<AutorModel> findByNomeIgnoreCase(String nome);

}
