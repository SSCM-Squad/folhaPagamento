package br.com.api.service.calculo;

public class ServiceCalculoIR {

    double valorDependente = 189.59;
    private final double[] aliquota = { 0.075, 0.15, 0.225, 0.275 };
    private final double[] deducao = { 142.80, 354.80, 636.13, 869.36 };

    double calculoIR(double salarioMenosINSS, int qtdDependentes, double outrosDescontos, double pensaoAlimenticia,
                     double previdenciaSocial) {

        double salarioBaseIR = salarioMenosINSS - (valorDependente * qtdDependentes) - outrosDescontos
                - pensaoAlimenticia - previdenciaSocial;

        if (salarioBaseIR <= 1900) {
            return 0;
        } else if (salarioBaseIR >= 1900.01 && salarioBaseIR <= 2800.00) {
            return salarioBaseIR * aliquota[0] - deducao[0];
        } else if (salarioBaseIR >= 2800.01 && salarioBaseIR <= 3751.00) {
            return salarioBaseIR * aliquota[1] - deducao[1];
        } else if (salarioBaseIR >= 3751.01 && salarioBaseIR <= 4664.00) {
            return salarioBaseIR * aliquota[2] - deducao[2];
        } else {
            return salarioBaseIR * aliquota[3] - deducao[3];
        }
    }


}
