package br.com.api.models;

import javax.persistence.*;

@Entity
@Table(name = "detalhes")
public class Detalhe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    private String referencia;

    @ManyToOne
    private Holerite holerite;

}
