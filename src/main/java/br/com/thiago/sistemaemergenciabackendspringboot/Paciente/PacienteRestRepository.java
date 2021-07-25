package br.com.thiago.sistemaemergenciabackendspringboot.Paciente;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class PacienteRestRepository {

    private String caminho = "src/main/resources/dados/paciente.csv";

    private Path path;

    @PostConstruct
    public void init() {
        try {
            path = Paths.get(String.valueOf(caminho));
            if (!path.toFile().exists()) {
                Files.createFile(path);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public Paciente inserirArquivo(Paciente paciente) throws IOException {
        write(format(paciente), StandardOpenOption.APPEND);
        return paciente;
    }

    private void write(String pacienteStr, StandardOpenOption option) throws IOException {
        try (BufferedWriter bf = Files.newBufferedWriter(path, option)) {
            bf.flush();
            bf.write(pacienteStr);
        }
    }


    public List<Paciente> listAll() throws IOException {
        try (BufferedReader br = Files.newBufferedReader(path)) {
            return br.lines().filter(Objects::nonNull).filter(Predicate.not(String::isEmpty)).map(this::convert).collect(Collectors.toList());
        }
    }

    private String format(Paciente paciente) {
        return String.format("%s,%s,%s\r\n",
                paciente.getId(),
                paciente.getNome(),
                paciente.getCpf());
    }

    private Paciente convert(String linha) {
        StringTokenizer token = new StringTokenizer(linha, ",");
        return Paciente.builder()
                .id(token.nextToken())
                .nome(token.nextToken())
                .cpf(token.nextToken())
                .build();
    }
}