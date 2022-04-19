package models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "funcionarios")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private BigDecimal salario;

    private String funcao;

    private BigDecimal adicionalPericulosidade;

    private int jornadaDeTrabalho;

    private int horasExtras;

    private int dependentes;

    private BigDecimal ajudaDeCusto;

    private BigDecimal valorPlanoDeSaude;

    @ManyToOne
    @JsonIgnore
    private Empresa empresa;

    @OneToMany
    private List<Holerite> holerites;

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public BigDecimal getAdicionalPericulosidade() {
        return adicionalPericulosidade;
    }

    public void setAdicionalPericulosidade(BigDecimal adicionalPericulosidade) {
        this.adicionalPericulosidade = adicionalPericulosidade;
    }

    public int getJornadaDeTrabalho() {
        return jornadaDeTrabalho;
    }

    public void setJornadaDeTrabalho(int jornadaDeTrabalho) {
        this.jornadaDeTrabalho = jornadaDeTrabalho;
    }

    public int getHorasExtras() {
        return horasExtras;
    }

    public void setHorasExtras(int horasExtras) {
        this.horasExtras = horasExtras;
    }

    public int getDependentes() {
        return dependentes;
    }

    public void setDependentes(int dependentes) {
        this.dependentes = dependentes;
    }

    public BigDecimal getAjudaDeCusto() {
        return ajudaDeCusto;
    }

    public void setAjudaDeCusto(BigDecimal ajudaDeCusto) {
        this.ajudaDeCusto = ajudaDeCusto;
    }

    public BigDecimal getValorPlanoDeSaude() {
        return valorPlanoDeSaude;
    }

    public void setValorPlanoDeSaude(BigDecimal valorPlanoDeSaude) {
        this.valorPlanoDeSaude = valorPlanoDeSaude;
    }

}
