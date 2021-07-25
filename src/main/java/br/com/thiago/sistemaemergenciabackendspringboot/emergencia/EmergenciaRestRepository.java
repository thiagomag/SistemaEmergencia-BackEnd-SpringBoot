package br.com.thiago.sistemaemergenciabackendspringboot.emergencia;

import br.com.thiago.sistemaemergenciabackendspringboot.Medicamento.Medicamento;
import br.com.thiago.sistemaemergenciabackendspringboot.Paciente.Paciente;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EmergenciaRestRepository {

    private Path path;

    @PostConstruct
    public void init() {
        try {
            String caminho = "src/main/resources/dados/emergencia.csv";
            path = Paths.get(caminho);
            if (!path.toFile().exists()) {
                Files.createFile(path);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public Emergencia inserirArquivo(Emergencia emergencia) throws IOException {
        write(format(emergencia), StandardOpenOption.APPEND);
        return emergencia;
    }

    private void write(String pacienteStr, StandardOpenOption option) throws IOException {
        try (BufferedWriter bf = Files.newBufferedWriter(path, option)) {
            bf.flush();
            bf.write(pacienteStr);
        }
    }


    public List<Emergencia> listAll() throws IOException {
        try (BufferedReader br = Files.newBufferedReader(path)) {
            return br.lines().filter(Objects::nonNull).filter(Predicate.not(String::isEmpty)).map(this::convert).collect(Collectors.toList());
        }
    }

    private String format(Emergencia emergencia) {
        return String.format("%s,%s\r\n",
                emergencia.getPaciente().toString().replace("[", "").trim().replace("]", "").trim(),
                emergencia.getMedicamentos().toString().replace("[", "").trim().replace("]", "").trim());
    }

    private Emergencia convert(String linha) {
        StringTokenizer token = new StringTokenizer(linha, ",");
        var emergencia = Emergencia.builder()
                .paciente(Paciente.builder()
                        .id(token.nextToken())
                        .nome(token.nextToken())
                        .cpf(token.nextToken())
                        .build())
                .build();
        List<Medicamento> medicamentoList = new ArrayList<>();
        while(token.hasMoreTokens()) {
            var medicamento = Medicamento.builder()
                    .id(token.nextToken())
                    .principioAtivo(token.nextToken())
                    .fabricante(token.nextToken())
                    .dosagem(Integer.parseInt(token.nextToken()))
                    .horarioDosagem(LocalDateTime.parse(token.nextToken()))
                    .build();
            medicamentoList.add(dateAndHourFormat(medicamento));
        }
        emergencia.setMedicamentos(medicamentoList);
        return emergencia;
    }

    private Medicamento dateAndHourFormat(Medicamento medicamento) {
        var horarioDose = medicamento.getHorarioDosagem();
        var dias = LocalDateTime.now().getDayOfMonth() - horarioDose.getDayOfMonth();
        var horas = LocalDateTime.now().getHour() - horarioDose.getHour();
        if(medicamento.getPeriodicidade() == 24) {
            horarioDose = horarioDose.plusDays(dias+1);
        } else if (horas > medicamento.getPeriodicidade()) {
            var resultado = medicamento.getPeriodicidade() - ((((double) horas/(double) medicamento.getPeriodicidade()) - (horas/medicamento.getPeriodicidade())) * medicamento.getPeriodicidade());
            System.out.println(resultado);
            horarioDose = horarioDose.plusDays(dias).plusHours((long) (horas+resultado));
            medicamento.setHorarioDosagem(horarioDose);
        }
        return medicamento;
    }

    public Optional<Emergencia> findById(String idPaciente) throws IOException {
        List<Emergencia> emergenciaList = listAll();
        return emergenciaList.stream().filter(emergencia -> emergencia.getPaciente().getId().equals(idPaciente)).findFirst();
    }
}