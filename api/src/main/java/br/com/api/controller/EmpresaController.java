package br.com.api.controller;

import br.com.api.dto.DTOEmpresa;
import br.com.api.dto.DTOFuncionarioCompleto;
import br.com.api.dto.DTOFuncionarioSimples;
import br.com.api.dto.FormCadastroFuncionario;
import br.com.api.exception.FuncionarioNaoEncontradoException;
import br.com.api.models.Empresa;
import br.com.api.repository.EmpresaRepository;
import br.com.api.models.Funcionario;

import br.com.api.repository.EmpresaRepository;
import br.com.api.repository.FuncionarioRepository;
import br.com.api.service.funcionario.ServiceFuncionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/empresas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EmpresaController {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private ServiceFuncionario serviceFuncionario;

    @GetMapping("/{idEmpresa}")
    public ResponseEntity<DTOEmpresa> buscarEmpresa(@PathVariable Long idEmpresa){

        Optional<Empresa> empresaOptional = empresaRepository.findById(idEmpresa);

        if(empresaOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(new DTOEmpresa(empresaOptional.get()));
    }

    @GetMapping("/funcionario/cpf={cpf}")
    public ResponseEntity<DTOFuncionarioCompleto> buscarFuncionarioPorCpf(@PathVariable String cpf){

        Optional<Funcionario> funcionario = funcionarioRepository.findByCpf(cpf);

        if(funcionario.isEmpty()){
           throw new FuncionarioNaoEncontradoException("Não existe funcionário com o CPF informado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(new DTOFuncionarioCompleto(funcionario.get()));
    }

    @GetMapping("/funcionarios")
    public ResponseEntity<List<DTOFuncionarioSimples>> listarFuncionario() {

        List<Funcionario> funcionarios = funcionarioRepository.findAll();

        List<DTOFuncionarioSimples> dtofuncionarios = funcionarios.stream()
                .map(funcionario -> new DTOFuncionarioSimples(funcionario)).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(dtofuncionarios);
    }

    @GetMapping("/funcionario/{idFuncionario}")
    public ResponseEntity<DTOFuncionarioCompleto> buscarFuncionario(@PathVariable Long idFuncionario) {

        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findById(idFuncionario);

        if (funcionarioOptional.isEmpty()) {
            throw new FuncionarioNaoEncontradoException("Não existe funcionário com o id informado!");
        }

        DTOFuncionarioCompleto dtoFuncionario = new DTOFuncionarioCompleto(funcionarioOptional.get());

        return ResponseEntity.status(HttpStatus.OK).body(dtoFuncionario);
    }

    @PostMapping("/cadastrar-funcionario")
    @Transactional
    public ResponseEntity<DTOFuncionarioCompleto> cadastrarFuncionario(@RequestBody @Valid FormCadastroFuncionario
                                                                                   funcionarioFormulario) {

        Funcionario funcionarioModelo = funcionarioFormulario.converterFormularioParaEntidade();

        Optional<Empresa> empresa = empresaRepository.findById(1l);
        empresa.ifPresent(funcionarioModelo::setEmpresa);

        Funcionario funcionarioSalvo = funcionarioRepository.save(funcionarioModelo);

        return ResponseEntity.status(HttpStatus.CREATED).body(new DTOFuncionarioCompleto(funcionarioSalvo));
    }

    @PutMapping("/atualizar-funcionario/{idFuncionario}")
    @Transactional
    public ResponseEntity<DTOFuncionarioCompleto> atualizarFuncionario(@RequestBody FormCadastroFuncionario funcionarioAtualizado,
                                                            @PathVariable Long idFuncionario) {

        Optional<Funcionario> funcionarioBuscado = funcionarioRepository.findById(idFuncionario);

        if (funcionarioBuscado.isEmpty()) {
            throw new FuncionarioNaoEncontradoException("Não existe funcionário com o código informado!");
        }

        Funcionario funcionarioSalvo = serviceFuncionario.atualizarFuncionario(funcionarioBuscado.get(), funcionarioAtualizado);

        return ResponseEntity.status(HttpStatus.OK).body(new DTOFuncionarioCompleto(funcionarioSalvo));
    }

}
