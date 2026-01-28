package joao2dev.ProjetoHq.Revista;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tb_Publishers")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PublisherModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long idEditora;

    @Column(name = "nome")
    String nomeDaEditora;

    @Column(name = "pais")
    String PaísDaEditora;

    //  relacionando  EDITORA com HQ
    @OneToMany(mappedBy = "editora")
    private List<ComicModel> revista;
}
