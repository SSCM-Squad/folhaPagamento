package br.com.api.controller;

import br.com.api.models.Empresa;
import br.com.api.models.Funcionario;
import br.com.api.repository.EmpresaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class EmpresaControllerTest {

    final static String EMPRESA_URI = "/api/empresas";


    @Autowired
    MockMvc mockMvc;

    @MockBean
    EmpresaRepository empresaRepository;

    @Test
    @DisplayName("Deve retornar o Json de uma Empresa buscada pelo ID")
    public void deveRetornarEmpresa() throws Exception {

        Empresa empresa = new Empresa();
        empresa.setId(1L);
        empresa.setCnpj("teste");
        empresa.setRazaoSocial("XP_INC");

        Funcionario funcionario = new Funcionario();
        empresa.setFuncionarios(Arrays.asList(funcionario));

        BDDMockito.given(empresaRepository.findById(anyLong())).willReturn(Optional.of(empresa));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(EMPRESA_URI +"/" + empresa.getId())
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("cnpj").value("teste"))
                .andExpect(jsonPath("razaoSocial").value("XP_INC"))
                .andExpect(jsonPath("funcionarios").isNotEmpty());

    }
}
