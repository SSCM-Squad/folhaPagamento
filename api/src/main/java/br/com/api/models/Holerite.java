package br.com.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
    @JsonIgnoreProperties("holerite")
    private List<Detalhe> detalhes;

    @Embedded
    private Rodape rodape;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;


    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<Detalhe> getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(List<Detalhe> detalhes) {
        this.detalhes = detalhes;
    }

    public Rodape getRodape() {
        return rodape;
    }

    public void setRodape(Rodape rodape) {
        this.rodape = rodape;
    }

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
