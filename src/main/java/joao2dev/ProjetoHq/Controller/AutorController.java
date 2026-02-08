package joao2dev.ProjetoHq.Controller;

import joao2dev.ProjetoHq.Revista.AutorModel;

import joao2dev.ProjetoHq.Services.AutorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutorController {

    private AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @GetMapping("/listar")
    public List<AutorModel> listarAutores(){
        return autorService.listarAutores();
    }
    @GetMapping("/listar/{id}")
    public AutorModel listarAutoresPorId(@PathVariable Long id){
        return autorService.listarAutorPorId(id);
    }
    @PostMapping("/adicionar")
    public AutorModel adicionarAutor(@RequestBody AutorModel autorModel){
        return autorService.criarAutor(autorModel);
    }
    @PutMapping("/editar/{id}")
    public AutorModel editarAutor(@PathVariable Long id , @RequestBody AutorModel autorModel){
        return autorService.editarAutor(id,autorModel);
    }
    @DeleteMapping("/deletar/{id}")
    public void deletarAutor(@PathVariable Long id){
        autorService.deletarAutor(id);
    }
    @GetMapping("/buscar/{nome}")
    public List<AutorModel> buscarPersonagem(@PathVariable String nome){
        return autorService.buscarAutor(nome);
    }
}
