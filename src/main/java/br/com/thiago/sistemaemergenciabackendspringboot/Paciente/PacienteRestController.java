package br.com.thiago.sistemaemergenciabackendspringboot.Paciente;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("pacientes")
@RequiredArgsConstructor
public class PacienteRestController {

    private final PacienteRestService pacienteRestService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PacienteDTOResponse criarPaciente(@RequestBody PacienteDTORequest pacienteDTORequest) {
        return pacienteRestService.criarPaciente(pacienteDTORequest);
    }

    @GetMapping
    public List<PacienteDTOResponse> listarTodos() {
        return pacienteRestService.listarTodos();
    }

    @GetMapping("/{id}")
    public PacienteDTOResponse buscarPorId(@PathVariable Long id) {
        return pacienteRestService.findById(id);
    }


}
