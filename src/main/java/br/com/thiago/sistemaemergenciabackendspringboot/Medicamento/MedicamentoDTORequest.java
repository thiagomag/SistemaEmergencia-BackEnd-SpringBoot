package br.com.thiago.sistemaemergenciabackendspringboot.Medicamento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicamentoDTORequest {

    private String principioAtivo;
    private String fabricante;
    private Long dosagem;
    private Long periodicidade;

    public Medicamento convert() {
        return Medicamento.builder()
                .principioAtivo(this.principioAtivo)
                .fabricante(this.fabricante)
                .dosagem(this.dosagem)
                .periodicidade(this.periodicidade)
                .ativo(true)
                .build();
    }
}
