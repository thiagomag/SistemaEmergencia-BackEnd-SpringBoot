package br.com.thiago.sistemaemergenciabackendspringboot.emergencia;

import br.com.thiago.sistemaemergenciabackendspringboot.Medicamento.Medicamento;
import br.com.thiago.sistemaemergenciabackendspringboot.Medicamento.MedicamentoRestRepository;
import br.com.thiago.sistemaemergenciabackendspringboot.Paciente.IdPacienteNaoExisteException;
import br.com.thiago.sistemaemergenciabackendspringboot.Paciente.PacienteRestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmergenciaRestService {

    private final EmergenciaRestRepository emergenciaRestRepository;
    private final MedicamentoRestRepository medicamentoRestRepository;
    private final PacienteRestRepository pacienteRestRepository;

    public ResponseEntity<EmergenciaDTOResponse> registrarPosologia(EmergenciaDTORequest emergenciaDTORequest, UriComponentsBuilder uriComponentsBuilder) {
        var paciente = pacienteRestRepository.findById(emergenciaDTORequest.getIdPaciente()).orElseThrow(() ->
                new IdPacienteNaoExisteException(emergenciaDTORequest.getIdPaciente()));
        var medicamentos = medicamentoRestRepository.findAllById(emergenciaDTORequest.getIdMedicamentos());
        for(Medicamento medicameto : medicamentos)
            medicameto.setProximaDose(dateAndHourFormat(emergenciaDTORequest.getHorarioDosagem(), medicameto));
        var emergencia = emergenciaDTORequest.convert(medicamentos, paciente);
        medicamentoRestRepository.saveAll(medicamentos);
        var uri =uriComponentsBuilder.path("/emergencia/{id}").buildAndExpand(emergencia.getId()).toUri();
        return ResponseEntity.created(uri).body(new EmergenciaDTOResponse(emergenciaRestRepository.save(emergencia)));
    }

    private LocalDateTime dateAndHourFormat(LocalDateTime horarioDose, Medicamento medicamento) {
        var dias = LocalDateTime.now().getDayOfMonth() - horarioDose.getDayOfMonth();
        var horas = LocalDateTime.now().getHour() - horarioDose.getHour();
        if(medicamento.getPeriodicidade() == 24) {
            horarioDose = horarioDose.plusDays(dias+1);
        } else if (horas > medicamento.getPeriodicidade()) {
            var resultado = medicamento.getPeriodicidade() - ((((double) horas/(double) medicamento.getPeriodicidade()) - ((int) (horas/medicamento.getPeriodicidade()))) * medicamento.getPeriodicidade());
            System.out.println(resultado);
            horarioDose = horarioDose.plusDays(dias).plusHours((long) (horas+resultado));
        }
        return horarioDose;
    }

    public List<EmergenciaDTOResponse> listarTodos() {
        var emergencias = emergenciaRestRepository.findAll();
        return EmergenciaDTOResponse.convert(emergencias);
    }

    public List<EmergenciaDTOResponse> buscarEmergenciaPorCpfPaciente(String cpf) {
        var emergencias = emergenciaRestRepository.findEmergenciaByPacienteId_Cpf(cpf);
        if (!emergencias.isEmpty()) {
            return EmergenciaDTOResponse.convert(emergencias);
        } else {
            throw new CpfNaoEncontradoException(cpf);
        }
    }

    public EmergenciaDTOResponse emergenciaPorId(Long idEmergencia) {
        var emergencia = emergenciaRestRepository.findById(idEmergencia).orElseThrow(() ->
                new IdEmergenciaNaoExisteException(idEmergencia));
        return new EmergenciaDTOResponse(emergencia);
    }

    public ResponseEntity<?> deletar(Long id) {
        var emergencia = emergenciaRestRepository.findById(id).orElseThrow(() ->
                new IdEmergenciaNaoExisteException(id));
        emergencia.setAtivo(false);
        emergenciaRestRepository.save(emergencia);
        return ResponseEntity.ok().build();
    }
}