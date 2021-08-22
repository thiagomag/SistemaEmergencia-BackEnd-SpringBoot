package br.com.thiago.sistemaemergenciabackendspringboot.emergencia;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmergenciaRestRepository extends JpaRepository<Emergencia, Long> {

    List<Emergencia> findEmergenciaByPacienteId_Cpf(String cpf);
}