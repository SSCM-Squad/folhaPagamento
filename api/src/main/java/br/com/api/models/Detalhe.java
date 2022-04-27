package br.com.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "detalhes")
public class Detalhe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    private String referencia;

    private BigDecimal desconto;

    private BigDecimal vencimento;

    @ManyToOne
    @JsonIgnore
    private Holerite holerite;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public BigDecimal getVencimento() {
        return vencimento;
    }

    public void setVencimento(BigDecimal vencimento) {
        this.vencimento = vencimento;
    }

    public Holerite getHolerite() {
        return holerite;
    }

    public void setHolerite(Holerite holerite) {
        this.holerite = holerite;
    }
}
