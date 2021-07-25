package br.com.thiago.sistemaemergenciabackendspringboot.Medicamento;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("medicamento")
@RequiredArgsConstructor
public class MedicamentoRestController {

    private final MedicamentoRestService medicamentoRestService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Medicamento adicionarMedicamento(@RequestBody Medicamento medicamento) throws IOException {
        return medicamentoRestService.adicionarMedicamento(medicamento);
    }

    @GetMapping
    public List<Medicamento> listarMedicamentos() throws IOException {
        return medicamentoRestService.listarMedicamentos();
    }

}
