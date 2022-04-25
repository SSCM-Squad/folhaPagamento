package br.com.api.service.holerite;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.models.Cabecalho;
import br.com.api.models.Detalhe;
import br.com.api.models.Funcionario;
import br.com.api.models.Holerite;
import br.com.api.models.Rodape;
import br.com.api.repository.HoleriteRepository;
import br.com.api.service.calculo.ServiceCalculoFGTS;
import br.com.api.service.calculo.ServiceCalculoINSS;
import br.com.api.service.calculo.ServiceCalculoIR;

@Service
public class ServiceHolerite {

	@Autowired
	private HoleriteRepository holeriteRepository;

	public Holerite gerarHolerite(Funcionario funcionario, LocalDate data) {
		Holerite holerite = new Holerite();

		holerite.setCabecalho(montarCabecalho(funcionario, data));

		List<Detalhe> detalhes = montarDetalhesVencimento(funcionario);

		detalhes.addAll(montarDetalhesDesconto(funcionario, detalhes));

		holerite.setDetalhes(detalhes);
		
		holerite.setRodape(montarRodape(funcionario, detalhes));
		
		holerite.setFuncionario(funcionario);

		return holeriteRepository.save(holerite);
	}

	private Cabecalho montarCabecalho(Funcionario funcionario, LocalDate data) {
		Cabecalho cabecalho = new Cabecalho();
		cabecalho.setData(data);
		cabecalho.setCargoFuncionario(funcionario.getFuncao());
		cabecalho.setCnpjEmpresa(funcionario.getEmpresa().getCnpj());
		cabecalho.setCod("COD");
		cabecalho.setNomeEmpresa(funcionario.getEmpresa().getRazaoSocial());
		cabecalho.setNomeFuncionario(funcionario.getNome());
		return cabecalho;
	}

	private List<Detalhe> montarDetalhesVencimento(Funcionario funcionario) {

		BigDecimal valorVencimentoPericulosidade = funcionario.getSalario().multiply(funcionario.getAdicionalPericulosidade());
		BigDecimal valorDaHora = funcionario.getSalario().divide(new BigDecimal(funcionario.getJornadaDeTrabalho()));
		BigDecimal vencimentoHoraExtra = valorDaHora.multiply(new BigDecimal(funcionario.getHorasExtras())).multiply(new BigDecimal("1.5"));
		
		Detalhe detalheSalario = new Detalhe();
		Detalhe detalhePlanoDeSaude = new Detalhe();
		Detalhe detalheAjudaDeCusto = new Detalhe();
		Detalhe detalhePericulosidade = new Detalhe();
		Detalhe detalheHorasExtraordinarias = new Detalhe();

		List<Detalhe> detalhesVencimento = new ArrayList<>();

		detalheSalario.setDescricao("Salário");
		detalheSalario.setVencimento(funcionario.getSalario());
		detalheSalario.setReferencia("30 dias");

		detalheAjudaDeCusto.setDescricao("Ajuda de Custo");
		detalheAjudaDeCusto.setVencimento(funcionario.getAjudaDeCusto());
		detalheAjudaDeCusto.setReferencia("-");

		detalhePericulosidade.setDescricao("Periculosidade");
		detalhePericulosidade.setVencimento(valorVencimentoPericulosidade);
		detalhePericulosidade.setReferencia("30%");

		detalheHorasExtraordinarias.setDescricao("Horas Extra Ordinárias");
		detalheHorasExtraordinarias.setVencimento(vencimentoHoraExtra.setScale(2, RoundingMode.HALF_UP));
		detalheHorasExtraordinarias.setReferencia(String.valueOf(funcionario.getHorasExtras()));

		detalhePlanoDeSaude.setDescricao("Plano de Saúde");
		detalhePlanoDeSaude.setDesconto(funcionario.getValorPlanoDeSaude());
		detalhePlanoDeSaude.setReferencia("-");

		detalhesVencimento.add(detalheSalario);
		detalhesVencimento.add(detalheAjudaDeCusto);
		detalhesVencimento.add(detalhePericulosidade);
		detalhesVencimento.add(detalheHorasExtraordinarias);
		detalhesVencimento.add(detalhePlanoDeSaude);

		return detalhesVencimento;
	}

	private List<Detalhe> montarDetalhesDesconto(Funcionario funcionario, List<Detalhe> detalhes) {

		ServiceCalculoIR calculoIR = new ServiceCalculoIR();
		ServiceCalculoINSS calculoINSS = new ServiceCalculoINSS();
		BigDecimal totalVencimentos = detalhes.stream().map(detalhe -> detalhe.getVencimento()).reduce(BigDecimal::add).get();
		BigDecimal totalVencimentosTributaveis = totalVencimentos.subtract(funcionario.getAjudaDeCusto());
		BigDecimal valorINSS = calculoINSS.calcularContribuicaoINSS(totalVencimentosTributaveis);
		BigDecimal totalVtMenosINSS = totalVencimentosTributaveis.subtract(valorINSS);
		HashMap<String, BigDecimal> valorEAliquota = calculoIR.calculoIR(totalVtMenosINSS, funcionario.getDependentes(),BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);

		Detalhe detalheINSS = new Detalhe();
		Detalhe detalheIR = new Detalhe();

		List<Detalhe> detalhesDesconto = new ArrayList<>();

		detalheINSS.setDescricao("INSS");
		detalheINSS.setDesconto(valorINSS);
		detalheINSS.setReferencia(valorINSS.divide(totalVencimentosTributaveis).multiply(new BigDecimal("100")).toString());

		detalheIR.setDescricao("IRRF");
		detalheIR.setDesconto(valorEAliquota.entrySet().stream().map(Map.Entry::getValue).findFirst().get());
		detalheIR.setReferencia(valorEAliquota.entrySet().stream().map(Map.Entry::getKey).findFirst().get());

		detalhesDesconto.add(detalheINSS);
		detalhesDesconto.add(detalheIR);

		return detalhesDesconto;
	}
	
	private Rodape montarRodape(Funcionario funcionario, List<Detalhe> detalhes) {
		
		ServiceCalculoIR calculoIR = new ServiceCalculoIR();
		
		BigDecimal totalVencimentos = detalhes.stream().map(detalhe -> detalhe.getVencimento()).reduce(BigDecimal::add).get();
		BigDecimal totalVencimentosTributaveis = totalVencimentos.subtract(funcionario.getAjudaDeCusto());
		
		Rodape rodape = new Rodape();
		
		rodape.setDependentes(funcionario.getDependentes());
		rodape.setSalarioBase(funcionario.getSalario());
		rodape.setTotalVencimentos(totalVencimentos);
		rodape.setBaseInss(totalVencimentosTributaveis);
		rodape.setValorFgts(ServiceCalculoFGTS.calcularContribuicaoFGTS(totalVencimentosTributaveis, funcionario.getTipo()));
		rodape.setValorLiquido(totalVencimentos);

		BigDecimal totalDescontos = detalhes.stream().map(detalhe -> detalhe.getDesconto()).reduce(BigDecimal::add).get();
		rodape.setTotalDescontos(totalDescontos);

		rodape.setBaseIrpf(calculoIR.getSalarioBaseIR());

		rodape.setValorLiquido(totalVencimentos.subtract(totalDescontos));
		
		return rodape;
	}

}