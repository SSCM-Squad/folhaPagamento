package br.com.api.service.calculo;

import java.math.BigDecimal;
import java.util.HashMap;


public class ServiceCalculoIR {
	
	private BigDecimal salarioBaseIR;	
	
	BigDecimal valorDependente = new BigDecimal("189.59");
    private final BigDecimal[] aliquota = {new BigDecimal("0.075"), new BigDecimal("0.15"), new BigDecimal("0.225"), new BigDecimal("0.275") };
    private final BigDecimal[] deducao = {new BigDecimal("142.80") ,new BigDecimal("354.80") ,new BigDecimal("636.13") ,new BigDecimal("869.36")  };

    public HashMap<String,BigDecimal> calculoIR(BigDecimal totalVtMenosINSS, int qtdDependentes, BigDecimal outrosDescontos, BigDecimal pensaoAlimenticia,
    		BigDecimal previdenciaSocial) {
    	HashMap<String,BigDecimal> valorEAliquota = new HashMap<>();
    	
        this.salarioBaseIR = totalVtMenosINSS.subtract(valorDependente.multiply(new BigDecimal(qtdDependentes)) ).subtract(outrosDescontos).subtract(pensaoAlimenticia).subtract(previdenciaSocial);

        if (salarioBaseIR.compareTo(new BigDecimal("1900")) <= 0) {
        	valorEAliquota.put("Isento", BigDecimal.ZERO);
            return valorEAliquota;
            
        } else if (salarioBaseIR.compareTo(new BigDecimal("1900.01")) >= 0 && salarioBaseIR.compareTo(new BigDecimal("2800.00")) <= 0) {
        	valorEAliquota.put(aliquota[0].toString(), salarioBaseIR.multiply(aliquota[0]).subtract(deducao[0]));
        	return valorEAliquota;
        	
        } else if (salarioBaseIR.compareTo(new BigDecimal("2800.01")) >= 0 && salarioBaseIR.compareTo(new BigDecimal("3751.00")) <= 0) {
        	valorEAliquota.put(aliquota[1].toString(), salarioBaseIR.multiply(aliquota[1]).subtract(deducao[1]));
        	return valorEAliquota;
        	
        } else if (salarioBaseIR.compareTo(new BigDecimal("3751.01")) >= 0 && salarioBaseIR.compareTo(new BigDecimal("4664.00")) <= 0) {
        	valorEAliquota.put(aliquota[2].toString(), salarioBaseIR.multiply(aliquota[2]).subtract(deducao[2]));
        	return valorEAliquota;
            
        } else {
        	valorEAliquota.put(aliquota[3].toString(), salarioBaseIR.multiply(aliquota[3]).subtract(deducao[3]));
        	return valorEAliquota;
        }
    }

	public BigDecimal getSalarioBaseIR() {
		return salarioBaseIR;
	}
    
    


}
