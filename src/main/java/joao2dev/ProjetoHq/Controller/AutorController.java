package joao2dev.ProjetoHq.Controller;

import joao2dev.ProjetoHq.Revista.AutorModel;

import joao2dev.ProjetoHq.Services.AutorService;
import joao2dev.ProjetoHq.dto.AutorRequestDTO;
import joao2dev.ProjetoHq.dto.AutorResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autores")
@AllArgsConstructor
public class AutorController {

    private AutorService autorService;

    @GetMapping("/listar")
    public List<AutorResponseDTO> listarAutores(){
        return autorService.listarAutores();
    }
    @GetMapping("/listar/{id}")
    public AutorResponseDTO listarAutoresPorId(@PathVariable Long id){
        return autorService.listarAutorPorId(id);
    }
    @PostMapping("/adicionar")
    public AutorResponseDTO adicionarAutor(@RequestBody AutorRequestDTO autorModel){
        return autorService.criarAutor(autorModel);
    }
    @PutMapping("/editar/{id}")
    public AutorResponseDTO editarAutor(@PathVariable Long id , @RequestBody AutorRequestDTO autorModel){
        return autorService.editarAutor(id,autorModel);
    }
    @DeleteMapping("/deletar/{id}")
    public void deletarAutor(@PathVariable Long id){
        autorService.deletarAutor(id);
    }
    @GetMapping("/buscar/{nome}")
    public List<AutorResponseDTO> buscarPersonagem(@PathVariable String nome){
        return autorService.buscarAutor(nome);
    }
}
