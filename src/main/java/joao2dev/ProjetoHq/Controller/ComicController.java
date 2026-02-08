package joao2dev.ProjetoHq.Controller;

import joao2dev.ProjetoHq.Revista.ComicModel;
import joao2dev.ProjetoHq.Services.ComicService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/revista")
public class ComicController {

    private ComicService comicService;

    public ComicController(ComicService comicService) {
        this.comicService = comicService;
    }

    @GetMapping("/listar")
    public List<ComicModel> listarComics() {
        return comicService.listarComics();
    }

    @GetMapping("/listar/{id}")
    public ComicModel buscarPorId(@PathVariable Long id) {
        return comicService.buscarComicPorId(id);
    }

    @PostMapping("/adicionar")
    public ComicModel adicionarComic(@RequestBody ComicModel comicModel) {
        return comicService.criarComic(comicModel);
    }

    @PutMapping("/editar/{id}")
    public ComicModel editarComic(
            @PathVariable Long id,
            @RequestBody ComicModel comicModel
    ) {
        return comicService.editarComic(id, comicModel);
    }
    @GetMapping("/buscar/{Titulo}")
    public List<ComicModel> buscarComic(String titulo){
        return comicService.buscarComicPorTitulo(titulo);
    }
    @DeleteMapping("/deletar/{id}")
    public void deletarComic(@PathVariable Long id) {
        comicService.deletarComic(id);
    }
}
