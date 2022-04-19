package br.com.api.repository;

import models.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository  extends JpaRepository < Empresa, Long >{
}
