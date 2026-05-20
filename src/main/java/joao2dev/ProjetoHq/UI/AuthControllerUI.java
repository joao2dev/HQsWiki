package joao2dev.ProjetoHq.UI;

import joao2dev.ProjetoHq.Usuario.UserModel;
import joao2dev.ProjetoHq.Usuario.UserService;
import joao2dev.ProjetoHq.dto.LoginRequest;
import joao2dev.ProjetoHq.dto.UserRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("auth/ui")
@RequiredArgsConstructor

public class AuthControllerUI {
    private final UserService userService;
    @PostMapping("registrar")
    public String registrarUsuario(@ModelAttribute UserRequestDTO userRequestDTO){
        userService.registrarUsuario(userRequestDTO);
        return "auth/login";
    }
    @GetMapping("/registrar")
    public String paginaRegistro() {
        return "auth/registro"; // aponta para templates/auth/registro.html
    }
    @GetMapping("/login")
    public String paginaDeLogin(){
        return "auth/login";
    }

}
