package joao2dev.ProjetoHq.Revista;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_Autores")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AutorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long idAutor;

    @Column(name = "nome")
    String nomeAutor;

    @Column(name = "nacionalidade")
    String nacionalidadeAutor;

    //  relacinamento do AUTOR com HQ
    @ManyToMany(mappedBy = "autores")

    private List<ComicModel> revista = new ArrayList<>();

}
