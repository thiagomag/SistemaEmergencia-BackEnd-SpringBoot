package br.com.thiago.sistemaemergenciabackendspringboot.emergencia;

public class IdEmergenciaNaoExisteException extends RuntimeException{
    public IdEmergenciaNaoExisteException(Long idEmergencia) {
        super("O Id, " + idEmergencia + ", da emergencia pesquisada não existe no banco de dados.");
    }
}
