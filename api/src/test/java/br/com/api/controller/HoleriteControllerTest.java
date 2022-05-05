package br.com.api.controller;

import br.com.api.models.Cabecalho;
import br.com.api.models.Funcionario;
import br.com.api.models.Holerite;
import br.com.api.repository.FuncionarioRepository;
import br.com.api.repository.HoleriteRepository;
import br.com.api.service.holerite.ServiceHolerite;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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

    @MockBean
    FuncionarioRepository funcionarioRepository;

    @MockBean
    ServiceHolerite serviceHolerite;

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
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve retornar um Not Found ao não encontrar um holerite pelo ID")
    public void deveRetornarNotFound() throws Exception {

        BDDMockito.given(holeriteRepository.findById(anyLong())).willReturn(Optional.empty());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(HOLERITE_URI +"/" + 1L)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Deve retornar uma lista de holerite para o CPF informado")
    public void deveRetornarListaHoleritePorCpf() throws Exception {

        Cabecalho cabecalho = new Cabecalho();

        Holerite holerite = new Holerite();
        holerite.setId(1L);
        holerite.setCabecalho(cabecalho);

        Funcionario funcionario = new Funcionario();
        holerite.setFuncionario(funcionario);

        List<Holerite> holerites = new ArrayList<>(List.of(holerite));

        BDDMockito.given(holeriteRepository.findAllByFuncionarioCpf(anyString())).willReturn(holerites);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(HOLERITE_URI +"/consultar-holerites/" + "1234")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve retornar not found ao não encontrar holerites para o CPF informado")
    public void deveRetornarNotFoundAoListarHoleritesPorCpf() throws Exception {

        List<Holerite> holerites = new ArrayList<>();

        BDDMockito.given(holeriteRepository.findAllByFuncionarioCpf(anyString())).willReturn(holerites);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(HOLERITE_URI +"/consultar-holerites/" + "1234")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isNotFound());
    }



    @Test
    @DisplayName("Deve retornar um holerite buscado pelo CPF e data")
    public void deveRetornarHoleritePorCpfEData() throws Exception {

        Holerite holerite = new Holerite();
        holerite.setId(1L);

        Funcionario funcionario = new Funcionario();
        holerite.setFuncionario(funcionario);

        BDDMockito.given(holeriteRepository.buscarHolerite(anyString(), any(LocalDate.class))).willReturn(Optional.of(holerite));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(HOLERITE_URI +"/consultar-holerite/cpf=1234&data=2022-03-01")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1L));
    }

    @Test
    @DisplayName("Deve retornar not found ao buscar holerite por CPF e data ")
    public void deveRetornarErroAoBuscarHoleritePorCpfEData() throws Exception {

        BDDMockito.given(holeriteRepository.buscarHolerite(anyString(), any(LocalDate.class))).willReturn(Optional.empty());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(HOLERITE_URI +"/consultar-holerite/cpf=1234&data=2022-03-01")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Deve retornar um holerite criado pelo ID funcionário e data")
    public void deveRetornarHoleriteGerado() throws Exception {

        Holerite holerite = new Holerite();
        holerite.setId(1L);

        Funcionario funcionario = new Funcionario();
        holerite.setFuncionario(funcionario);

        BDDMockito.given(funcionarioRepository.findById(anyLong())).willReturn(Optional.of(funcionario));
        BDDMockito.given(serviceHolerite.gerarHolerite(any(Funcionario.class), any(LocalDate.class))).willReturn(holerite);
        BDDMockito.given(holeriteRepository.save(any(Holerite.class))).willReturn(holerite);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(HOLERITE_URI +"/gerar-holerite/1&2022-03-01")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1L));

    }

    @Test
    @DisplayName("Deve retornar erro ao tentar gerar um holerite já criado")
    public void deveRetornarErroAoGerarHolerite() throws Exception {

        BDDMockito.given(holeriteRepository.existsByCabecalhoDataAndFuncionarioId(any(LocalDate.class), anyLong()))
                .willReturn(true);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(HOLERITE_URI +"/gerar-holerite/1&2022-03-01")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("erro")
                        .value("O Holerite referente a essa data e funcionário já foi gerado!"));

    }

    @Test
    @DisplayName("Deve retornar erro ao tentar gerar um holerite para um funcionário inexistente")
    public void deveRetornarErroAoGerarHoleriteFuncionario() throws Exception {

        BDDMockito.given(holeriteRepository.existsByCabecalhoDataAndFuncionarioId(any(LocalDate.class), anyLong()))
                .willReturn(false);
        BDDMockito.given(funcionarioRepository.findById(anyLong())).willReturn(Optional.empty());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(HOLERITE_URI +"/gerar-holerite/1&2022-03-01")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("erro")
                        .value("Não existe funcionário com o código informado!"));

    }
}
