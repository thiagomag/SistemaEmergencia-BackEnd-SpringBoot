package br.com.thiago.sistemaemergenciabackendspringboot.emergencia;

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
import java.util.Optional;

@RestController
@RequestMapping("emergencia")
@RequiredArgsConstructor
public class EmergenciaRestController {

    private final EmergenciaRestService emergenciaRestService;

    @GetMapping("/todosAtendimentos")
    public List<Emergencia> listarTodos() {
        return emergenciaRestService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<Emergencia> pacientePorId(@PathVariable Long id) {
        return emergenciaRestService.pacientePorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmergenciaDTOResponse registrarPosologia(@RequestBody EmergenciaDTORequest emergenciaDTORequest) {
        return emergenciaRestService.registrarPosologia(emergenciaDTORequest);
    }
}