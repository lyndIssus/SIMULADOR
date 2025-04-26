package grafico;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import util.ResultadoSimulacao;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GraficoTaxaFalta {

    public static void gerarGraficoTaxa(List<ResultadoSimulacao> resultadosSimulacoes) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (ResultadoSimulacao resultado : resultadosSimulacoes) {
            double taxaFalta = 100.0 * resultado.getFaltasPaginas() / resultado.getTotalReferencias();
            dataset.addValue(taxaFalta, "Taxa de Falta (%)", resultado.algoritmo);
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Taxa de Faltas (%) por Algoritmo",
                "Algoritmo",
                "Taxa de Falta (%)",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );

        CategoryPlot plot = barChart.getCategoryPlot();
        plot.setBackgroundPaint(Color.white);
        plot.setRangeGridlinePaint(Color.gray);

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDefaultItemLabelsVisible(true);
        renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}%", new java.text.DecimalFormat("#0.00")));
        renderer.setDefaultItemLabelFont(new Font("Arial", Font.BOLD, 14));

        Color[] cores = {new Color(79, 129, 189), new Color(192, 80, 77), new Color(155, 187, 89)};
        for (int i = 0; i < resultadosSimulacoes.size(); i++) {
            renderer.setSeriesPaint(i, cores[i % cores.length]);
        }

        JFrame frame = new JFrame("Taxa de Faltas (%) - Simulador de Páginas");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(new ChartPanel(barChart));
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
