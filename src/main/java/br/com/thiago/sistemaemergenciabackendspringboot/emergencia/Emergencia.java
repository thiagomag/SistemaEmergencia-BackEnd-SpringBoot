package br.com.thiago.sistemaemergenciabackendspringboot.emergencia;

import br.com.thiago.sistemaemergenciabackendspringboot.Medicamento.Medicamento;
import br.com.thiago.sistemaemergenciabackendspringboot.Paciente.Paciente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emergencia {

    private String id;
    private Paciente paciente;
    private List<Medicamento> medicamentos;
}