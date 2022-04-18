package br.com.api.controller;

import br.com.api.models.Empresa;
import br.com.api.models.Holerite;
import br.com.api.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaRepository empresaRepository;

    @GetMapping("/{idEmpresa}")
    public ResponseEntity<Empresa> buscarEmpresa(@PathVariable Long idEmpresa){

        Optional<Empresa> empresaOptional = empresaRepository.findById(idEmpresa);

        if(empresaOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        /*Holerite holerite = new Holerite();

        holerite.getCabecalho().getCod();*/

        return ResponseEntity.status(HttpStatus.OK).body(empresaOptional.get());
    }
}
