package joao2dev.ProjetoHq.Controller;

import joao2dev.ProjetoHq.Revista.CharacterModel;
import joao2dev.ProjetoHq.Services.CharacterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personagem")
public class CharacterController {
    private CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping("/listar")
    public List<CharacterModel> mostrarPersonagens() {
        return characterService.mostrarPersonagens();
    }

    @GetMapping("/lista/{id}")
    public CharacterModel mostrarPorId(@PathVariable Long id) {
        return characterService.mostrarPersonagemPorId(id);
    }

    @PostMapping("/adicionar")
    public CharacterModel adicionar(@RequestBody CharacterModel characterModel) {
        return characterService.adicionarPersonagem(characterModel);
    }

    @PutMapping("/editar/{id}")
    public CharacterModel editarPersonagem(
            @PathVariable Long id,
            @RequestBody CharacterModel characterModel
    ) {
        return characterService.editarById(id, characterModel);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarById(@PathVariable Long id) {
        characterService.deletarById(id);
    }
 /*   @GetMapping("/buscar/{nome}")
    public List<CharacterModel> buscarPersonagem(@PathVariable String nome){
        return characterService.buscarPersonagem(nome);
    }*/
}
