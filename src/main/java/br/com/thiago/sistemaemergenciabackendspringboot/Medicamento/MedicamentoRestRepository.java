package br.com.thiago.sistemaemergenciabackendspringboot.Medicamento;

import br.com.thiago.sistemaemergenciabackendspringboot.Paciente.Paciente;
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
public class MedicamentoRestRepository {

    private String caminho = "src/main/resources/dados/medicamento.csv";

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

    public Medicamento inserirArquivo(Medicamento medicamento) throws IOException {
        write(format(medicamento), StandardOpenOption.APPEND);
        return medicamento;
    }

    private void write(String pacienteStr, StandardOpenOption option) throws IOException {
        try (BufferedWriter bf = Files.newBufferedWriter(path, option)) {
            bf.flush();
            bf.write(pacienteStr);
        }
    }


    public List<Medicamento> listAll() throws IOException {
        try (BufferedReader br = Files.newBufferedReader(path)) {
            return br.lines().filter(Objects::nonNull).filter(Predicate.not(String::isEmpty)).map(this::convert).collect(Collectors.toList());
        }
    }

    private String format(Medicamento medicamento) {
        return String.format("%s;%s;%d\r\n",
                medicamento.getPrincipioAtivo(),
                medicamento.getFabricante(),
                medicamento.getDosagem());
    }

    private Medicamento convert(String linha) {
        StringTokenizer token = new StringTokenizer(linha, ";");
        return Medicamento.builder()
                .principioAtivo(token.nextToken())
                .fabricante(token.nextToken())
                .dosagem(Integer.parseInt(token.nextToken()))
                .build();
    }
}
