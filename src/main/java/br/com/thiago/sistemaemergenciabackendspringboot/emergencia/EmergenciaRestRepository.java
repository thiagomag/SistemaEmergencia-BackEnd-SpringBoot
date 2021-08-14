package br.com.thiago.sistemaemergenciabackendspringboot.emergencia;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmergenciaRestRepository extends JpaRepository<Emergencia, Long> {
}