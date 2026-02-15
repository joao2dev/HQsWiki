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


    @Test
    void criarComic() {

        List<String> autores = new ArrayList<>();
        List<String> personagens = new ArrayList<>();
        autores.add("teste");
        personagens.add("teste");
        ComicRequestDTO comic = new ComicRequestDTO();
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


        ComicModel comicModel = new ComicModel();
        ComicResponseDTO response = new ComicResponseDTO();

        when(comicRepository.save(any())).thenReturn(comicModel);

        ComicResponseDTO resultado = comicService.criarComic(comic);

        verify(comicRepository, times(1)).save(any(ComicModel.class));

        assertEquals(response, resultado);
    }

    @Test
    void editarComic() {
        List<String> autores = new ArrayList<>();
        List<String> personagens = new ArrayList<>();
        autores.add("teste");
        personagens.add("teste");
        ComicRequestDTO comic1 = new ComicRequestDTO();
        comic1.setId(1L);
        comic1.setTituloHq("teste");

        ComicModel comicExistente = new ComicModel();
        comicExistente.setId(1L);

        when(comicRepository.findById(1L)).thenReturn(Optional.of(comicExistente));
        when(comicRepository.save(any())).thenReturn(comicExistente);

        verify(comicRepository,times(1)).findById(1L);
        verify(comicRepository,times(1)).save(any());


    }
}