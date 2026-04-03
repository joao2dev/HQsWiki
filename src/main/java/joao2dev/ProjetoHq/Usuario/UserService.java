package joao2dev.ProjetoHq.Usuario;

import joao2dev.ProjetoHq.Services.AuthService;
import joao2dev.ProjetoHq.config.TokenService;
import joao2dev.ProjetoHq.dto.LoginRequest;
import joao2dev.ProjetoHq.dto.UserRequestDTO;
import joao2dev.ProjetoHq.dto.UserResponseDTO;
import joao2dev.ProjetoHq.mapstruct.UserMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Data
public class UserService {
    private UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private UserMapper mapper;
    private final TokenService tokenService;
    private final AuthService authService;

    public ResponseEntity<?> registrarUsuario(UserRequestDTO user){
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        UserModel userCriado = repository.save(mapper.paraUserModel(user));
        String token = tokenService.generateToken(userCriado);
        mapper.paraUserResponse(userCriado,token);
        return ResponseEntity.status(HttpStatus.CREATED).body(token);

    }
    public String login(LoginRequest request){
        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(request.email(),request.password());
        Authentication authenticate = authenticationManager.authenticate(userAndPass);

          UserModel user = (UserModel) authenticate.getPrincipal();
          return tokenService.generateToken(user);


    }
}
