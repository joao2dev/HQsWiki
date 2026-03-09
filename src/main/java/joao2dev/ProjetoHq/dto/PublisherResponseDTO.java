package joao2dev.ProjetoHq.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class PublisherResponseDTO {
    private Long id;
    private String nome;
    private String descricao;
}
