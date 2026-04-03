package joao2dev.ProjetoHq.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComicRequestDTO {
    private Long id;
    private String tituloHq;
    private Integer anolancamento;
    private int edicao;
    private String genero;
    private String sinopse;
    private String registrocriacao;
    private String imgUrl;



    // pode vir assim: "Stan Lee, Jack Kirby"
    private List<String> autores;

    // pode vir assim: "Homem-Aranha, Venom"
    private List<String> personagens;

    // usuário digita livre
    private String nomeEditora;

}

