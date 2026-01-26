package joao2dev.ProjetoHq.Revista;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tb_Autores")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idAutor;
    String nomeAutor;
    String nacionalidadeAutor;

    //  relacinamento do AUTOR com HQ
    @ManyToMany(mappedBy = "autor")
    private List<ComicModel> revista;

}
