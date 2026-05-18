package joao2dev.ProjetoHq.Usuario;

import joao2dev.ProjetoHq.Services.AuthService;
import joao2dev.ProjetoHq.Services.ComicService;
import joao2dev.ProjetoHq.config.TokenService;
import joao2dev.ProjetoHq.dto.UserRequestDTO;
import joao2dev.ProjetoHq.mapstruct.ComicMapper;
import joao2dev.ProjetoHq.mapstruct.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    @Mock
    private UserRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private TokenService tokenService;

    @Mock
    private AuthService authService;
    @Mock
    private UserMapper mapper;

    @InjectMocks
    private UserService userService;




    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        mapper = Mappers.getMapper(UserMapper.class);
        userService = new UserService(
                repository,
                passwordEncoder,
                authenticationManager,
                mapper,
                tokenService,
                authService
        );
    }

    private UserModel userModel;
    private UserRequestDTO user;

    @BeforeEach
    public void criarUser(){
        userModel = new UserModel();
        user = new UserRequestDTO();
        user.setId(1L);
        user.setNome("joao");
        user.setEmail("joao12345");
        user.setPassword("12345joao");
    }
    //TODO:verificar se o usuario foi criado
    //TODO:verificar se o token do usuario foi criado
}