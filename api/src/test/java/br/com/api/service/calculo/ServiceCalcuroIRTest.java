package br.com.api.service.calculo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServiceCalcuroIRTest {

    @Test
    void testIsento() {
        ServiceCalculoIR isentoIR = new ServiceCalculoIR();
        assertEquals(0,isentoIR.calculoIR(3000, 2, 500, 500, 100));
    }

    @Test
    void testFaixa1() {
        ServiceCalculoIR faixa1IR = new ServiceCalculoIR();
        assertEquals(60.48074999999997,faixa1IR.calculoIR(3000, 1, 0, 0, 100));
    }

    @Test
    void testFaixa2() {
        ServiceCalculoIR faixa2IR = new ServiceCalculoIR();
        assertEquals(179.2614999999999,faixa2IR.calculoIR(4000, 1, 250, 0, 0));
    }

    @Test
    void testFaixa3() {
        ServiceCalculoIR faixa3IR = new ServiceCalculoIR();
        assertEquals(376.37,faixa3IR.calculoIR(5000, 0, 250, 200, 50));
    }

    @Test
    void testFaixa4() {
        ServiceCalculoIR faixa4IR = new ServiceCalculoIR();
        assertEquals(1501.3654999999999,faixa4IR.calculoIR(10000, 2, 500, 0, 500));
    }

}
