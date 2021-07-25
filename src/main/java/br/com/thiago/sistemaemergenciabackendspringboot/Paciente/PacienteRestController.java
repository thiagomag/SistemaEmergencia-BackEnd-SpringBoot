package br.com.thiago.sistemaemergenciabackendspringboot.Paciente;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("pacientes")
@RequiredArgsConstructor
public class PacienteRestController {

    private final PacienteRestService pacienteRestService;

    @PostConstruct
    @ResponseStatus(HttpStatus.CREATED)
    public Paciente criarPaciente(@RequestBody Paciente paciente) throws IOException {
        return pacienteRestService.criarPaciente(paciente);
    }

    @GetMapping
    public List<Paciente> listarTodso() throws IOException {
        return pacienteRestService.listarTodos();
    }
}
