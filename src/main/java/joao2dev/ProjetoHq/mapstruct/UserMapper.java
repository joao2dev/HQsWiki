package joao2dev.ProjetoHq.mapstruct;

import joao2dev.ProjetoHq.Usuario.UserModel;
import joao2dev.ProjetoHq.dto.UserRequestDTO;
import joao2dev.ProjetoHq.dto.UserResponseDTO;
import lombok.experimental.UtilityClass;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserModel paraUserModel(UserRequestDTO request);
    @Mapping(target = "token", source = "token")
    UserResponseDTO paraUserResponse(UserModel model,String token);

}
