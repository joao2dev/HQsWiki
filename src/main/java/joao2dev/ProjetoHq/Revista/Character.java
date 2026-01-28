package joao2dev.ProjetoHq.Revista;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tb_Characters")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long idPersonagem;

    @Column(name = "nome")
    String nomePersonagem;

    @Column(name = "universo")
    String universoDoPersonagem;

    @Column(name = "poderes")
    String poderesDoPersonagem;

//  relacionando  PERSONAGEM com HQ
    @ManyToMany(mappedBy = "personagens")
    private List<ComicModel> revista;
}
