package joao2dev.ProjetoHq.Revista;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "comic")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComicModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tituloHq;
    private Integer anolancamento;
    private int edicao;
    private String genero;
    private String sinopse;
    private String registrocriacao;
    private String imgUrl;

    @ElementCollection
    @CollectionTable(
            name = "COMIC_AUTORES",
            joinColumns = @JoinColumn(name = "comic_id")
    )
    @Column(name = "autor_nome")
    private List<String> autores;

    @ElementCollection
    @CollectionTable(
            name = "COMIC_PERSONAGENS",
            joinColumns = @JoinColumn(name = "comic_id")
    )
    @Column(name = "personagem_nome")
    private List<String> personagens;

    private String nomeEditora;
}
