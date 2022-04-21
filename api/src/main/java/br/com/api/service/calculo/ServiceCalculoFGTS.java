package br.com.api.service.calculo;

public class ServiceCalculoFGTS {

    private double salarioBruto;
    private int tipo;

    public ServiceCalculoFGTS(double salarioBruto, int tipo) {
        this.salarioBruto = salarioBruto;
        this.tipo = tipo;
    }

    public double calcularContribuicaoFGTS() {
        double contribuicaoFGTS;
        switch (tipo) {
            case 1:
                return contribuicaoFGTS = 0.02 * salarioBruto;
            case 2:
                return contribuicaoFGTS = 0.112 * salarioBruto;
            case 3:
                return contribuicaoFGTS = 0.08 * salarioBruto;
            default:
                return (Double) null;
        }
    }

}
