package br.com.thiago.sistemaemergenciabackendspringboot.Paciente;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PacienteDTOResponse {

    private Long id;
    private String nome;
    private String cpf;
    private Boolean ativo;

    public PacienteDTOResponse(Paciente paciente) {
        this.id = paciente.getId();
        this.nome = paciente.getNome();
        this.cpf = paciente.getCpf();
        this.ativo = paciente.getAtivo();
    }

    public static List<PacienteDTOResponse> convert(List<Paciente> pacienteList) {
        return pacienteList.stream().map(PacienteDTOResponse::new).collect(Collectors.toList());
    }
}
