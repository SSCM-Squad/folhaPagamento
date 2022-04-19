package br.com.api.controller;

import br.com.api.models.Empresa;
import br.com.api.models.Funcionario;
<<<<<<< HEAD
import br.com.api.models.Holerite;
=======
>>>>>>> dominio-empresa
import br.com.api.repository.EmpresaRepository;
import br.com.api.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @GetMapping("/{idEmpresa}")
    public ResponseEntity<Empresa> buscarEmpresa(@PathVariable Long idEmpresa){

        Optional<Empresa> empresaOptional = empresaRepository.findById(idEmpresa);

        if(empresaOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(empresaOptional.get());
    }

    @PostMapping("/cadastrar-funcionario")
    @Transactional
    public ResponseEntity<Funcionario> cadastrarFuncionario(@RequestBody Funcionario funcionario) {

        Optional<Empresa> empresa = empresaRepository.findById(1l);

        empresa.ifPresent(funcionario::setEmpresa);

        Funcionario funcionarioSalvo = funcionarioRepository.save(funcionario);

        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioSalvo);
    }

    @GetMapping("/funcionarios")
    public ResponseEntity<List<Funcionario>> listarFuncionario() {

        List<Funcionario> funcionarios = funcionarioRepository.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(funcionarios);
    }

    @GetMapping("funcionario/{idFuncionario}")
    public ResponseEntity<Funcionario> buscarFuncionario(@PathVariable Long idFuncionario) {

        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findById(idFuncionario);

        if (funcionarioOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(funcionarioOptional.get());
    }

    @PutMapping("/atualizar-funcionario/{idFuncionario}")
    @Transactional
    public ResponseEntity<Funcionario> atualizarFuncionario(@RequestBody Funcionario funcionario,
                                                            @PathVariable Long idFuncionario) {

        Optional<Funcionario> funcionarioBuscado = funcionarioRepository.findById(idFuncionario);

        if (funcionarioBuscado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Funcionario funcionario1 = funcionarioBuscado.get();

        funcionario1.setNome(funcionario.getNome());
        funcionario1.setSalario(funcionario.getSalario());
        funcionario1.setFuncao(funcionario.getFuncao());
        funcionario1.setAdicionalPericulosidade(funcionario.getAdicionalPericulosidade());
        funcionario1.setJornadaDeTrabalho(funcionario.getJornadaDeTrabalho());
        funcionario1.setHorasExtras(funcionario.getHorasExtras());
        funcionario1.setDependentes(funcionario.getDependentes());
        funcionario1.setAjudaDeCusto(funcionario.getAjudaDeCusto());
        funcionario1.setValorPlanoDeSaude(funcionario.getValorPlanoDeSaude());

        Funcionario funcionarioSalvo = funcionarioRepository.save(funcionario1);

        return ResponseEntity.status(HttpStatus.OK).body(funcionarioSalvo);
    }

}
