package joao2dev.ProjetoHq.Repositorys;

import joao2dev.ProjetoHq.Revista.AutorModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<AutorModel, Long> {
    List<AutorModel> findByNomeAutor(String nome);

}
