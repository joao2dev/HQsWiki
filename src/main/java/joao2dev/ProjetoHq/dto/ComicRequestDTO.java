package joao2dev.ProjetoHq.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComicRequestDTO {
    private Long id;
    private String tituloHq;
    private Integer anolancamento;
    private int edicao;
    private String genero;
    private String sinopse;
    private String registrocriacao;

    // usuário digita livre
    private String nomeEditora;

    // pode vir assim: "Stan Lee, Jack Kirby"
    private List<String> autores;

    // pode vir assim: "Homem-Aranha, Venom"
    private List<String> personagens;

    private String imgUrl;
}

