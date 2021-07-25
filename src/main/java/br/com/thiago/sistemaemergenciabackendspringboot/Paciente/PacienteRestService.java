package br.com.thiago.sistemaemergenciabackendspringboot.Paciente;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PacienteRestService {

    private final PacienteRestRepository pacienteRestRepository;

    public Paciente criarPaciente(Paciente paciente) throws IOException {
        paciente.setId(UUID.randomUUID().toString());
        return pacienteRestRepository.inserirArquivo(paciente);
    }

    public List<Paciente> listarTodos() throws IOException {
        return pacienteRestRepository.listAll();
    }
}
