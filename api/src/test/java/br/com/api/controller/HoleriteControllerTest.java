package br.com.api.controller;

import br.com.api.models.Funcionario;
import br.com.api.models.Holerite;
import br.com.api.repository.HoleriteRepository;
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

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class HoleriteControllerTest {

    final static String HOLERITE_URI = "/api/holerites";

    @Autowired
    MockMvc mockMvc;

    @MockBean
    HoleriteRepository holeriteRepository;

    @Test
    @DisplayName("Deve retornar um holerite buscado pelo ID")
    public void deveRetornarHolerite() throws Exception {

        Holerite holerite = new Holerite();
        holerite.setId(1L);

        Funcionario funcionario = new Funcionario();
        holerite.setFuncionario(funcionario);

        BDDMockito.given(holeriteRepository.findById(anyLong())).willReturn(Optional.of(holerite));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(HOLERITE_URI +"/" + holerite.getId())
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("funcionario").isNotEmpty());
    }
}
