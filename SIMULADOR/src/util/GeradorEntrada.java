package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.*;

public class GeradorEntrada {

    public static void gerarEntradaPareto(String caminhoArquivo, int quantidadeReferencias, int totalPaginas) throws Exception {
        Random random = new Random();
        Set<Integer> paginasUnicas = new HashSet<>();
        List<Integer> referencias = new ArrayList<>();
        List<String> acoes = new ArrayList<>();

        int quantidadePaginasQuentes = Math.max(1, totalPaginas / 5);
        List<Integer> paginasQuentes = new ArrayList<>();
        List<Integer> paginasFrias = new ArrayList<>();

        for (int i = 0; i < totalPaginas; i++) {
            if (i < quantidadePaginasQuentes) {
                paginasQuentes.add(i);
            } else {
                paginasFrias.add(i);
            }
        }

        for (int i = 0; i < quantidadeReferencias; i++) {
            double chance = random.nextDouble();

            int pagina;
            if (chance < 0.8) {
                pagina = paginasQuentes.get(random.nextInt(paginasQuentes.size()));
            } else {
                pagina = paginasFrias.get(random.nextInt(paginasFrias.size()));
            }

            referencias.add(pagina);
            paginasUnicas.add(pagina);
            acoes.add(random.nextBoolean() ? "L" : "E");
        }

        ConfiguracaoSimulacao config = new ConfiguracaoSimulacao();
        config.tamanhoMemoria = 100;
        config.referencias = referencias;
        config.acoes = acoes;
        config.memoriaInicial = new ArrayList<>();
        config.interrupcaoRelogio = 50;
        config.tau = 10;
        config.paginasUnicas = new ArrayList<>(paginasUnicas);
        config.quantidadePaginas = paginasUnicas.size();
        config.configuracoes = new ConfiguracaoSimulacao.Configuracoes();
        config.configuracoes.usarEstadoInicial = false;
        config.configuracoes.considerarAcoes = false;
        config.configuracoes.usarClockInterrupcao = true;
        config.configuracoes.usarTauAging = true;

        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(caminhoArquivo), config);
    }

    public static void main(String[] args) {
        try {
            gerarEntradaPareto("entrada.json", 1000, 1000);
            System.out.println("arquivo entrada.json gerado usando Lei de Pareto!");
        } catch (Exception e) {
            System.err.println("erro ao gerar entrada:");
            e.printStackTrace();
        }
    }
}
