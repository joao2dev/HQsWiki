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
    Long idPersonagem;
    String nomePersonagem;
    String universoDoPersonagem;
    String poderesDoPersonagem;

//  relacionando  PERSONAGEM com HQ
    @ManyToMany(mappedBy = "personagens")
    private List<ComicModel> revista;
}
