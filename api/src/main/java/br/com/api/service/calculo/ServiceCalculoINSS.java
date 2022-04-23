package br.com.api.service.calculo;

import java.math.BigDecimal;
import java.util.Map;

public class ServiceCalculoINSS {
	private final Map<String, BigDecimal> mapaDeducao = this.inciarValoresDeducao();
	private final Map<String, BigDecimal> mapaAliquota = this.inciarValoresAliquota();

	public BigDecimal calcularContribuicaoINSS(BigDecimal salarioTotalTributavel) {
		
		if (salarioTotalTributavel.compareTo(new BigDecimal("1212")) <= 0) {
			return salarioTotalTributavel.multiply(this.mapaAliquota.get("Faixa0"));
			
		} else if (salarioTotalTributavel.compareTo(new BigDecimal("1212")) > 0 && salarioTotalTributavel.compareTo(new BigDecimal("2427.35")) <= 0) {
			return  salarioTotalTributavel.multiply(this.mapaAliquota.get("Faixa1")).subtract(this.mapaDeducao.get("Faixa1"));
			
		} else if (salarioTotalTributavel.compareTo(new BigDecimal("2427.35")) > 0 && salarioTotalTributavel.compareTo(new BigDecimal("3641.03")) <= 0) {
			return salarioTotalTributavel.multiply(this.mapaAliquota.get("Faixa2")).subtract(this.mapaDeducao.get("Faixa2"));
			
		} else if (salarioTotalTributavel.compareTo(new BigDecimal("3641.03")) > 0 && salarioTotalTributavel.compareTo(new BigDecimal("7087.22")) <= 0) {
			return salarioTotalTributavel.multiply(this.mapaAliquota.get("Faixa3")).subtract(this.mapaDeducao.get("Faixa3"));
			
		} else {
			return new BigDecimal("828.39");
		}
	}
	
	private Map<String, BigDecimal> inciarValoresDeducao() {
		this.mapaDeducao.put("Faixa1", new BigDecimal("18.18"));
		this.mapaDeducao.put("Faixa2", new BigDecimal("91"));
		this.mapaDeducao.put("Faixa3", new BigDecimal("163.82"));
		
		return mapaDeducao;
	}
	
	private Map<String, BigDecimal> inciarValoresAliquota() {
		this.mapaDeducao.put("Faixa0", new BigDecimal("0.075"));
		this.mapaDeducao.put("Faixa1", new BigDecimal("0.09"));
		this.mapaDeducao.put("Faixa2", new BigDecimal("0.12"));
		this.mapaDeducao.put("Faixa3", new BigDecimal("0.14"));
		
		return mapaDeducao;
	}

}
