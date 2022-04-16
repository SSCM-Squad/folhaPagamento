package br.com.api.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "holerites")
public class Holerite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Cabecalho cabecalho;

    @OneToMany(mappedBy = "holerite")
    private List<Detalhe> detalhes;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cabecalho getCabecalho() {
        return cabecalho;
    }

    public void setCabecalho(Cabecalho cabecalho) {
        this.cabecalho = cabecalho;
    }
}
