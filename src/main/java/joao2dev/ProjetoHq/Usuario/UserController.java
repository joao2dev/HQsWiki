package joao2dev.ProjetoHq.Usuario;

import joao2dev.ProjetoHq.Services.AuthService;
import joao2dev.ProjetoHq.dto.LoginRequest;
import joao2dev.ProjetoHq.dto.UserRequestDTO;
import joao2dev.ProjetoHq.dto.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")

public class UserController {
    @Autowired
    private UserService service;
    private  AuthService authService;
    @PostMapping("/registrar")
    public ResponseEntity<?> registrarUsuario(@RequestBody UserRequestDTO user){

        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrarUsuario(user));


    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest user){
        String token = service.login(user);
        return ResponseEntity.ok(token);
    }
    @GetMapping("/registrar")
    public String paginaRegistro() {
        return "auth/registro"; // aponta para templates/auth/registro.html
    }
}
