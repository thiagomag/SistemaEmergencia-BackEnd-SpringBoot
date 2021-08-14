package br.com.thiago.sistemaemergenciabackendspringboot.Paciente;

public class IdPacienteNaoExisteException extends RuntimeException{
    public IdPacienteNaoExisteException(Long idPaciente) {
        super("O Id, " + idPaciente + ", do paciente pesquisado não existe no banco de dados.");
    }
}
