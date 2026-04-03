package joao2dev.ProjetoHq.config;

import lombok.Builder;

@Builder
public record JWTUserData(Long id, String name, String email, String token) {
}
