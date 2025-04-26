package grafico;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.ChartPanel;
import org.jfree.data.category.DefaultCategoryDataset;
import util.ResultadoSimulacao;

import javax.swing.*;
import java.awt.*;

public class GraficoIndividual {

    public static JPanel gerarGraficoIndividual(ResultadoSimulacao resultado) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(resultado.getFaltasPaginas(), "Métricas", "Faltas");
        dataset.addValue(resultado.getAcertos(), "Métricas", "Acertos");
        dataset.addValue(resultado.getTempoExecucao() / 1_000_000.0, "Métricas", "Tempo (ms)");

        JFreeChart chart = ChartFactory.createBarChart(
                resultado.algoritmo,
                "Métricas",
                "Valor",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false
        );

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.white);
        plot.setRangeGridlinePaint(Color.lightGray);

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDefaultItemLabelsVisible(true);
        renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setDefaultItemLabelFont(new Font("Arial", Font.BOLD, 12));

        Color[] cores = {new Color(79, 129, 189), new Color(192, 80, 77), new Color(155, 187, 89)};
        for (int i = 0; i < 3; i++) {
            renderer.setSeriesPaint(i, cores[i % cores.length]);
        }

        return new ChartPanel(chart); // Retorna pronto para ser embutido no grid
    }
}
