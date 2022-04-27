package br.com.api.controller;

import br.com.api.models.Empresa;
import br.com.api.models.Funcionario;
import br.com.api.repository.EmpresaRepository;
import br.com.api.repository.FuncionarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
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

    @MockBean
    FuncionarioRepository funcionarioRepository;

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
                .andExpect(status().isOk())
                .andExpect(jsonPath("cnpj").value("teste"))
                .andExpect(jsonPath("razaoSocial").value("XP_INC"))
                .andExpect(jsonPath("funcionarios").isNotEmpty());
    }

    @Test
    @DisplayName("Deve retornar uma lista de funcionários")
    public void deveRetornarListaFuncionarios() throws Exception{

        Funcionario funcionario = new Funcionario();

        Empresa empresa = new Empresa();
        funcionario.setEmpresa(empresa);

        List<Funcionario> funcionarios = new ArrayList<>(List.of(funcionario));

        BDDMockito.given(funcionarioRepository.findAll()).willReturn(funcionarios);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(EMPRESA_URI +"/funcionarios")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve retornar um funcionário buscado pelo ID")
    public void deveRetornarFuncionario() throws Exception{

        Funcionario funcionario = new Funcionario();
        funcionario.setId(1L);
        funcionario.setCpf("54897563285");
        funcionario.setNome("Angela");
        funcionario.setFuncao("Função teste");
        funcionario.setAdicionalPericulosidade(true);
        funcionario.setAjudaDeCusto(BigDecimal.valueOf(0));
        funcionario.setSalario(BigDecimal.valueOf(15000));
        funcionario.setJornadaDeTrabalho(8);
        funcionario.setDependentes(2);

        Empresa empresa = new Empresa();
        funcionario.setEmpresa(empresa);

        BDDMockito.given(funcionarioRepository.findById(anyLong())).willReturn(Optional.of(funcionario));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(EMPRESA_URI +"/funcionario/" + funcionario.getId())
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("cpf").value("54897563285"))
                .andExpect(jsonPath("nome").value("Angela"))
                .andExpect(jsonPath("funcao").value("Função teste"))
                .andExpect(jsonPath("salario").value(15000))
                .andExpect(jsonPath("empresa").isNotEmpty())
                .andExpect(jsonPath("adicionalPericulosidade").isBoolean())
                .andExpect(jsonPath("ajudaDeCusto").value(0));
    }

    @Test
    @DisplayName("Deve cadastrar um funcionário")
    public void deveCadastrarFuncionario() throws Exception{

        ObjectMapper objectMapper = new ObjectMapper();

        Funcionario funcionario = new Funcionario();
        funcionario.setId(1L);
        funcionario.setCpf("54897563285");
        funcionario.setNome("Angela");
        funcionario.setFuncao("Função teste");
        funcionario.setAdicionalPericulosidade(true);
        funcionario.setAjudaDeCusto(BigDecimal.valueOf(0));
        funcionario.setSalario(BigDecimal.valueOf(15000));
        funcionario.setJornadaDeTrabalho(8);
        funcionario.setDependentes(2);

        Empresa empresa = new Empresa();
        funcionario.setEmpresa(empresa);

        String funcionarioJson = objectMapper.writeValueAsString(funcionario);

        BDDMockito.given(funcionarioRepository.save(any(Funcionario.class))).willReturn(funcionario);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(EMPRESA_URI +"/cadastrar-funcionario")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(funcionarioJson);

        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("cpf").value("54897563285"))
                .andExpect(jsonPath("nome").value("Angela"))
                .andExpect(jsonPath("funcao").value("Função teste"))
                .andExpect(jsonPath("salario").value(15000))
                .andExpect(jsonPath("empresa").isNotEmpty())
                .andExpect(jsonPath("adicionalPericulosidade").isBoolean())
                .andExpect(jsonPath("ajudaDeCusto").value(0));
    }

    @Test
    @DisplayName("Deve atualizar um funcionário")
    public void deveAtualizarFuncionario() throws Exception{

        ObjectMapper objectMapper = new ObjectMapper();

        Funcionario funcionario = new Funcionario();
        funcionario.setId(1L);
        funcionario.setCpf("54897563285");
        funcionario.setNome("Angela");
        funcionario.setFuncao("Função teste");
        funcionario.setAdicionalPericulosidade(true);
        funcionario.setAjudaDeCusto(BigDecimal.valueOf(0));
        funcionario.setSalario(BigDecimal.valueOf(15000));
        funcionario.setJornadaDeTrabalho(8);
        funcionario.setDependentes(2);

        Empresa empresa = new Empresa();
        funcionario.setEmpresa(empresa);

        String funcionarioJson = objectMapper.writeValueAsString(funcionario);

        BDDMockito.given(funcionarioRepository.findById(anyLong())).willReturn(Optional.of(funcionario));
        BDDMockito.given(funcionarioRepository.save(any(Funcionario.class))).willReturn(funcionario);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put(EMPRESA_URI +"/atualizar-funcionario/" + funcionario.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(funcionarioJson);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("cpf").value("54897563285"))
                .andExpect(jsonPath("nome").value("Angela"))
                .andExpect(jsonPath("funcao").value("Função teste"))
                .andExpect(jsonPath("salario").value(15000))
                .andExpect(jsonPath("empresa").isNotEmpty())
                .andExpect(jsonPath("adicionalPericulosidade").isBoolean())
                .andExpect(jsonPath("ajudaDeCusto").value(0));
    }
}
