package joao2dev.ProjetoHq.Services;

import jakarta.transaction.Transactional;
import joao2dev.ProjetoHq.Repositorys.ComicRepository;
import joao2dev.ProjetoHq.Revista.ComicModel;
import joao2dev.ProjetoHq.Usuario.UserModel;
import joao2dev.ProjetoHq.Usuario.UserRepository;
import joao2dev.ProjetoHq.config.JWTUserData;
import joao2dev.ProjetoHq.dto.ComicRequestDTO;
import joao2dev.ProjetoHq.dto.ComicResponseDTO;
import joao2dev.ProjetoHq.mapstruct.ComicMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ComicService {

    private final ComicRepository comicRepository;
    private final ComicMapper mapper;
    private UserRepository userRepository;




    @Transactional
    public ComicResponseDTO criarComic(ComicRequestDTO dto) {
        JWTUserData userData = (JWTUserData) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        UserModel usuario = userRepository.findByEmail(userData.email());
        ComicModel comicModel = mapper.paraComicModel(dto);
        ComicModel salvo = comicRepository.save(comicModel);
        validarComic(comicModel);
        comicModel.setUsuario(usuario);
        return mapper.paraComicResponseDTO(salvo);
    }


    public List<ComicResponseDTO> listarComics() {
        JWTUserData userData = (JWTUserData) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        UserModel usuario = userRepository.findByEmail(userData.email());
        return comicRepository.findByUsuario(usuario)
                .stream()
                .map(mapper::paraComicResponseDTO)
                .toList();
    }


    public ComicResponseDTO buscarComicPorId(Long id) {
        ComicModel comic = comicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("HQ não encontrada"));

        return mapper.paraComicResponseDTO(comic);
    }


    public List<ComicResponseDTO> buscarComicPorTitulo(String tituloHq) {
        JWTUserData userData = (JWTUserData) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        UserModel usuario = userRepository.findByEmail(userData.email());

        return mapper.paraListaComicResponse(
                comicRepository.findByTituloHqContainingIgnoreCaseAndUsuario(tituloHq, usuario)

        );
    }


    @Transactional
    public ComicResponseDTO editarComic(Long id, ComicRequestDTO dto) {

        JWTUserData userData = (JWTUserData) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        ComicModel comicExistente = comicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("HQ não encontrada"));


        UserModel usuario = userRepository.findByEmail(userData.email());
        if (!comicExistente.getUsuario().getId().equals(usuario.getId())){
            throw new RuntimeException("Você não tem permissão para editar essa HQ.");
        }
        ComicModel comicModel = mapper.paraComicModel(dto);
        comicModel.setId(id);
        comicModel.setUsuario(usuario);
        validarComic(comicModel);


        return mapper.paraComicResponseDTO(
                comicRepository.save(comicModel)
        );
    }


    public void deletarComic(Long id) {
        JWTUserData userData = (JWTUserData) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        UserModel usuario = userRepository.findByEmail(userData.email());
        ComicModel comic = comicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("HQ não encontrada"));

        if (!comic.getUsuario().getId().equals(usuario.getId())) {
            throw new RuntimeException("Você não tem permissão para deletar essa HQ.");
        }
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
