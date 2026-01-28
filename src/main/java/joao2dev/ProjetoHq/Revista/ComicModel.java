package joao2dev.ProjetoHq.Revista;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tb_Comics")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ComicModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long idHQ;

    @Column(name = "titulo")
    String tituloHq;

    @Column(name = "ano_lancamento")
    int anoDeLancamentoHq;

    @Column(name = "edicao")
    int edicaoHq;

    @Column(name = "genero")
    String generoHq;

    @Column(name = "sinopse")
    String sinopseHq;

    @Column(name = "registro")
    String registroDeCriacaoHq;

    // HQ → EDITORA
    @ManyToOne
    @JoinColumn(name = "editora_id")
    private PublisherModel editora;

    // HQ ↔ AUTORES
    @ManyToMany
    @JoinTable(
            name = "hq_autores",
            joinColumns = @JoinColumn(name = "hq_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autor> autores;

    // HQ ↔ PERSONAGENS
    @ManyToMany
    @JoinTable(
            name = "hq_personagens",
            joinColumns = @JoinColumn(name = "hq_id"),
            inverseJoinColumns = @JoinColumn(name = "personagem_id")
    )
    private List<Character> personagens;
}
