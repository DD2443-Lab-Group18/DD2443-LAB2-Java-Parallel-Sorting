import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.style.Styler;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.pow;

public class MyAmdahlPlot {

    public static void main(String[] args) {

        // Thread counts to consider (2, 4, 8, 16, 32)
        int[] threadCounts = {2, 4, 8, 16, 32};

        // Parallelization portion (p)
        double[] parallelizationPortions = {0.2, 0.4, 0.6, 0.8};

        // Create Chart
        XYChart chart = new XYChartBuilder().width(800)
                .title("My Amdahl's Law: Speedup with Different Thread Counts and P")
                .xAxisTitle("Number of Threads")
                .yAxisTitle("Speedup (%)")
                .build();

        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);

        // Calculate speedup for different values of p
        for (double p : parallelizationPortions) {
            List<Integer> xData = new ArrayList<>();
            List<Double> yData = new ArrayList<>();

            for (int n : threadCounts) {
                double speedup = calculateSpeedup(p, n);
                xData.add(n);
                yData.add(speedup);
            }

            // Add the series to the chart
            chart.addSeries("p = " + p, xData, yData);
        }

        // Show the chart
        new SwingWrapper<>(chart).displayChart();
    }

    // Method to calculate speedup based on Amdahl's Law
    private static double calculateSpeedup(double p, int N) {
        return 1 / ((1 - p) + (p / pow(N, (double) 8/9)));
    }
}
