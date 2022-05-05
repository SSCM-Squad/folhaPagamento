package br.com.api.dto;

import br.com.api.models.Funcionario;
import br.com.api.models.TipoFuncionario;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class FormCadastroFuncionario {

    @NotNull @NotEmpty
    private String nome;

    @Positive
    private BigDecimal salario;

    @NotNull @NotEmpty
    private String funcao;

    @NotNull
    private boolean adicionalPericulosidade;

    @NotNull @Positive
    private int jornadaDeTrabalho;

    @NotNull @PositiveOrZero
    private int horasExtras;

    @PositiveOrZero @NotNull
    private int dependentes;

    @NotNull @PositiveOrZero
    private BigDecimal ajudaDeCusto;

    @NotNull @PositiveOrZero
    private BigDecimal valorPlanoDeSaude;

    @NotNull @Size(min = 11, max = 11)
    private String cpf;

    @NotNull
    private TipoFuncionario tipoFuncionario;

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

    public boolean isAdicionalPericulosidade() {
        return adicionalPericulosidade;
    }

    public void setAdicionalPericulosidade(boolean adicionalPericulosidade) {
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public TipoFuncionario getTipoFuncionario() {
        return tipoFuncionario;
    }

    public void setTipoFuncionario(TipoFuncionario tipoFuncionario) {
        this.tipoFuncionario = tipoFuncionario;
    }


    public Funcionario converterFormularioParaEntidade() {
        Funcionario funcionario = new Funcionario();

        funcionario.setNome(this.getNome());
        funcionario.setSalario(this.getSalario());
        funcionario.setFuncao(this.getFuncao());
        funcionario.setAdicionalPericulosidade(this.isAdicionalPericulosidade());
        funcionario.setJornadaDeTrabalho(this.getJornadaDeTrabalho());
        funcionario.setHorasExtras(this.getHorasExtras());
        funcionario.setHorasExtras(this.getHorasExtras());
        funcionario.setDependentes(this.getDependentes());
        funcionario.setAjudaDeCusto(this.getAjudaDeCusto());
        funcionario.setValorPlanoDeSaude(this.getValorPlanoDeSaude());
        funcionario.setCpf(this.getCpf());
        funcionario.setTipo(this.getTipoFuncionario());

        return funcionario;
    }
}
