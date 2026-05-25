package joao2dev.ProjetoHq.Repositorys;

import joao2dev.ProjetoHq.Revista.ComicModel;
import joao2dev.ProjetoHq.Usuario.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ComicRepository extends JpaRepository<ComicModel , Long> {

   List<ComicModel> findByTituloHqContainingIgnoreCase(String tituloHq);
   List<ComicModel> findByUsuario(UserModel usuario);
}
