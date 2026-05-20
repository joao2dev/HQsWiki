package joao2dev.ProjetoHq.Usuario;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import joao2dev.ProjetoHq.Services.AuthService;
import joao2dev.ProjetoHq.dto.LoginRequest;
import joao2dev.ProjetoHq.dto.UserRequestDTO;
import joao2dev.ProjetoHq.dto.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private AuthService authService;

    @PostMapping("/registrar")

    public String registrarUsuario(@ModelAttribute UserRequestDTO user, HttpServletResponse response) {
        // registra o usuário
        service.registrarUsuario(user);

        // redireciona para o login após cadastro
        return "redirect:/auth/ui/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequest user, HttpServletResponse response) {
        String token = service.login(user);


        Cookie cookie = new Cookie("jwt", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(8 * 60 * 60);
        response.addCookie(cookie);

        return "redirect:/comics/ui/listar";
    }
    @GetMapping("/logout")
    public String logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("jwt", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:/auth/ui/login";
    }

}