package joao2dev.ProjetoHq.Services;

import joao2dev.ProjetoHq.Repositorys.CharacterRepository;
import joao2dev.ProjetoHq.Revista.CharacterModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CharacterService {
    private final CharacterRepository characterRepository;

    public CharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    public List<CharacterModel> mostrarPersonagens() {
        return characterRepository.findAll();
    }

    public CharacterModel mostrarPersonagemPorId(Long id) {
        Optional<CharacterModel> personagem = characterRepository.findById(id);
        return personagem.orElse(null);
    }

    public CharacterModel adicionarPersonagem(CharacterModel characterModel) {
        return characterRepository.save(characterModel);
    }

    public void deletarById(Long id) {
        characterRepository.deleteById(id);
    }

    public CharacterModel editarById(Long id, CharacterModel characterModel) {
        Optional<CharacterModel> personagem = characterRepository.findById(id);

        if (personagem.isPresent()) {
            characterModel.setId(id);
            return characterRepository.save(characterModel);
        }
        return null;
    }
    /*public List<CharacterModel> buscarPersonagem(String nome){
        return characterRepository.findByNomePersonagem(nome);
    }*/
}
