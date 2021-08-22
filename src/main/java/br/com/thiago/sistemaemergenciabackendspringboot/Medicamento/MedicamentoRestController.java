package br.com.thiago.sistemaemergenciabackendspringboot.Medicamento;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("medicamento")
@RequiredArgsConstructor
public class MedicamentoRestController {

    private final MedicamentoRestService medicamentoRestService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MedicamentoDTOResponse> adicionarMedicamento(@RequestBody MedicamentoDTORequest medicamentoDTORequest,
                                                                       UriComponentsBuilder uriComponentsBuilder) {
        return medicamentoRestService.adicionarMedicamento(medicamentoDTORequest, uriComponentsBuilder);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicamentoDTOResponse> listarPorId(@PathVariable Long id) {
        return medicamentoRestService.listarPorId(id);
    }

    @GetMapping
    public ResponseEntity<List<MedicamentoDTOResponse>> listarMedicamentos() {
        return medicamentoRestService.listarMedicamentos();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MedicamentoDTOResponse> deletarMedicamento(@PathVariable Long id) {
        return medicamentoRestService.deletarMedicamento(id);
    }
}
