package br.com.api.service.calculo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServiceCalculoINSSTest {

    @Test
    void testFaixa1() {
        ServiceCalculoINSS faixa1INSS = new ServiceCalculoINSS(1000);
        assertEquals(75, faixa1INSS.calcularContribuicaoINSS());
    }

    @Test
    void testFaixa2() {
        ServiceCalculoINSS faixa2INSS = new ServiceCalculoINSS(2000);
        assertEquals(161.82, faixa2INSS.calcularContribuicaoINSS());
    }

    @Test
    void testFaixa3() {
        ServiceCalculoINSS faixa3INSS = new ServiceCalculoINSS(3000);
        assertEquals(269, faixa3INSS.calcularContribuicaoINSS());
    }

    @Test
    void testFaixa4() {
        ServiceCalculoINSS faixa4INSS = new ServiceCalculoINSS(4125);
        assertEquals(413.68, faixa4INSS.calcularContribuicaoINSS());
    }

    @Test
    void testFaixa5() {
        ServiceCalculoINSS faixa5INSS = new ServiceCalculoINSS(15000);
        assertEquals(828.39, faixa5INSS.calcularContribuicaoINSS());
    }

}
