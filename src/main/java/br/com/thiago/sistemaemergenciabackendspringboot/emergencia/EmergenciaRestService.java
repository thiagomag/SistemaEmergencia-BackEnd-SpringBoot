package br.com.thiago.sistemaemergenciabackendspringboot.emergencia;

import br.com.thiago.sistemaemergenciabackendspringboot.Medicamento.Medicamento;
import br.com.thiago.sistemaemergenciabackendspringboot.Medicamento.MedicamentoRestRepository;
import br.com.thiago.sistemaemergenciabackendspringboot.Paciente.IdPacienteNaoExisteException;
import br.com.thiago.sistemaemergenciabackendspringboot.Paciente.PacienteRestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmergenciaRestService {

    private final EmergenciaRestRepository emergenciaRestRepository;
    private final MedicamentoRestRepository medicamentoRestRepository;
    private final PacienteRestRepository pacienteRestRepository;

    public EmergenciaDTOResponse registrarPosologia(EmergenciaDTORequest emergenciaDTORequest) {
        var paciente = pacienteRestRepository.findById(emergenciaDTORequest.getIdPaciente()).orElseThrow(() ->
                new IdPacienteNaoExisteException(emergenciaDTORequest.getIdPaciente()));
        var medicamentos = medicamentoRestRepository.findAllById(emergenciaDTORequest.getIdMedicamentos());
        for(Medicamento medicameto : medicamentos)
            medicameto.setHorarioDosagem(LocalDateTime.now());
        var emergencia = emergenciaDTORequest.convert(medicamentos, paciente);
        medicamentoRestRepository.saveAll(medicamentos);
        return new EmergenciaDTOResponse(emergenciaRestRepository.save(emergencia));
    }

    public List<Emergencia> listarTodos() {
        return emergenciaRestRepository.findAll();
    }

    public Optional<Emergencia> pacientePorId(Long idPaciente) {
        return emergenciaRestRepository.findById(idPaciente);
    }
}