package br.com.thiago.sistemaemergenciabackendspringboot.Medicamento;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medicamento")
@RequiredArgsConstructor
public class MedicamentoRestController {

    private final MedicamentoRestService medicamentoRestService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MedicamentoDTOResponse adicionarMedicamento(@RequestBody MedicamentoDTORequest medicamentoDTORequest) {
        return medicamentoRestService.adicionarMedicamento(medicamentoDTORequest);
    }

    @GetMapping
    public List<MedicamentoDTOResponse> listarMedicamentos() {
        return medicamentoRestService.listarMedicamentos();
    }

}
