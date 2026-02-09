package joao2dev.ProjetoHq.Services;

import joao2dev.ProjetoHq.Repositorys.AutorRepository;
import joao2dev.ProjetoHq.Revista.AutorModel;
import joao2dev.ProjetoHq.dto.AutorRequestDTO;
import joao2dev.ProjetoHq.dto.AutorResponseDTO;
import joao2dev.ProjetoHq.mapstruct.AutorMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@AllArgsConstructor
@Service
public class AutorService {
    private final AutorRepository autorRepository;
    private final AutorMapper mapper;


    //    Criar autor
    public AutorResponseDTO criarAutor(AutorRequestDTO autorModel){
        return mapper.paraAutorResponseDTO(autorRepository.save(mapper.paraAutorModel(autorModel)));

    }
//   Listar autores
    public List<AutorResponseDTO> listarAutores(){
        return mapper.paraListarAutorResponse(autorRepository.findAll());
    }
//    listar autor por id
    public AutorResponseDTO listarAutorPorId(Long id){
        AutorModel autor = autorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("nao ha autores aqui"));
        return mapper.paraAutorResponseDTO(autor);
    }
//  Deletar autor
    public void deletarAutor(Long id){
        autorRepository.deleteById(id);
    }
//  Editar autor
    public AutorResponseDTO editarAutor(Long id, AutorRequestDTO autorModel){
        if (autorRepository.existsById(id)){
            autorModel.setId(id);
            return mapper.paraAutorResponseDTO(autorRepository.save(mapper.paraAutorModel(autorModel)));
        }
            return null;

    }
   public List<AutorResponseDTO> buscarAutor(String nome){
        return mapper.paraListarAutorResponse(autorRepository.findByNomeIgnoreCase(nome));
    }
}
