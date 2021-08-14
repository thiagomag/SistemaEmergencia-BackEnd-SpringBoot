package br.com.thiago.sistemaemergenciabackendspringboot.emergencia;

import br.com.thiago.sistemaemergenciabackendspringboot.Medicamento.Medicamento;
import br.com.thiago.sistemaemergenciabackendspringboot.Paciente.Paciente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmergenciaDTORequest {

    private Long idPaciente;
    private List<Long> idMedicamentos;
    private LocalDateTime horarioDosagem;

    public Emergencia convert(List<Medicamento> medicamentos, Paciente paciente) {
        return Emergencia.builder()
                .medicamentoId(medicamentos)
                .pacienteId(paciente)
                .build();
    }
}
