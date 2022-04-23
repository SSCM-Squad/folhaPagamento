package br.com.api.service.holerite;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.api.models.Detalhe;
import br.com.api.models.Funcionario;
import br.com.api.models.Holerite;
import br.com.api.service.calculo.ServiceCalculoFGTS;
import br.com.api.service.calculo.ServiceCalculoINSS;

@Service
public class ServiceHolerite {

	public Holerite gerarHolerite(Funcionario funcionario, LocalDate data) {
		Holerite holerite = new Holerite();
		
		holerite.getCabecalho().setData(data);
		holerite.getCabecalho().setCargoFuncionario(funcionario.getFuncao());
		holerite.getCabecalho().setCnpjEmpresa(funcionario.getEmpresa().getCnpj());
		holerite.getCabecalho().setCod("COD");
		holerite.getCabecalho().setNomeEmpresa(funcionario.getEmpresa().getRazaoSocial());
		holerite.getCabecalho().setNomeFuncionario(funcionario.getNome());
		
		
		Detalhe detalheSalario = new Detalhe();
		detalheSalario.setDescricao("Salário");
		detalheSalario.setVencimento(funcionario.getSalario());
		detalheSalario.setReferencia("30 dias");
		
		Detalhe detalheAjudaDeCusto = new Detalhe();
		detalheAjudaDeCusto.setDescricao("Ajuda de Custo");
		detalheAjudaDeCusto.setVencimento(funcionario.getAjudaDeCusto());
		detalheAjudaDeCusto.setReferencia("-");
		
		Detalhe detalhePericulosidade = new Detalhe();
		BigDecimal valorVencimentoPericulosidade = funcionario.getSalario().multiply(funcionario.getAdicionalPericulosidade());
		detalhePericulosidade.setDescricao("Periculosidade");
		detalhePericulosidade.setVencimento(valorVencimentoPericulosidade);
		detalhePericulosidade.setReferencia("30%");
		
		Detalhe detalheHorasExtraordinarias = new Detalhe();
		BigDecimal valorDaHora = funcionario.getSalario().divide(new BigDecimal(funcionario.getJornadaDeTrabalho()));
		BigDecimal vencimentoHoraExtra = valorDaHora.multiply(new BigDecimal(funcionario.getHorasExtras())).multiply(new BigDecimal("1.5"));
		detalheHorasExtraordinarias.setDescricao("Horas Extra Ordinárias");
		detalheHorasExtraordinarias.setVencimento(vencimentoHoraExtra.setScale(2,RoundingMode.HALF_UP));
		detalheHorasExtraordinarias.setReferencia(String.valueOf(funcionario.getHorasExtras()));
		
		Detalhe detalhePlanoDeSaude = new Detalhe();
		detalhePlanoDeSaude.setDescricao("Plano de Saúde");
		detalhePlanoDeSaude.setDesconto(funcionario.getValorPlanoDeSaude());
		detalhePlanoDeSaude.setReferencia("-");
		
		List<Detalhe> detalhes = new ArrayList<>();
		detalhes.add(detalheSalario);
		detalhes.add(detalheAjudaDeCusto);
		detalhes.add(detalhePericulosidade);
		detalhes.add(detalheHorasExtraordinarias);
		detalhes.add(detalhePlanoDeSaude);
		
		
		holerite.getRodape().setDependentes(funcionario.getDependentes());
		holerite.getRodape().setSalarioBase(funcionario.getSalario());
		BigDecimal totalVencimentos = detalhes.stream().map(detalhe -> detalhe.getVencimento()).reduce(BigDecimal::add).get();
		holerite.getRodape().setTotalVencimentos(totalVencimentos);
		BigDecimal totalVencimentosTributaveis = totalVencimentos.subtract(funcionario.getAjudaDeCusto());
		holerite.getRodape().setTotalDescontos(detalhes.stream().map(detalhe -> detalhe.getDesconto()).reduce(BigDecimal::add).get());
		holerite.getRodape().setValorFgts(ServiceCalculoFGTS.calcularContribuicaoFGTS(totalVencimentosTributaveis, funcionario.getTipo()));
		
		Detalhe detalheINSS = new Detalhe();
		detalheINSS.setDescricao("INSS");
		ServiceCalculoINSS calculoINSS = new ServiceCalculoINSS();
		BigDecimal valorINSS = calculoINSS.calcularContribuicaoINSS(totalVencimentosTributaveis);
		detalheINSS.setDesconto(valorINSS);
		detalheINSS.setReferencia(valorINSS.divide(totalVencimentosTributaveis).multiply(new BigDecimal("100")) .toString());
		detalhes.add(detalheINSS);
		
		Detalhe detalheIR = new Detalhe();
		detalheIR.setDescricao("IRRF");
		detalheIR.setDesconto(valorINSS);
		
		holerite.setDetalhes(detalhes);
		return null;
	}

}
