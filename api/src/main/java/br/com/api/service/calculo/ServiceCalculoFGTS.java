package br.com.api.service.calculo;

import java.math.BigDecimal;

import br.com.api.models.TipoFuncionario;

public class ServiceCalculoFGTS {

	public static BigDecimal calcularContribuicaoFGTS(BigDecimal salarioTotalTributavel, TipoFuncionario tipo) {
		BigDecimal contribuicaoFGTS;
		switch (tipo) {
		case JovemAprendiz:
			return contribuicaoFGTS = salarioTotalTributavel.multiply(new BigDecimal("0.02"));
		case TrabalhadorDomestico:
			return contribuicaoFGTS = salarioTotalTributavel.multiply(new BigDecimal("0.112"));
		case Colaborador:
			return contribuicaoFGTS = salarioTotalTributavel.multiply(new BigDecimal("0.08"));
		default:
			return BigDecimal.ZERO;
		}
	}

}
