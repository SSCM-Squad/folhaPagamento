package models;import javax.persistence.Embeddable;import java.math.BigDecimal;@Embeddablepublic class Rodape {    private BigDecimal totalVencimentos;    private BigDecimal totalDescontos;    private BigDecimal salarioBase;    private BigDecimal baseInss;    private BigDecimal baseFgts;    private BigDecimal baseIrpf;    private int dependentes;    private BigDecimal valorLiquido;    public BigDecimal getTotalVencimentos() {        return totalVencimentos;    }    public void setTotalVencimentos(BigDecimal totalVencimentos) {        this.totalVencimentos = totalVencimentos;    }    public BigDecimal getTotalDescontos() {        return totalDescontos;    }    public void setTotalDescontos(BigDecimal totalDescontos) {        this.totalDescontos = totalDescontos;    }    public BigDecimal getSalarioBase() {        return salarioBase;    }    public void setSalarioBase(BigDecimal salarioBase) {        this.salarioBase = salarioBase;    }    public BigDecimal getBaseInss() {        return baseInss;    }    public void setBaseInss(BigDecimal baseInss) {        this.baseInss = baseInss;    }    public BigDecimal getBaseFgts() {        return baseFgts;    }    public void setBaseFgts(BigDecimal baseFgts) {        this.baseFgts = baseFgts;    }    public BigDecimal getBaseIrpf() {        return baseIrpf;    }    public void setBaseIrpf(BigDecimal baseIrpf) {        this.baseIrpf = baseIrpf;    }    public int getDependentes() {        return dependentes;    }    public void setDependentes(int dependentes) {        this.dependentes = dependentes;    }    public BigDecimal getValorLiquido() {        return valorLiquido;    }    public void setValorLiquido(BigDecimal valorLiquido) {        this.valorLiquido = valorLiquido;    }}