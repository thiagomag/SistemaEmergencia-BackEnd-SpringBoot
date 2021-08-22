package br.com.thiago.sistemaemergenciabackendspringboot.Paciente;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("pacientes")
@RequiredArgsConstructor
public class PacienteRestController {

    private final PacienteRestService pacienteRestService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PacienteDTOResponse> criarPaciente(@RequestBody PacienteDTORequest pacienteDTORequest,
                                                             UriComponentsBuilder uriComponentsBuilder) {
        return pacienteRestService.criarPaciente(pacienteDTORequest, uriComponentsBuilder);
    }

    @GetMapping
    public ResponseEntity<List<PacienteDTOResponse>> listarTodos() {
        return pacienteRestService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTOResponse> buscarPorId(@PathVariable Long id) {
        return pacienteRestService.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PacienteDTOResponse> deletarPaciente(@PathVariable Long id) {
        return pacienteRestService.deletarPaciente(id);
    }
}
