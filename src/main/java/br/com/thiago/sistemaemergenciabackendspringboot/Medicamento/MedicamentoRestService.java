package br.com.thiago.sistemaemergenciabackendspringboot.Medicamento;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicamentoRestService {

    private final MedicamentoRestRepository medicamentoRestRepository;

    public ResponseEntity<MedicamentoDTOResponse> adicionarMedicamento(MedicamentoDTORequest medicamentoDTORequest,
                                                                       UriComponentsBuilder uriComponentsBuilder) {
        var medicamento = medicamentoDTORequest.convert();
        medicamentoRestRepository.save(medicamento);
        var uri = uriComponentsBuilder.path("/medicamento/{id}").buildAndExpand(medicamento.getId()).toUri();
        return ResponseEntity.created(uri).body(new MedicamentoDTOResponse(medicamento));
    }

    public ResponseEntity<List<MedicamentoDTOResponse>> listarMedicamentos() {
        var medicamentos = medicamentoRestRepository.findAll();
        return ResponseEntity.ok().body(MedicamentoDTOResponse.convert(medicamentos));
    }

    public ResponseEntity<MedicamentoDTOResponse> listarPorId(Long id) {
        var medicamento = medicamentoRestRepository.findById(id).orElseThrow(() ->
                new IdMedicamentoNaoExisteException(id));
        return ResponseEntity.ok().body(new MedicamentoDTOResponse(medicamento));
    }

    public ResponseEntity<MedicamentoDTOResponse> deletarMedicamento(Long id) {
        var medicamento = medicamentoRestRepository.findById(id).orElseThrow(() ->
                new IdMedicamentoNaoExisteException(id));
        medicamento.setAtivo(false);
        medicamentoRestRepository.save(medicamento);
        return ResponseEntity.ok().body(new MedicamentoDTOResponse(medicamento));
    }
}
