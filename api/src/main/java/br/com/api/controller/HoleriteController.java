package br.com.api.controller;

import br.com.api.dto.DTOConsultarHoleriteSimples;
import br.com.api.dto.DTOFuncionarioSimples;
import br.com.api.dto.DTOHoleriteCompleto;
import br.com.api.models.Funcionario;
import br.com.api.models.Holerite;
import br.com.api.repository.FuncionarioRepository;
import br.com.api.repository.HoleriteRepository;
import br.com.api.service.holerite.ServiceHolerite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/holerites")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HoleriteController {

	@Autowired
	private HoleriteRepository holeriteRepository;

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private ServiceHolerite serviceHolerite;

    @GetMapping("/{idHolerite}")
    public ResponseEntity<DTOHoleriteCompleto> buscarHoleritePorId(@PathVariable Long idHolerite){

        Optional<Holerite> holerite = holeriteRepository.findById(idHolerite);

        if(holerite.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new DTOHoleriteCompleto(holerite.get()));
    }

    @GetMapping("/consultar-holerites/{cpf}")
    public ResponseEntity<List<DTOConsultarHoleriteSimples>> buscarHolerites(@PathVariable String cpf){

        List<Holerite> holerites = holeriteRepository.findAllByFuncionarioCpf(cpf);

        if(holerites.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        List<DTOConsultarHoleriteSimples> DTOHolerites = holerites.stream().map(holerite ->
                new DTOConsultarHoleriteSimples(holerite)).collect(Collectors.toList());

        return ResponseEntity.ok(DTOHolerites);
    }

    @GetMapping("/consultar-holerite/cpf={cpf}&data={dataHolerite}")
    public ResponseEntity<DTOHoleriteCompleto> buscarHolerite(@PathVariable String cpf,
    @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataHolerite){

        Optional<Holerite> holerite = holeriteRepository.buscarHolerite(cpf, dataHolerite);

        if(holerite.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new DTOHoleriteCompleto(holerite.get()));

    }

	@PostMapping("/gerar-holerite/{idFuncionario}&{data}")
	public ResponseEntity<DTOHoleriteCompleto> gerarHolerite(@PathVariable Long idFuncionario,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data) {

		Optional<Funcionario> funcionario = funcionarioRepository.findById(idFuncionario);

		if(holeriteRepository.existsByCabecalhoDataAndFuncionarioId(data, idFuncionario)){
			return ResponseEntity.badRequest().build();
		}

		if (funcionario.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Holerite holerite = serviceHolerite.gerarHolerite(funcionario.get(), data);

		return ResponseEntity.ok(new DTOHoleriteCompleto(holerite));
	}

}
