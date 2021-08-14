package br.com.thiago.sistemaemergenciabackendspringboot.Paciente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRestRepository extends JpaRepository<Paciente, Long> {
}