package br.com.thiago.sistemaemergenciabackendspringboot.emergencia;

public class CpfNaoEncontradoException extends RuntimeException {
    public CpfNaoEncontradoException(String cpf) {
        super("O cpf " + cpf + " não foi encontrado em nosso banco de dados.");
    }
}
