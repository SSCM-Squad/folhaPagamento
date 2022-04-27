package br.com.api.service.calculo;

import java.math.BigDecimal;

import br.com.api.models.TipoFuncionario;

public class ServiceCalculoFGTS {

	public static BigDecimal calcularContribuicaoFGTS(BigDecimal salarioTotalTributavel, TipoFuncionario tipo) {
		BigDecimal contribuicaoFGTS;
		switch (tipo) {
		case JOVEM_APRENDIZ:
			return contribuicaoFGTS = salarioTotalTributavel.multiply(new BigDecimal("0.02"));
		case TRABALHADOR_DOMESTICO:
			return contribuicaoFGTS = salarioTotalTributavel.multiply(new BigDecimal("0.112"));
		case COLABORADOR:
			return contribuicaoFGTS = salarioTotalTributavel.multiply(new BigDecimal("0.08"));
		default:
			return BigDecimal.ZERO;
		}
	}

}
