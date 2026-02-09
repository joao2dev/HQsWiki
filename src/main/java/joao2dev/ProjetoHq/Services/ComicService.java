package joao2dev.ProjetoHq.Services;

import jakarta.transaction.Transactional;
import joao2dev.ProjetoHq.Repositorys.ComicRepository;
import joao2dev.ProjetoHq.Revista.ComicModel;
import joao2dev.ProjetoHq.dto.ComicRequestDTO;
import joao2dev.ProjetoHq.dto.ComicResponseDTO;
import joao2dev.ProjetoHq.mapstruct.ComicMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ComicService {

    private final ComicRepository comicRepository;
    private final ComicMapper mapper;



    // 📌 Criar HQ
    @Transactional
    public ComicResponseDTO criarComic(ComicRequestDTO dto) {

        ComicModel comicModel = mapper.paraComicModel(dto);
        ComicModel salvo = comicRepository.save(comicModel);
        validarComic(comicModel);
        return mapper.paraComicResponseDTO(salvo);
    }

    // 📌 Listar HQs
    public List<ComicResponseDTO> listarComics() {
        return mapper.paraListaComicResponse(comicRepository.findAll());
    }

    // 📌 Buscar por ID
    public ComicResponseDTO buscarComicPorId(Long id) {
        ComicModel comic = comicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("HQ não encontrada"));

        return mapper.paraComicResponseDTO(comic);
    }

    // 📌 Buscar por título
    public List<ComicResponseDTO> buscarComicPorTitulo(String tituloHq) {
        return mapper.paraListaComicResponse(
                comicRepository.findByTituloHqIgnoreCase(tituloHq)
        );
    }

    // 📌 Editar HQ
    @Transactional
    public ComicResponseDTO editarComic(Long id, ComicRequestDTO dto) {

        comicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("HQ não encontrada"));

        ComicModel comicModel = mapper.paraComicModel(dto);
        comicModel.setId(id);

        validarComic(comicModel);


        return mapper.paraComicResponseDTO(
                comicRepository.save(comicModel)
        );
    }

    // 📌 Deletar HQ
    public void deletarComic(Long id) {
        comicRepository.deleteById(id);
    }

    // ================= VALIDACOES =================

    private void validarComic(ComicModel comicModel) {

        if (comicModel.getTituloHq() == null || comicModel.getTituloHq().isBlank()) {
            throw new RuntimeException("Título da HQ é obrigatório.");
        }

        if (comicModel.getAnolancamento() < 0) {
            throw new RuntimeException("Ano de lançamento inválido.");
        }

        if (comicModel.getGenero() == null || comicModel.getGenero().isBlank()) {
            throw new RuntimeException("Gênero da HQ é obrigatório.");
        }

        if (comicModel.getNomeEditora() == null ||
                comicModel.getNomeEditora().isBlank()) {

            throw new RuntimeException("A HQ precisa de uma editora válida.");
        }

        if (comicModel.getAutores() == null || comicModel.getAutores().isEmpty()) {
            throw new RuntimeException("A HQ precisa ter ao menos um autor.");
        }

        if (comicModel.getPersonagens() == null || comicModel.getPersonagens().isEmpty()) {
            throw new RuntimeException("A HQ precisa ter ao menos um personagem.");
        }
    }


}
