package br.com.thiago.sistemaemergenciabackendspringboot.Paciente;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PacienteDTORequest {

    private Long id;
    private String nome;
    private String cpf;

    public Paciente convert() {
        return Paciente.builder()
                .id(this.id)
                .nome(this.nome)
                .cpf(this.cpf)
                .ativo(true)
                .build();
    }
}
