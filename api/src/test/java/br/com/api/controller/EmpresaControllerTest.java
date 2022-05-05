package br.com.api.controller;

import br.com.api.dto.FormCadastroFuncionario;
import br.com.api.models.Empresa;
import br.com.api.models.Funcionario;
import br.com.api.models.TipoFuncionario;
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

import static org.mockito.ArgumentMatchers.*;
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
                .andExpect(jsonPath("razaoSocial").value("XP_INC"));
    }

    @Test
    @DisplayName("Deve retornar not found ao tentar buscar uma Empresa com o ID informado")
    public void deveRetornarErroAoBuscarEmpresa() throws Exception {

        BDDMockito.given(empresaRepository.findById(anyLong())).willReturn(Optional.empty());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(EMPRESA_URI +"/" + 1L)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Deve retornar uma lista de funcionários")
    public void deveRetornarListaFuncionarios() throws Exception{

        Funcionario funcionario = new Funcionario();

        Empresa empresa = new Empresa();
        funcionario.setEmpresa(empresa);

        List<Funcionario> funcionarios = List.of(funcionario);

        BDDMockito.given(funcionarioRepository.findAll()).willReturn(funcionarios);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(EMPRESA_URI +"/funcionarios")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve retornar um funcionário buscado pelo CPF informado")
    public void deveRetornarFuncionarioPorCpf() throws Exception{

        Funcionario funcionario = new Funcionario();
        funcionario.setId(1L);
        funcionario.setCpf("54897563285");

        Empresa empresa = new Empresa();
        funcionario.setEmpresa(empresa);

        BDDMockito.given(funcionarioRepository.findByCpf(anyString())).willReturn(Optional.of(funcionario));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(EMPRESA_URI +"/funcionario/cpf=" + funcionario.getCpf())
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("cpf").value("54897563285"));
    }

    @Test
    @DisplayName("Deve retornar uma exceção ao não encontrar funcionário com o CPF informado")
    public void deveRetornarErroAoBuscarFuncionarioPorCpf() throws Exception{

        BDDMockito.given(funcionarioRepository.findByCpf(anyString())).willReturn(Optional.empty());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(EMPRESA_URI +"/funcionario/cpf=" + "54897563285")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("erro")
                        .value("Não existe funcionário com o CPF informado"));
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
                .andExpect(jsonPath("adicionalPericulosidade").isBoolean())
                .andExpect(jsonPath("ajudaDeCusto").value(0));
    }

    @Test
    @DisplayName("Deve retornar uma exceção ao não encontrar funcionário  pelo ID informado")
    public void deveRetornarErroAoBuscarFuncionario() throws Exception{

        BDDMockito.given(funcionarioRepository.findById(anyLong())).willReturn(Optional.empty());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(EMPRESA_URI +"/funcionario/" + 1L)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("erro").value("Não existe funcionário com o id informado!"));
    }

    @Test
    @DisplayName("Deve cadastrar um funcionário")
    public void deveCadastrarFuncionario() throws Exception{

        ObjectMapper objectMapper = new ObjectMapper();

        FormCadastroFuncionario cadastroFuncionario = new FormCadastroFuncionario();
        cadastroFuncionario.setNome("Angela");
        cadastroFuncionario.setFuncao("Dev Java");
        cadastroFuncionario.setCpf("54897563285");
        cadastroFuncionario.setAdicionalPericulosidade(true);
        cadastroFuncionario.setAjudaDeCusto(BigDecimal.valueOf(0));
        cadastroFuncionario.setSalario(BigDecimal.valueOf(15000));
        cadastroFuncionario.setJornadaDeTrabalho(8);
        cadastroFuncionario.setDependentes(2);
        cadastroFuncionario.setHorasExtras(0);
        cadastroFuncionario.setValorPlanoDeSaude(new BigDecimal("600"));
        cadastroFuncionario.setTipoFuncionario(TipoFuncionario.COLABORADOR);



        String funcionarioJson = objectMapper.writeValueAsString(cadastroFuncionario);

        BDDMockito.given(funcionarioRepository.save(any(Funcionario.class)))
                .willReturn(cadastroFuncionario.converterFormularioParaEntidade());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(EMPRESA_URI +"/cadastrar-funcionario")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(funcionarioJson);

        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("cpf").value("54897563285"))
                .andExpect(jsonPath("nome").value("Angela"))
                .andExpect(jsonPath("funcao").value("Dev Java"))
                .andExpect(jsonPath("salario").value(15000))
                .andExpect(jsonPath("adicionalPericulosidade").isBoolean())
                .andExpect(jsonPath("ajudaDeCusto").value(0));
    }

    @Test
    @DisplayName("Deve retornar erros de validação ao tentar cadastrar um funcionário")
    public void deveRetornarErrosAoCadastrarFuncionario() throws Exception{

        ObjectMapper objectMapper = new ObjectMapper();

        FormCadastroFuncionario cadastroFuncionario = new FormCadastroFuncionario();
        cadastroFuncionario.setNome("Angela");
        cadastroFuncionario.setFuncao(" ");
        cadastroFuncionario.setCpf("54897563285sdasd");
        cadastroFuncionario.setAdicionalPericulosidade(true);
        cadastroFuncionario.setAjudaDeCusto(BigDecimal.valueOf(0));
        cadastroFuncionario.setSalario(BigDecimal.valueOf(15000));
        cadastroFuncionario.setJornadaDeTrabalho(0);
        cadastroFuncionario.setDependentes(-1);
        cadastroFuncionario.setHorasExtras(-2);
        cadastroFuncionario.setValorPlanoDeSaude(new BigDecimal("0.00"));
        cadastroFuncionario.setTipoFuncionario(null);



        String funcionarioJson = objectMapper.writeValueAsString(cadastroFuncionario);


        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(EMPRESA_URI +"/cadastrar-funcionario")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(funcionarioJson);

        mockMvc.perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("erros").isNotEmpty())
                .andExpect(jsonPath("erros.cpf").value("size must be between 11 and 11"))
                .andExpect(jsonPath("erros.jornadaDeTrabalho")
                        .value("must be greater than 0"));

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
                .andExpect(jsonPath("adicionalPericulosidade").isBoolean())
                .andExpect(jsonPath("ajudaDeCusto").value(0));
    }

    @Test
    @DisplayName("Deve retornar exceção ao não encontrar o funcionário para atualização")
    public void deveRetornarErroAtualizarFuncionario() throws Exception{

        ObjectMapper objectMapper = new ObjectMapper();

        Funcionario funcionario = new Funcionario();
        funcionario.setId(1L);

        String funcionarioJson = objectMapper.writeValueAsString(funcionario);

        BDDMockito.given(funcionarioRepository.findById(anyLong())).willReturn(Optional.empty());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put(EMPRESA_URI +"/atualizar-funcionario/" + funcionario.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(funcionarioJson);

        mockMvc.perform(request)
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("erro")
                        .value("Não existe funcionário com o código informado!"));
    }
}
