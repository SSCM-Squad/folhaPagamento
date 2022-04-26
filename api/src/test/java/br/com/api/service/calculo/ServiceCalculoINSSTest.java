package br.com.api.service.calculo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

public class ServiceCalculoINSSTest {

    @Test
    void testFaixa1() {
        ServiceCalculoINSS faixa1INSS = new ServiceCalculoINSS();
        assertEquals(new BigDecimal("75.000"), faixa1INSS.calcularContribuicaoINSS(new BigDecimal("1000")));
    }

    @Test
    void testFaixa2() {
        ServiceCalculoINSS faixa2INSS = new ServiceCalculoINSS();
        assertEquals(new BigDecimal("161.82"), faixa2INSS.calcularContribuicaoINSS(new BigDecimal("2000")));
    }

    @Test
    void testFaixa3() {
        ServiceCalculoINSS faixa3INSS = new ServiceCalculoINSS();
        assertEquals(new BigDecimal("269.00"), faixa3INSS.calcularContribuicaoINSS(new BigDecimal("3000")));
    }

    @Test
    void testFaixa4() {
        ServiceCalculoINSS faixa4INSS = new ServiceCalculoINSS();
        assertEquals(new BigDecimal("413.68"), faixa4INSS.calcularContribuicaoINSS(new BigDecimal("4125")));
    }

    @Test
    void testFaixa5() {
        ServiceCalculoINSS faixa5INSS = new ServiceCalculoINSS();
        assertEquals(new BigDecimal("828.39"), faixa5INSS.calcularContribuicaoINSS(new BigDecimal("15000")));
    }

}
