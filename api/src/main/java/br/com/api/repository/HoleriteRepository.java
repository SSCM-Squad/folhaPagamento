package br.com.api.repository;import br.com.api.models.Holerite;import org.springframework.data.jpa.repository.JpaRepository;import org.springframework.data.jpa.repository.Query;import org.springframework.stereotype.Repository;import java.time.LocalDate;import java.util.List;import java.util.Optional;@Repositorypublic interface HoleriteRepository extends JpaRepository<Holerite, Long > {    List<Holerite> findAllByFuncionarioCpf(String cpf);    @Query("FROM Holerite h WHERE h.funcionario.cpf = :cpf AND h.cabecalho.data = :dataHolerite")    Optional<Holerite> buscarHolerite(String cpf, LocalDate dataHolerite);    Optional<Holerite> findByCabecalhoData(LocalDate dataHolerite);}