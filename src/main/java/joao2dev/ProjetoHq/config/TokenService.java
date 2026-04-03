package joao2dev.ProjetoHq.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import joao2dev.ProjetoHq.Usuario.UserModel;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.math.ec.rfc8032.Ed25519;
import org.bouncycastle.math.ec.rfc8032.Ed448;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;


@Component

public class TokenService {
    @Value("${projetohq.security.secret}")
    private String secret;
    public String generateToken(UserModel userModel){
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withSubject(userModel.getEmail())
                .withClaim("userId",userModel.getId())
                .withClaim("name",userModel.getUsername())
                .withExpiresAt(Instant.now().plusSeconds(86400))
                .withIssuedAt(Instant.now())
                .withIssuer("Projeto HQ")
                .sign(algorithm);
    }

    public Optional<JWTUserData> verifyToken(String token){
        try{
        Algorithm algorithm = Algorithm.HMAC256(secret);

        DecodedJWT jwt = JWT.require(algorithm)
                .build()
                .verify(token);

       return Optional.of(JWTUserData
                .builder()
                .id(jwt.getClaim("userId").asLong())
                .name(jwt.getClaim("name").asString())
                .email(jwt.getSubject())
                .build());
    }catch (JWTVerificationException exception){
        return Optional.empty();
        }
    }
}
