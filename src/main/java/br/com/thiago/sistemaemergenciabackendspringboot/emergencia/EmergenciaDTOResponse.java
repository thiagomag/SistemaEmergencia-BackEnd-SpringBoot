package br.com.thiago.sistemaemergenciabackendspringboot.emergencia;

import br.com.thiago.sistemaemergenciabackendspringboot.Medicamento.Medicamento;
import br.com.thiago.sistemaemergenciabackendspringboot.Paciente.Paciente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmergenciaDTOResponse {

    private Long id;
    private Paciente paciente;
    private List<Medicamento> medicamentos;

    public EmergenciaDTOResponse(Emergencia emergencia) {
        this.id = emergencia.getId();
        this.paciente = emergencia.getPacienteId();
        this.medicamentos = emergencia.getMedicamentoId();
    }

    public static List<EmergenciaDTOResponse> convert(List<Emergencia> emergencias) {
        return emergencias.stream().map(EmergenciaDTOResponse::new).collect(Collectors.toList());
    }
}
