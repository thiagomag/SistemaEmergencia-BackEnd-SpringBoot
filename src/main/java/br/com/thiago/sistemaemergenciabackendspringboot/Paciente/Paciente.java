package br.com.thiago.sistemaemergenciabackendspringboot.Paciente;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Paciente {

    private String id;
    private String nome;
    private String cpf;
}
