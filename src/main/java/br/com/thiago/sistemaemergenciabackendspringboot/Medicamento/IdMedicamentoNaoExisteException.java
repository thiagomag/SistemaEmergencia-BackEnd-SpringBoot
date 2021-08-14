package br.com.thiago.sistemaemergenciabackendspringboot.Medicamento;

public class IdMedicamentoNaoExisteException extends RuntimeException{
    public IdMedicamentoNaoExisteException(Long idMedicamento) {
        super("O Id, " + idMedicamento + ", do medicamento pesquisado não existe no banco de dados.");
    }
}
