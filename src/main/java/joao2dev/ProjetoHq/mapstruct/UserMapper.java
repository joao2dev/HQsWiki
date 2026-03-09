package joao2dev.ProjetoHq.mapstruct;

import joao2dev.ProjetoHq.Usuario.UserModel;
import joao2dev.ProjetoHq.dto.UserRequestDTO;
import joao2dev.ProjetoHq.dto.UserResponseDTO;
import lombok.experimental.UtilityClass;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserModel paraUserModel(UserRequestDTO request);
    UserResponseDTO paraUserResponse(UserModel model);
}
