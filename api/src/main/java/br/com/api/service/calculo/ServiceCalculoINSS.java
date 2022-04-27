package br.com.api.service.calculo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ServiceCalculoINSS {
	
	HashMap<String,BigDecimal> mapaDeducao = new HashMap<>();
	HashMap<String,BigDecimal> mapaAliquota = new HashMap<>();

	public BigDecimal calcularContribuicaoINSS(BigDecimal salarioTotalTributavel) {
		
		if (salarioTotalTributavel.compareTo(new BigDecimal("1212")) <= 0) {
			return salarioTotalTributavel.multiply(this.inciarValoresAliquota().get("Faixa0"));
			
		} else if (salarioTotalTributavel.compareTo(new BigDecimal("1212")) > 0 && salarioTotalTributavel.compareTo(new BigDecimal("2427.35")) <= 0) {
			return  salarioTotalTributavel.multiply(this.inciarValoresAliquota().get("Faixa1")).subtract(this.inciarValoresDeducao().get("Faixa1"));
			
		} else if (salarioTotalTributavel.compareTo(new BigDecimal("2427.35")) > 0 && salarioTotalTributavel.compareTo(new BigDecimal("3641.03")) <= 0) {
			return salarioTotalTributavel.multiply(this.inciarValoresAliquota().get("Faixa2")).subtract(this.inciarValoresDeducao().get("Faixa2"));
			
		} else if (salarioTotalTributavel.compareTo(new BigDecimal("3641.03")) > 0 && salarioTotalTributavel.compareTo(new BigDecimal("7087.22")) <= 0) {
			return salarioTotalTributavel.multiply(this.inciarValoresAliquota().get("Faixa3")).subtract(this.inciarValoresDeducao().get("Faixa3"));
			
		} else {
			return new BigDecimal("828.39");
		}
	}
	
	private HashMap<String,BigDecimal> inciarValoresDeducao() {
		this.mapaDeducao.put("Faixa1", new BigDecimal("18.18"));
		this.mapaDeducao.put("Faixa2", new BigDecimal("91"));
		this.mapaDeducao.put("Faixa3", new BigDecimal("163.82"));
		
		return mapaDeducao;
	}
	
	private Map<String, BigDecimal> inciarValoresAliquota() {
		this.mapaAliquota.put("Faixa0", new BigDecimal("0.075"));
		this.mapaAliquota.put("Faixa1", new BigDecimal("0.09"));
		this.mapaAliquota.put("Faixa2", new BigDecimal("0.12"));
		this.mapaAliquota.put("Faixa3", new BigDecimal("0.14"));
		
		return mapaAliquota;
	}

}
