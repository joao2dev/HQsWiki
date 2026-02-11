package joao2dev.ProjetoHq.UI;

import joao2dev.ProjetoHq.Revista.ComicModel;
import joao2dev.ProjetoHq.Services.ComicService;
import joao2dev.ProjetoHq.dto.ComicRequestDTO;
import joao2dev.ProjetoHq.dto.ComicResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/comics/ui")
@AllArgsConstructor
public class ComicControllerUI {
    private ComicService comicService;

    @GetMapping("/listar")
    public String listarComics(Model model) {
        List<ComicResponseDTO> comics = comicService.listarComics();
        model.addAttribute("comics", comics);
        return "comics/listarComics";
    }


    @GetMapping("detalhes/{id}")
    public String buscarPorId(@PathVariable Long id, Model model) {
        ComicResponseDTO comic = comicService.buscarComicPorId(id);
        model.addAttribute("comic", comic);
        return "comics/detalhes";
    }

    @GetMapping("/adicionar")
    public String adicionarComic( Model model) {
        model.addAttribute("comic",new ComicResponseDTO());
        return "comics/create";
    }
    @PostMapping("/adicionar")
    public String adicionarComic(@ModelAttribute ComicRequestDTO comic){
        comicService.criarComic(comic);
        return "redirect:/comics/ui/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarComic(@PathVariable Long id, Model model){
        ComicResponseDTO comic = comicService.buscarComicPorId(id);
        model.addAttribute("comic",comic);
        return "comics/editarComic";
    }

    @PostMapping("/editar/{id}")
    public String editarComic(
            @PathVariable Long id,
            @ModelAttribute ComicRequestDTO comicModel
    ) {
         comicService.editarComic(id, comicModel);
         return "redirect:/comics/ui/listar";
    }
    @GetMapping("/buscar")
    public String buscarComic(@RequestParam String tituloHq, Model model){
        List<ComicResponseDTO> resultado = comicService.buscarComicPorTitulo(tituloHq);
        model.addAttribute("comics",resultado);
        return "comics/listarComics";
    }
    @GetMapping("/deletar/{id}")
    public String deletarComic(@PathVariable Long id) {
         comicService.deletarComic(id);
         return  "redirect:/comics/ui/listar";
    }
}
