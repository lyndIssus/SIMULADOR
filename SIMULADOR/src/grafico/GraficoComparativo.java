package grafico;

import util.ResultadoSimulacao;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GraficoComparativo {

    public static void gerarGradeDeGraficos(List<ResultadoSimulacao> resultados) {
        JFrame frame = new JFrame("Comparativo - Resumo Geral");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        int linhas = (int) Math.ceil(resultados.size() / 2.0);
        JPanel painel = new JPanel(new GridLayout(linhas, 2, 10, 10));

        for (ResultadoSimulacao resultado : resultados) {
            JPanel graficoIndividual = GraficoIndividual.gerarGraficoIndividual(resultado);
            painel.add(graficoIndividual);
        }

        frame.add(painel);
        frame.setSize(1200, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
