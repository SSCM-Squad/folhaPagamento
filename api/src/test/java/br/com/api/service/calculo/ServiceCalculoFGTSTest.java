package br.com.api.service.calculo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ServiceCalculoFGTSTest {

    @Test
    void testJovemAprendiz() {
        ServiceCalculoFGTS jovemAprendizFGTS = new ServiceCalculoFGTS(1000, 1);
        assertEquals(20, jovemAprendizFGTS.calcularContribuicaoFGTS());
    }

    @Test
    void testTrabalhadoresDomesticos() {
        ServiceCalculoFGTS trabalhadoresDomesticosFGTS = new ServiceCalculoFGTS(5000, 2);
        assertEquals(560, trabalhadoresDomesticosFGTS.calcularContribuicaoFGTS());
    }

    @Test
    void testColaboradoresEmGeral() {
        ServiceCalculoFGTS colaboradoresEmGeralFGTS = new ServiceCalculoFGTS(10000, 3);
        assertEquals(800, colaboradoresEmGeralFGTS.calcularContribuicaoFGTS());
    }


}
