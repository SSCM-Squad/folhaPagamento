package br.com.api.service.holerite;

import br.com.api.models.*;
import br.com.api.repository.EmpresaRepository;
import br.com.api.repository.FuncionarioRepository;
import br.com.api.repository.HoleriteRepository;
import br.com.api.service.funcionario.ServiceFuncionario;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
public class ServiceHoleriteTest {

    @Autowired
    private ServiceHolerite serviceHolerite;

    @Autowired
    private HoleriteRepository holeriteRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    private Funcionario funcionario;

   @BeforeEach
   public void setUp(){

       Empresa empresa = new Empresa();
       empresa.setCnpj("00.145.652/0001-98");
       empresa.setRazaoSocial("Empresa Empresa");

       empresaRepository.save(empresa);

       funcionario = new Funcionario();
       funcionario.setNome("João");
       funcionario.setSalario(BigDecimal.valueOf(5000.00));
       funcionario.setFuncao("Desenvolvedor");
       funcionario.setAdicionalPericulosidade(true);
       funcionario.setJornadaDeTrabalho(220);
       funcionario.setHorasExtras(20);
       funcionario.setDependentes(3);
       funcionario.setAjudaDeCusto(BigDecimal.valueOf(500));
       funcionario.setValorPlanoDeSaude(BigDecimal.valueOf(800));
       funcionario.setTipo(TipoFuncionario.COLABORADOR);
       funcionario.setEmpresa(empresa);

       funcionarioRepository.save(funcionario);
   }

    @Test
    @DisplayName("Deve gerar cabeçalho de acordo com informações do funcionário")
    public void gerarCabecalhoTest(){

        Holerite holerite = serviceHolerite.gerarHolerite(funcionario, LocalDate.ofEpochDay(2022 - 02 - 01));

        Assertions.assertThat(holerite.getCabecalho()).isNotNull();
    }

    @Test
    @DisplayName("Deve gerar detalhe de acordo com informações do funcionário")
    public void gerarDetalhesVencimentoTest(){

        Holerite holerite = serviceHolerite.gerarHolerite(funcionario, LocalDate.ofEpochDay(2022 - 02 - 01));

        List<Detalhe> detalhes = holerite.getDetalhes().stream()
                .filter(detalhe -> detalhe.getVencimento() != null).collect(Collectors.toList());

        Assertions.assertThat(detalhes.size()).isEqualTo(4);
    }

    @Test
    @DisplayName("Deve gerar detalhe de acordo com informações do funcionário")
    public void gerarDetalhesDescontoTest(){

        Holerite holerite = serviceHolerite.gerarHolerite(funcionario, LocalDate.ofEpochDay(2022 - 02 - 01));

        List<Detalhe> detalhes = holerite.getDetalhes().stream()
                .filter(detalhe -> detalhe.getDesconto() != null).collect(Collectors.toList());

        Assertions.assertThat(detalhes.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("Deve gerar detalhe de acordo com informações do funcionário")
    public void gerarRodapeTest(){

        Holerite holerite = serviceHolerite.gerarHolerite(funcionario, LocalDate.ofEpochDay(2022 - 02 - 01));

        Assertions.assertThat(holerite.getRodape()).isNotNull();
    }



}
