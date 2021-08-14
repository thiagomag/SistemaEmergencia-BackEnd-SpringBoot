package br.com.thiago.sistemaemergenciabackendspringboot.Medicamento;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicamentoRestService {

    private final MedicamentoRestRepository medicamentoRestRepository;

    public MedicamentoDTOResponse adicionarMedicamento(MedicamentoDTORequest medicamentoDTORequest) {
        var medicamento = medicamentoDTORequest.convert();
        medicamentoRestRepository.save(medicamento);
        return new MedicamentoDTOResponse(medicamento);
    }

    public List<MedicamentoDTOResponse> listarMedicamentos() {
        var medicamentos = medicamentoRestRepository.findAll();
        return MedicamentoDTOResponse.convert(medicamentos);
    }
}
