package br.com.api.controller;

import br.com.api.models.Funcionario;
import br.com.api.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @GetMapping("/{idFuncionario}")
    public ResponseEntity<Funcionario> buscarFuncionario(@PathVariable Long idFuncionario) {

        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findById(idFuncionario);

        if (funcionarioOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(funcionarioOptional.get());
    }

}
