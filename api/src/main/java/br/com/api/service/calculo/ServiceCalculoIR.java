package br.com.api.service.calculo;

import java.math.BigDecimal;

public class ServiceCalculoIR {

	BigDecimal valorDependente = new BigDecimal("189.59");
    private final BigDecimal[] aliquota = {new BigDecimal("0.075"), new BigDecimal("0.15"), new BigDecimal("0.225"), new BigDecimal("0.275") };
    private final BigDecimal[] deducao = {new BigDecimal("142.80") ,new BigDecimal("354.80") ,new BigDecimal("636.13") ,new BigDecimal("869.36")  };

    BigDecimal calculoIR(BigDecimal salarioMenosINSS, BigDecimal qtdDependentes, BigDecimal outrosDescontos, BigDecimal pensaoAlimenticia,
    		BigDecimal previdenciaSocial) {

        BigDecimal salarioBaseIR = salarioMenosINSS.subtract(valorDependente.multiply(qtdDependentes) ).subtract(outrosDescontos).subtract(pensaoAlimenticia).subtract(previdenciaSocial);

        if (salarioBaseIR.compareTo(new BigDecimal("1900")) <= 0) {
            return BigDecimal.ZERO;
        } else if (salarioBaseIR.compareTo(new BigDecimal("1900.01")) >= 0 && salarioBaseIR.compareTo(new BigDecimal("2800.00")) <= 0) {
        	return salarioBaseIR.multiply(aliquota[0]).subtract(deducao[0]);
        } else if (salarioBaseIR.compareTo(new BigDecimal("2800.01")) >= 0 && salarioBaseIR.compareTo(new BigDecimal("3751.00")) <= 0) {
        	return salarioBaseIR.multiply(aliquota[1]).subtract(deducao[1]);
        } else if (salarioBaseIR.compareTo(new BigDecimal("3751.01")) >= 0 && salarioBaseIR.compareTo(new BigDecimal("4664.00")) <= 0) {
            return salarioBaseIR.multiply(aliquota[2]).subtract(deducao[2]);
        } else {
        	return salarioBaseIR.multiply(aliquota[3]).subtract(deducao[3]);
        }
    }


}
