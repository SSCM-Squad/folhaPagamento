package br.com.api;

import br.com.api.controller.EmpresaController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;



@SpringBootTest
@ActiveProfiles("test")
class ApiApplicationTests {

    @Autowired
    private EmpresaController empresaController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(empresaController).isNotNull();
    }



}
