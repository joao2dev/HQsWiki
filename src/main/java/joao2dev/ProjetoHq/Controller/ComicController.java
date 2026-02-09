package joao2dev.ProjetoHq.Controller;

import joao2dev.ProjetoHq.Services.ComicService;
import joao2dev.ProjetoHq.dto.ComicRequestDTO;
import joao2dev.ProjetoHq.dto.ComicResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/revista")
@AllArgsConstructor
public class ComicController {

    private ComicService comicService;



    @GetMapping("/listar")
    public List<ComicResponseDTO> listarComics() {
        return comicService.listarComics();
    }

    @GetMapping("/listar/{id}")
    public ComicResponseDTO buscarPorId(@PathVariable Long id) {
        return comicService.buscarComicPorId(id);
    }

    @PostMapping("/adicionar")
    public ComicResponseDTO adicionarComic(@RequestBody ComicRequestDTO comicModel) {
        return comicService.criarComic(comicModel);
    }

    @PutMapping("/editar/{id}")
    public ComicResponseDTO editarComic(
            @PathVariable Long id,
            @RequestBody ComicRequestDTO comicModel
    ) {
        return comicService.editarComic(id, comicModel);
    }
  @GetMapping("/buscar/{tituloHq}")
    public List<ComicResponseDTO> buscarComic(@PathVariable String tituloHq){
        return comicService.buscarComicPorTitulo(tituloHq);
    }
    @DeleteMapping("/deletar/{id}")
    public void deletarComic(@PathVariable Long id) {
        comicService.deletarComic(id);
    }
}
