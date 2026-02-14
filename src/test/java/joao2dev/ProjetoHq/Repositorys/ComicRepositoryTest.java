package joao2dev.ProjetoHq.Repositorys;

import jakarta.persistence.EntityManager;
import jakarta.persistence.GeneratedValue;
import joao2dev.ProjetoHq.Revista.ComicModel;
import joao2dev.ProjetoHq.dto.AutorResponseDTO;
import joao2dev.ProjetoHq.dto.ComicRequestDTO;
import joao2dev.ProjetoHq.dto.ComicResponseDTO;
import joao2dev.ProjetoHq.mapstruct.ComicMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@ActiveProfiles("test")
class ComicRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    ComicRepository comicRepository;

    ComicMapper mapper = Mappers.getMapper(ComicMapper.class);

    @Test
    @DisplayName("Shold get Comic successfully from dataBase")
    void findByTituloHqIgnoreCase() {

        List<String> autores = new ArrayList<>();
        List<String> personagens = new ArrayList<>();

        autores.add("teste");
        personagens.add("teste");

        ComicRequestDTO data =
                new ComicRequestDTO(null,"teste",2000,12,"acao",
                        "teste hq","#dqew","teste",
                        autores,personagens,"egegsegsegse");

        this.createComic(data);

        Optional<ComicModel> comic =
                comicRepository.findById(1L);

        assertThat(comic).isPresent();
    }
    @Test
    @DisplayName("Shold not get Comic successfully from dataBase when Comic not exists")
    void findByTituloHqIgnoreCase2() {


        Optional<ComicModel> comic =
                comicRepository.findById(1L);

        assertThat(comic).isEmpty();
    }

    private ComicResponseDTO createComic(ComicRequestDTO data){

        ComicModel comic1 = mapper.paraComicModel(data);
        entityManager.persist(comic1);
        entityManager.flush();
        return mapper.paraComicResponseDTO(comic1);
    }
}
