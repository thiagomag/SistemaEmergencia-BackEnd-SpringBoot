package br.com.thiago.sistemaemergenciabackendspringboot.Medicamento;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MedicamentoRestService {

    private final MedicamentoRestRepository medicamentoRestRepository;

    public Medicamento adicionarMedicamento(Medicamento medicamento) throws IOException {
        medicamento.setId(UUID.randomUUID().toString());
        return medicamentoRestRepository.inserirArquivo(medicamento);
    }

    public List<Medicamento> listarMedicamentos() throws IOException {
        return medicamentoRestRepository.listAll();
    }
}
