package br.com.thiago.sistemaemergenciabackendspringboot.emergencia;

import br.com.thiago.sistemaemergenciabackendspringboot.Medicamento.Medicamento;
import br.com.thiago.sistemaemergenciabackendspringboot.Medicamento.MedicamentoRestRepository;
import br.com.thiago.sistemaemergenciabackendspringboot.Paciente.Paciente;
import br.com.thiago.sistemaemergenciabackendspringboot.Paciente.PacienteRestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmergenciaRestService {

    private final EmergenciaRestRepository emergenciaRestRepository;
    private final MedicamentoRestRepository medicamentoRestRepository;
    private final PacienteRestRepository pacienteRestRepository;

    public Emergencia registrarPosologia(String idPaciente, String idPrincipioAtivo, LocalDateTime horarioDosagem) throws IOException {
        List<Paciente> pacienteList = pacienteRestRepository.listAll();
        List<Medicamento> medicamentoList = medicamentoRestRepository.listAll();
        Optional<Paciente> paciente = pacienteList.stream().filter(pacienteSearch -> pacienteSearch.getId().equals(idPaciente)).findFirst();
        Optional<Medicamento> medicamento = medicamentoList.stream().filter(medicamentoSearch -> medicamentoSearch.getId().equals(idPrincipioAtivo)).findFirst();
        Emergencia atendimento = new Emergencia();
        if(medicamento.isPresent() && paciente.isPresent()) {
            atendimento.setPaciente(paciente.get());
            medicamento.get().setHorarioDosagem(horarioDosagem);
            List<Medicamento> medicamentosAtendimento = new ArrayList<>();
            medicamentosAtendimento.add(medicamento.get());
            atendimento.setMedicamentos(medicamentosAtendimento);
        }
        return emergenciaRestRepository.inserirArquivo(atendimento);
    }

    public List<Emergencia> listarTodos() throws IOException {
        return emergenciaRestRepository.listAll();
    }

    public Optional<Emergencia> pacientePorId(String idPaciente) throws IOException {
        return emergenciaRestRepository.findById(idPaciente);
    }
}