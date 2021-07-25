package br.com.thiago.sistemaemergenciabackendspringboot.Medicamento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medicamento {

    private String id;
    private String principioAtivo;
    private String fabricante;
    private int dosagem;
    private LocalDateTime horarioDosagem;
    private int periodicidade;
}
