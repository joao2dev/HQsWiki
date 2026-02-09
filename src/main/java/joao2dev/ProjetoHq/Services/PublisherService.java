package joao2dev.ProjetoHq.Services;

import joao2dev.ProjetoHq.Repositorys.PublisherRepository;
import joao2dev.ProjetoHq.Revista.CharacterModel;
import joao2dev.ProjetoHq.Revista.PublisherModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {
    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public List<PublisherModel> mostrarEditoras(){
        return publisherRepository.findAll();
    }
    public PublisherModel mostrarEditoraPorId(Long id){
        Optional<PublisherModel> editora = publisherRepository.findById(id);
        return editora.orElse(null);
    }
    public PublisherModel adicionarEditora(PublisherModel publisherModel){
        return publisherRepository.save(publisherModel);

    }

    public void deletarById(Long id){
        publisherRepository.deleteById(id);
    }
    public PublisherModel editarById(Long id, PublisherModel publisherModel){
        Optional<PublisherModel> editora = publisherRepository.findById(id);
        if (editora.isPresent()){
            publisherModel.setId(id);
            return publisherRepository.save(publisherModel);
        }return null;
    }
   /* public List<PublisherModel> buscarEditora(String nome){
        return publisherRepository.findByNomeDaEditora(nome);
    }*/
}
