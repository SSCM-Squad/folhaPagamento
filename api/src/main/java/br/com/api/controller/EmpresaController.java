package br.com.api.controller;

import br.com.api.dto.DTOEmpresa;
import br.com.api.dto.DTOFuncionarioSimples;
import br.com.api.models.Empresa;
import br.com.api.models.Funcionario;

import br.com.api.repository.EmpresaRepository;
import br.com.api.repository.FuncionarioRepository;
import br.com.api.service.funcionario.ServiceFuncionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/funcionarios")
    public ResponseEntity<List<DTOFuncionarioSimples>> listarFuncionario() {

        List<Funcionario> funcionarios = funcionarioRepository.findAll();

        List<DTOFuncionarioSimples> dtofuncionarios = funcionarios.stream()
                .map(funcionario -> new DTOFuncionarioSimples(funcionario)).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(dtofuncionarios);
    }

    @GetMapping("funcionario/{idFuncionario}")
    public ResponseEntity<Funcionario> buscarFuncionario(@PathVariable Long idFuncionario) {

        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findById(idFuncionario);

        if (funcionarioOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(funcionarioOptional.get());
    }

    @PostMapping("/cadastrar-funcionario")
    @Transactional
    public ResponseEntity<Funcionario> cadastrarFuncionario(@RequestBody Funcionario funcionario) {

        Optional<Empresa> empresa = empresaRepository.findById(1l);

        empresa.ifPresent(funcionario::setEmpresa);

        Funcionario funcionarioSalvo = funcionarioRepository.save(funcionario);

        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioSalvo);
    }

    @PutMapping("/atualizar-funcionario/{idFuncionario}")
    @Transactional
    public ResponseEntity<Funcionario> atualizarFuncionario(@RequestBody Funcionario funcionarioAtualizado,
                                                            @PathVariable Long idFuncionario) {

        Optional<Funcionario> funcionarioBuscado = funcionarioRepository.findById(idFuncionario);

        if (funcionarioBuscado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Funcionario funcionarioSalvo = serviceFuncionario.atualizarFuncionario(funcionarioBuscado.get(), funcionarioAtualizado);

        return ResponseEntity.status(HttpStatus.OK).body(funcionarioSalvo);
    }

}
