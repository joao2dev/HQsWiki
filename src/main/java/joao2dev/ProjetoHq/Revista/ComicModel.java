package joao2dev.ProjetoHq.Revista;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_Comics")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ComicModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idHQ;
    String tituloHq;
    int anoDeLancamentoHq;
    int edicaoHq;
    String generoHq;
    String sinopseHq;
    String registroDeCriacaoHq;

//  relacionando HQ com EDITORA
    @ManyToOne
    @JoinColumn(name = "editora_id")
    private PublisherModel editora;
//  relacinamento da HQ com AUTOR
    @ManyToMany
    @JoinColumn(name = "autor_id")
    private Autor autored;
//  relacinamento da HQ com PERSONAGEM
    @ManyToMany
    @JoinColumn(name = "personagem_id")
    private Character personagens;
}
