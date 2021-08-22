package br.com.thiago.sistemaemergenciabackendspringboot.Paciente;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PacienteRestService {

    private final PacienteRestRepository pacienteRestRepository;

    public ResponseEntity<PacienteDTOResponse> criarPaciente(PacienteDTORequest pacienteDTORequest,
                                             UriComponentsBuilder uriComponentsBuilder) {
        var paciente = pacienteDTORequest.convert();
        var uri = uriComponentsBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new PacienteDTOResponse(pacienteRestRepository.save(paciente)));
    }

    public ResponseEntity<List<PacienteDTOResponse>> listarTodos() {
        List<Paciente> pacienteList = pacienteRestRepository.findAll();
        return ResponseEntity.ok().body(PacienteDTOResponse.convert(pacienteList));
    }

    public ResponseEntity<PacienteDTOResponse> findById(Long id) {
        var paciente = pacienteRestRepository.findById(id).orElseThrow(() ->
                new IdPacienteNaoExisteException(id));
        return ResponseEntity.ok().body(new PacienteDTOResponse(paciente));
    }

    public ResponseEntity<PacienteDTOResponse> deletarPaciente(Long id) {
        var paciente = pacienteRestRepository.findById(id).orElseThrow(() ->
                new IdPacienteNaoExisteException(id));
        paciente.setAtivo(false);
        pacienteRestRepository.save(paciente);
        return ResponseEntity.ok().body(new PacienteDTOResponse(paciente));
    }
}