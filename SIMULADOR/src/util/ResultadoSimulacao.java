package util;

public class ResultadoSimulacao {
    public String algoritmo;
    public int totalReferencias;
    public int faltas;
    public int acertos;
    public long tempoExecucao; // Em nanossegundos

    public ResultadoSimulacao(String algoritmo, int faltas, int totalReferencias) {
        this.algoritmo = algoritmo;
        this.faltas = faltas;
        this.totalReferencias = totalReferencias;
        this.acertos = totalReferencias - faltas;
    }

    public void exibirResultado() {
        double hitRate = 100.0 * acertos / totalReferencias;
        double missRate = 100.0 * faltas / totalReferencias;
        double tempoEmMs = tempoExecucao / 1_000_000.0;

        System.out.printf("%s - Total de acessos: %d\n", algoritmo, totalReferencias);
        System.out.printf("%s - Faltas de página: %d\n", algoritmo, faltas);
        System.out.printf("%s - Acertos de página: %d\n", algoritmo, acertos);
        System.out.printf("%s - Taxa de acerto: %.2f%%\n", algoritmo, hitRate);
        System.out.printf("%s - Taxa de falta: %.2f%%\n", algoritmo, missRate);
        System.out.printf("%s - Tempo de execução: %.4f ms\n", algoritmo, tempoEmMs);
        System.out.println();
    }

    public int getFaltasPaginas() {
        return faltas;
    }

    public int getTotalReferencias() {
        return totalReferencias;
    }

    public int getAcertos() {
        return acertos;
    }

    public long getTempoExecucao() {
        return tempoExecucao;
    }
}
