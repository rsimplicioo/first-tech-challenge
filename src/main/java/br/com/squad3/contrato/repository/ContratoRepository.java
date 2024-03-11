package br.com.squad3.contrato.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.squad3.contrato.entities.Contrato;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, UUID> {
}
