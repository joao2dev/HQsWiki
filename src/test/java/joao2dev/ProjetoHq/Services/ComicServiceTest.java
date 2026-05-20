
package joao2dev.ProjetoHq.Services;

import joao2dev.ProjetoHq.Repositorys.ComicRepository;
import joao2dev.ProjetoHq.Revista.ComicModel;
import joao2dev.ProjetoHq.config.TokenService;
import joao2dev.ProjetoHq.dto.ComicRequestDTO;
import joao2dev.ProjetoHq.dto.ComicResponseDTO;
import joao2dev.ProjetoHq.mapstruct.ComicMapper;
import joao2dev.ProjetoHq.mapstruct.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ComicServiceTest {
    @Mock
    private ComicRepository comicRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final AuthService authService;

    private ComicMapper mapper = Mappers.getMapper(ComicMapper.class);

    @Autowired
    @InjectMocks
    private ComicService comicService;


    ComicServiceTest(PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenService tokenService, AuthService authService) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.authService = authService;
    }

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        mapper = Mappers.getMapper(ComicMapper.class);
        comicService = new ComicService(comicRepository, mapper);
    }

    private ComicModel comicModel;
    private ComicRequestDTO comic;
    private ComicRequestDTO comicEditado;

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
        comicEditado = new ComicRequestDTO();

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

        comicEditado.setId(1L);
        comicEditado.setTituloHq("editado");
        comicEditado.setAnolancamento(2000);
        comicEditado.setEdicao(1);
        comicEditado.setGenero("teste");
        comicEditado.setSinopse("teste");
        comicEditado.setRegistrocriacao("#1231");
        comicEditado.setImgUrl("teste");
        comicEditado.setAutores(autores);
        comicEditado.setPersonagens(personagens);
        comicEditado.setNomeEditora("teste");


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

        ArgumentCaptor<ComicModel> argument = ArgumentCaptor.forClass(ComicModel.class);

        verify(comicRepository).save(argument.capture());


        ComicModel comicCriado = argument.getValue();
        assertEquals("teste",comicCriado.getTituloHq());

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

        comicService.editarComic(1L, comicEditado);

        ArgumentCaptor<ComicModel> argumento = ArgumentCaptor.forClass(ComicModel.class);

        verify(comicRepository).save(argumento.capture());

        ComicModel comicSalvo = argumento.getValue();
        assertEquals("editado",comicSalvo.getTituloHq());

        verify(comicRepository, times(1))
                .findById(1L);

        verify(comicRepository, times(1))
                .save(any(ComicModel.class));
    }
//    TODO: verificar se o sistema busca a revista pelo id
}
