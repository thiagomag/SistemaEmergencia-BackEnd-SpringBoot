package br.com.thiago.sistemaemergenciabackendspringboot.emergencia;

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
@RequestMapping("emergencia")
@RequiredArgsConstructor
public class EmergenciaRestController {

    private final EmergenciaRestService emergenciaRestService;

    @GetMapping("/todosAtendimentos")
    public List<EmergenciaDTOResponse> listarTodos() {
        return emergenciaRestService.listarTodos();
    }

    @GetMapping("/{id}")
    public EmergenciaDTOResponse emergenciaPorId(@PathVariable Long id) {
        return emergenciaRestService.emergenciaPorId(id);
    }

    @GetMapping("/{cpf}")
    public List<EmergenciaDTOResponse> buscarEmergenciaPorCpfPaciente(@PathVariable String cpf) {
        return emergenciaRestService.buscarEmergenciaPorCpfPaciente(cpf);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EmergenciaDTOResponse> registrarPosologia(@RequestBody EmergenciaDTORequest emergenciaDTORequest,
                                                    UriComponentsBuilder uriComponentsBuilder) {
        return emergenciaRestService.registrarPosologia(emergenciaDTORequest, uriComponentsBuilder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        return emergenciaRestService.deletar(id);
    }
}