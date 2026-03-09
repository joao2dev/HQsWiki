package joao2dev.ProjetoHq.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CharacterResponseDTO {
    private Long id;
    private String nome;
    private String descricao;
}
