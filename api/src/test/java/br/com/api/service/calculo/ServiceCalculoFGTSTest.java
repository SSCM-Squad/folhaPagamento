package br.com.api.service.calculo;

import org.junit.jupiter.api.Test;

import br.com.api.models.TipoFuncionario;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;


public class ServiceCalculoFGTSTest {

    @Test
    void testJovemAprendiz() {
//        ServiceCalculoFGTS jovemAprendizFGTS = new ServiceCalculoFGTS();
        assertEquals(new BigDecimal("20.00"), ServiceCalculoFGTS.calcularContribuicaoFGTS(new BigDecimal("1000") , TipoFuncionario.JovemAprendiz));
    }

    @Test
    void testTrabalhadoresDomesticos() {
//        ServiceCalculoFGTS trabalhadoresDomesticosFGTS = new ServiceCalculoFGTS();
        assertEquals(new BigDecimal("560.000"), ServiceCalculoFGTS.calcularContribuicaoFGTS(new BigDecimal("5000"), TipoFuncionario.TrabalhadorDomestico));
    }

    @Test
    void testColaboradoresEmGeral() {
//        ServiceCalculoFGTS colaboradoresEmGeralFGTS = new ServiceCalculoFGTS(10000, 3);
        assertEquals(new BigDecimal("800.00"), ServiceCalculoFGTS.calcularContribuicaoFGTS(new BigDecimal("10000"), TipoFuncionario.Colaborador));
    }


}
