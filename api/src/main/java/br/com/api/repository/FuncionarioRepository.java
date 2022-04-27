package br.com.api.repository;

import br.com.api.models.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository <Funcionario, Long>{

    Optional<Funcionario> findByCpf(String cpf);
}
