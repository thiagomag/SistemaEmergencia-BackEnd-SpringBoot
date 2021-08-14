package br.com.thiago.sistemaemergenciabackendspringboot.Paciente;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PacienteRestService {

    private final PacienteRestRepository pacienteRestRepository;

    public PacienteDTOResponse criarPaciente(PacienteDTORequest pacienteDTORequest) {
        var paciente = pacienteDTORequest.convert();
        return new PacienteDTOResponse(pacienteRestRepository.save(paciente));
    }

    public List<PacienteDTOResponse> listarTodos() {
        List<Paciente> pacienteList = pacienteRestRepository.findAll();
        return PacienteDTOResponse.convert(pacienteList);
    }

    public PacienteDTOResponse findById(Long id) {
        var pacienteOptional = pacienteRestRepository.findById(id).orElseThrow(() -> new IdPacienteNaoExisteException(id));
        return new PacienteDTOResponse(pacienteOptional);
    }
}
