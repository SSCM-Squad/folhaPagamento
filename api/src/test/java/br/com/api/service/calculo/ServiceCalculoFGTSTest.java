package br.com.api.service.calculo;

import org.junit.jupiter.api.Test;

import br.com.api.models.TipoFuncionario;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;


public class ServiceCalculoFGTSTest {

    @Test
    void testJovemAprendiz() {
        assertEquals(new BigDecimal("20.00"), ServiceCalculoFGTS.calcularContribuicaoFGTS(new BigDecimal("1000") , TipoFuncionario.JOVEM_APRENDIZ));
    }

    @Test
    void testTrabalhadoresDomesticos() {
        assertEquals(new BigDecimal("560.000"), ServiceCalculoFGTS.calcularContribuicaoFGTS(new BigDecimal("5000"), TipoFuncionario.TRABALHADOR_DOMESTICO));
    }

    @Test
    void testColaboradoresEmGeral() {
        assertEquals(new BigDecimal("800.00"), ServiceCalculoFGTS.calcularContribuicaoFGTS(new BigDecimal("10000"), TipoFuncionario.COLABORADOR));
    }


}
