package principal;

import algoritmos.FIFO;
import algoritmos.Clock;
import algoritmos.AlgoritmoSubstituicao;
import grafico.GraficoIndividual;
import util.ConfiguracaoSimulacao;
import util.JacksonJSON;
import util.ResultadoSimulacao;
import util.MensagemErro;
import grafico.GraficoComparativo;
import grafico.GraficoTaxaFalta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            ConfiguracaoSimulacao config = JacksonJSON.carregar("entrada.json");
            List<Integer> referencias = new ArrayList<>(config.referencias);
            System.out.println("Configuração carregada com sucesso:");
            System.out.println(config);
            System.out.println("\n================ INICIANDO SIMULAÇÕES ================\n");

            List<ResultadoSimulacao> resultadosSimulacoes = new ArrayList<>();

            // FIFO
            FIFO fifo = new FIFO();
            ResultadoSimulacao resultadoFIFO = executarAlgoritmo(fifo, "FIFO", config, referencias);
            resultadosSimulacoes.add(resultadoFIFO);

            Clock clock = new Clock();
            ResultadoSimulacao resultadoClock = executarAlgoritmo(clock, "Clock", config, referencias);
            resultadosSimulacoes.add(resultadoClock);

            GraficoComparativo.gerarGradeDeGraficos(resultadosSimulacoes);
            GraficoTaxaFalta.gerarGraficoTaxa(resultadosSimulacoes);
            exibirResumoAnalitico(resultadosSimulacoes);

            GraficoIndividual.gerarGraficoIndividual(resultadoFIFO);
            GraficoIndividual.gerarGraficoIndividual(resultadoClock);


        } catch (Exception e) {
            MensagemErro.exibir("Main", e);
        }
    }

    private static ResultadoSimulacao executarAlgoritmo(AlgoritmoSubstituicao algoritmo, String nome, ConfiguracaoSimulacao config, List<Integer> referencias) {
        long inicio = System.nanoTime();

        ResultadoSimulacao resultado;
        if (config.configuracoes.usarEstadoInicial && config.memoriaInicial != null && !config.memoriaInicial.isEmpty()) {
            resultado = algoritmo.executarComEstadoInicial(referencias, config.tamanhoMemoria, config.memoriaInicial);
        } else {
            resultado = algoritmo.executar(referencias, config.tamanhoMemoria);
        }

        long fim = System.nanoTime();
        resultado.algoritmo = nome;
        resultado.tempoExecucao = fim - inicio;
        resultado.exibirResultado();
        return resultado;
    }

    private static void exibirResumoAnalitico(List<ResultadoSimulacao> resultados) {
        System.out.println("======= RESUMO ANALÍTICO FINAL =======");

        Collections.sort(resultados, Comparator.comparingDouble(
                r -> (double) r.getFaltasPaginas() / r.getTotalReferencias()
        ));

        for (ResultadoSimulacao r : resultados) {
            double taxaFalta = 100.0 * r.getFaltasPaginas() / r.getTotalReferencias();
            double taxaAcerto = 100.0 * r.getAcertos() / r.getTotalReferencias();
            double tempoEmMs = r.getTempoExecucao() / 1_000_000.0;
            System.out.printf("%s: %.2f%% de faltas | %.2f%% de acertos (%d faltas em %d acessos) | Tempo: %.4f ms\n",
                    r.algoritmo, taxaFalta, taxaAcerto, r.getFaltasPaginas(), r.getTotalReferencias(), tempoEmMs);
        }

        System.out.printf("\nMelhor algoritmo: %s (menor taxa de falta)\n", resultados.get(0).algoritmo);
        System.out.println("======================================\n");
    }
}
