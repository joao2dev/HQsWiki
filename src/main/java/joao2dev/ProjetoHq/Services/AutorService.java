package joao2dev.ProjetoHq.Services;

import joao2dev.ProjetoHq.Repositorys.AutorRepository;
import joao2dev.ProjetoHq.Revista.AutorModel;
import joao2dev.ProjetoHq.Revista.CharacterModel;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class AutorService {
    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    //    Criar autor
    public AutorModel criarAutor(AutorModel autorModel){
        return autorRepository.save(autorModel);

    }
//   Listar autores
    public List<AutorModel> listarAutores(){
        return autorRepository.findAll();
    }
//    listar autor por id
    public AutorModel listarAutorPorId(Long id){
        Optional<AutorModel> autor = autorRepository.findById(id);
        return autor.orElse(null);
    }
//  Deletar autor
    public void deletarAutor(Long id){
        autorRepository.deleteById(id);
    }
//  Editar autor
    public AutorModel editarAutor(Long id, AutorModel autorModel){
        if (autorRepository.existsById(id)){
            autorModel.setIdAutor(id);
            autorRepository.save(autorModel);
        }
            return null;

    }
    public List<AutorModel> buscarAutor(String nome){
        return autorRepository.findByNomeAutor(nome);
    }
}
