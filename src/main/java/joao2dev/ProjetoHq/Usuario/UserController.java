package joao2dev.ProjetoHq.Usuario;

import joao2dev.ProjetoHq.dto.UserRequestDTO;
import joao2dev.ProjetoHq.dto.UserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")

public class UserController {
    @Autowired
    private UserService service;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarUsuario(@RequestBody UserRequestDTO user){
        service.registrarUsuario(user);
        return ResponseEntity.ok("usuario registrado com sucesso!");
    }
}
