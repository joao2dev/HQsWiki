package joao2dev.ProjetoHq.Usuario;

import joao2dev.ProjetoHq.dto.UserRequestDTO;
import joao2dev.ProjetoHq.dto.UserResponseDTO;
import joao2dev.ProjetoHq.mapstruct.UserMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Data
public class UserService {
    private UserRepository repository;
    private UserMapper mapper;

    public ResponseEntity<UserResponseDTO> registrarUsuario(UserRequestDTO user){
        UserModel userCriado = repository.save(mapper.paraUserModel(user));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.paraUserResponse(userCriado));

    }
}
