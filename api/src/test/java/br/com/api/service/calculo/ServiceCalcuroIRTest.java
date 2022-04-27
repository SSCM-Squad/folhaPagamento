package br.com.api.service.calculo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Map;

public class ServiceCalcuroIRTest {

    @Test
    void testIsento() {
        ServiceCalculoIR isentoIR = new ServiceCalculoIR();
        assertEquals(new BigDecimal("0"),isentoIR.calculoIR(new BigDecimal("3000"), 2, new BigDecimal("500"), new BigDecimal("500"), new BigDecimal("100")).entrySet().stream().map(Map.Entry::getValue).findFirst().get());
    }

    @Test
    void testFaixa1() {
        ServiceCalculoIR faixa1IR = new ServiceCalculoIR();
        assertEquals(new BigDecimal("60.48075"),faixa1IR.calculoIR(new BigDecimal("3000"), 1, new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("100")).entrySet().stream().map(Map.Entry::getValue).findFirst().get());
    }

    @Test
    void testFaixa2() {
        ServiceCalculoIR faixa2IR = new ServiceCalculoIR();
        assertEquals(new BigDecimal("179.2615"),faixa2IR.calculoIR(new BigDecimal("4000"), 1, new BigDecimal("250"), new BigDecimal("0"), new BigDecimal("0")).entrySet().stream().map(Map.Entry::getValue).findFirst().get());
    }

    @Test
    void testFaixa3() {
        ServiceCalculoIR faixa3IR = new ServiceCalculoIR();
        assertEquals(new BigDecimal("376.37000"),faixa3IR.calculoIR(new BigDecimal("5000"), 0, new BigDecimal("250"), new BigDecimal("200"), new BigDecimal("50")).entrySet().stream().map(Map.Entry::getValue).findFirst().get());
    }

    @Test
    void testFaixa4() {
        ServiceCalculoIR faixa4IR = new ServiceCalculoIR();
        assertEquals(new BigDecimal("1501.36550"),faixa4IR.calculoIR(new BigDecimal("10000"), 2, new BigDecimal("500"), new BigDecimal("0"), new BigDecimal("500")).entrySet().stream().map(Map.Entry::getValue).findFirst().get());
    }

}
