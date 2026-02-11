package joao2dev.ProjetoHq.mapstruct;

import joao2dev.ProjetoHq.Revista.ComicModel;
import joao2dev.ProjetoHq.dto.ComicRequestDTO;
import joao2dev.ProjetoHq.dto.ComicResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ComicMapper {
    @Mapping(source = "anolancamento", target = "anolancamento")
    ComicModel paraComicModel(ComicRequestDTO dto);

    ComicResponseDTO paraComicResponseDTO(ComicModel model);

    List<ComicResponseDTO> paraListaComicResponse(List<ComicModel> list);
}
