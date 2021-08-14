package br.com.thiago.sistemaemergenciabackendspringboot.Medicamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicamentoRestRepository extends JpaRepository<Medicamento, Long> {
    List<Medicamento> findByPrincipioAtivoAndFabricante(String principioAtivo, String fabricante);
}
