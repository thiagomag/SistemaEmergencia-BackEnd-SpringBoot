package br.com.thiago.sistemaemergenciabackendspringboot.emergencia;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("emergencia")
@RequiredArgsConstructor
public class EmergenciaRestController {

    private final EmergenciaRestService emergenciaRestService;

    @GetMapping("/todosAtendimentos")
    public List<Emergencia> listarTodos() throws IOException {
        return emergenciaRestService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<Emergencia> pacientePorId(@PathVariable String id) throws IOException {
        return emergenciaRestService.pacientePorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Emergencia registrarPosologia(@RequestBody String idPaciente, String idPrincipioAtivo, LocalDateTime horarioDosagem) throws IOException {
        return emergenciaRestService.registrarPosologia(idPaciente, idPrincipioAtivo, horarioDosagem);
    }
}