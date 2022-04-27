package br.com.api.service.funcionario;

import br.com.api.dto.FormCadastroFuncionario;
import br.com.api.models.Funcionario;
import br.com.api.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceFuncionario {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Funcionario atualizarFuncionario(Funcionario funcionario, FormCadastroFuncionario funcionarioAtualizado) {

        funcionario.setNome(funcionarioAtualizado.getNome());
        funcionario.setSalario(funcionarioAtualizado.getSalario());
        funcionario.setFuncao(funcionarioAtualizado.getFuncao());
        funcionario.setAdicionalPericulosidade(funcionarioAtualizado.isAdicionalPericulosidade());
        funcionario.setJornadaDeTrabalho(funcionarioAtualizado.getJornadaDeTrabalho());
        funcionario.setHorasExtras(funcionarioAtualizado.getHorasExtras());
        funcionario.setDependentes(funcionarioAtualizado.getDependentes());
        funcionario.setAjudaDeCusto(funcionarioAtualizado.getAjudaDeCusto());
        funcionario.setValorPlanoDeSaude(funcionarioAtualizado.getValorPlanoDeSaude());
        funcionario.setCpf(funcionarioAtualizado.getCpf());

        return  funcionarioRepository.save(funcionario);
    }
}
