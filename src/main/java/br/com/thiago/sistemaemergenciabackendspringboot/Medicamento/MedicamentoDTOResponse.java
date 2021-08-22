package br.com.thiago.sistemaemergenciabackendspringboot.Medicamento;

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
public class MedicamentoDTOResponse {

    private Long id;
    private String principioAtivo;
    private String fabricante;
    private Long dosagem;
    private Long periodicidade;
    private Boolean ativo;

    public MedicamentoDTOResponse(Medicamento medicamento) {
        this.id = medicamento.getId();
        this.principioAtivo = medicamento.getPrincipioAtivo();
        this.fabricante = medicamento.getFabricante();
        this.dosagem = medicamento.getDosagem();
        this.periodicidade = medicamento.getPeriodicidade();
        this.ativo = medicamento.getAtivo();
    }

    public static List<MedicamentoDTOResponse> convert(List<Medicamento> medicamentos) {
        return medicamentos.stream().map(MedicamentoDTOResponse::new).collect(Collectors.toList());
    }
}