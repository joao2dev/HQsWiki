
package joao2dev.ProjetoHq.Services;

import joao2dev.ProjetoHq.Repositorys.ComicRepository;
import joao2dev.ProjetoHq.Revista.ComicModel;
import joao2dev.ProjetoHq.dto.ComicRequestDTO;
import joao2dev.ProjetoHq.dto.ComicResponseDTO;
import joao2dev.ProjetoHq.mapstruct.ComicMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ComicServiceTest {
    @Mock
    private ComicRepository comicRepository;

    private ComicMapper mapper = Mappers.getMapper(ComicMapper.class);

    @Autowired
    @InjectMocks
    private ComicService comicService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        mapper = Mappers.getMapper(ComicMapper.class);
        comicService = new ComicService(comicRepository, mapper);
    }

    private ComicModel comicModel;
    private ComicRequestDTO comic;

    private List<String> autores;
    private List<String> personagens;

    @BeforeEach
    void criarComic() {

        autores = new ArrayList<>();
        personagens = new ArrayList<>();

        autores.add("teste");
        personagens.add("teste");

        comicModel = new ComicModel();
        comic = new ComicRequestDTO();

        comic.setId(1L);
        comic.setTituloHq("teste");
        comic.setAnolancamento(2000);
        comic.setEdicao(1);
        comic.setGenero("teste");
        comic.setSinopse("teste");
        comic.setRegistrocriacao("#1231");
        comic.setImgUrl("teste");
        comic.setAutores(autores);
        comic.setPersonagens(personagens);
        comic.setNomeEditora("teste");

        comicModel.setId(1L);
        comicModel.setTituloHq("teste");
        comicModel.setAnolancamento(2000);
        comicModel.setEdicao(1);
        comicModel.setGenero("teste");
        comicModel.setSinopse("teste");
        comicModel.setRegistrocriacao("#1231");
        comicModel.setImgUrl("teste");
        comicModel.setAutores(autores);
        comicModel.setPersonagens(personagens);
        comicModel.setNomeEditora("teste");
    }

    @Test
    void confirmaSeFoiCriadoOComic() {

        when(comicRepository.save(any(ComicModel.class)))
                .thenReturn(comicModel);

        ComicResponseDTO resultado = comicService.criarComic(comic);

        verify(comicRepository, times(1))
                .save(any(ComicModel.class));

        assertNotNull(resultado);
    }

    @Test
    void editarComic() {

        when(comicRepository.findById(1L))
                .thenReturn(Optional.of(comicModel));

        when(comicRepository.save(any(ComicModel.class)))
                .thenReturn(comicModel);

        comicService.editarComic(1L, comic);

        verify(comicRepository, times(1))
                .findById(1L);

        verify(comicRepository, times(1))
                .save(any(ComicModel.class));
    }
//    TODO: verificar se o sistema busca a revista pelo id
}
