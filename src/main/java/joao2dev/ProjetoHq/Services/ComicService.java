package joao2dev.ProjetoHq.Services;

import jakarta.transaction.Transactional;
import joao2dev.ProjetoHq.Repositorys.AutorRepository;
import joao2dev.ProjetoHq.Repositorys.CharacterRepository;
import joao2dev.ProjetoHq.Repositorys.ComicRepository;
import joao2dev.ProjetoHq.Repositorys.PublisherRepository;
import joao2dev.ProjetoHq.Revista.AutorModel;
import joao2dev.ProjetoHq.Revista.CharacterModel;
import joao2dev.ProjetoHq.Revista.ComicModel;
import joao2dev.ProjetoHq.Revista.PublisherModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ComicService {
    private final ComicRepository comicRepository;
    private final PublisherRepository publisherRepository;
    private final AutorRepository autorRepository;
    private final CharacterRepository characterRepository;

    public ComicService(
            ComicRepository comicRepository,
            PublisherRepository publisherRepository,
            AutorRepository autorRepository,
            CharacterRepository characterRepository
    ) {
        this.comicRepository = comicRepository;
        this.publisherRepository = publisherRepository;
        this.autorRepository = autorRepository;
        this.characterRepository = characterRepository;
    }

    // 📌 Criar HQ
    public ComicModel criarComic(ComicModel comicModel) {
        validarComic(comicModel);
        vincularRelacionamentos(comicModel);
        return comicRepository.save(comicModel);
    }

    // 📌 Listar todas as HQs
    public List<ComicModel> listarComics() {
        return comicRepository.findAll();
    }

    // 📌 Buscar HQ por ID
    public ComicModel buscarComicPorId(Long id) {
        ComicModel comic = comicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("HQ não encontrada"));

        PublisherModel editora = publisherRepository
                .findById(comic.getEditora().getIdEditora())
                .orElseThrow();

        comic.setEditora(editora);

        return comic;
    }


    // 📌 Buscar HQ por título
    public List<ComicModel> buscarComicPorTitulo(String titulo) {
        return comicRepository.findByTituloHq(titulo);
    }

    // 📌 Editar HQ
    public ComicModel editarComic(Long id, ComicModel comicModel) {
        Optional<ComicModel> comicExistente = comicRepository.findById(id);

        if (comicExistente.isPresent()) {
            comicModel.setIdHQ(id);
            validarComic(comicModel);
            vincularRelacionamentos(comicModel);
            return comicRepository.save(comicModel);
        }
        return null;
    }

    // 📌 Deletar HQ
    public void deletarComic(Long id) {
        comicRepository.deleteById(id);
    }


    void validarComic(ComicModel comicModel) {
        if (comicModel.getTituloHq() == null || comicModel.getTituloHq().isBlank()) {
            throw new RuntimeException("titulo da HQ obrigarotio");
        }
        if (comicModel.getAnoDeLancamentoHq() < 1800) {
            throw new RuntimeException("o Ano da HQ não pode ser menor que 1800");
        }
        if (comicModel.getEditora() == null) {
            throw new RuntimeException("A HQ precisa de uma editora.");
        }

        if (comicModel.getEditora().getNomeDaEditora() == null ||
                comicModel.getEditora().getNomeDaEditora().isBlank()) {
            throw new RuntimeException("Nome da editora é obrigatório.");
        }

        if (comicModel.getAutores() == null || comicModel.getAutores().isEmpty()) {
            throw new RuntimeException("A Hq precisa de um autor, caso não tenha coloque como desconhecido.");
        }
        if (comicModel.getGeneroHq() == null) {
            throw new RuntimeException("Genero da HQ obrigarotio");
        }
        if (comicModel.getPersonagens() == null || comicModel.getPersonagens().isEmpty()) {
            throw new RuntimeException("A HQ precisa de personagens.");
        }
    }
    private void vincularRelacionamentos(ComicModel comicModel) {

        // ===== EDITORA =====
        List<PublisherModel> editoras =
                publisherRepository.findByNomeDaEditora(
                        comicModel.getEditora().getNomeDaEditora()
                );

        PublisherModel editora;

        if (!editoras.isEmpty()) {
            editora = editoras.get(0);
        } else {
            editora = publisherRepository.save(comicModel.getEditora());
        }

        comicModel.setEditora(editora);


        // ===== AUTORES =====
        List<AutorModel> autoresFinal = new ArrayList<>();

        for (AutorModel autor : comicModel.getAutores()) {
            List<AutorModel> autoresExistentes =
                    autorRepository.findByNomeAutor(autor.getNomeAutor());

            if (!autoresExistentes.isEmpty()) {
                autoresFinal.add(autoresExistentes.get(0));
            } else {
                autoresFinal.add(autorRepository.save(autor));
            }
        }

        comicModel.setAutores(autoresFinal);

        // ===== PERSONAGENS =====
        if (comicModel.getPersonagens() != null && !comicModel.getPersonagens().isEmpty()) {

            List<CharacterModel> personagensFinal = new ArrayList<>();

            for (CharacterModel personagem : comicModel.getPersonagens()) {

                List<CharacterModel> existentes =
                        characterRepository.findByNomePersonagem(personagem.getNomePersonagem());

                if (!existentes.isEmpty()) {
                    personagensFinal.add(existentes.get(0));
                } else {
                    personagensFinal.add(characterRepository.save(personagem));
                }
            }

            comicModel.setPersonagens(personagensFinal);
        }

    }



}
