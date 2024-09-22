import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

public class MyPDCPlot {

    public static void plotSpeedup(double[] threadCounts, double[] speedups) {
        XYChart chart = new XYChartBuilder().width(800)
                .title("Speedup with Different Thread Counts")
                .xAxisTitle("Threads")
                .yAxisTitle("Speedup")
                .build();

        chart.addSeries("ForkJoinPool Speedup", threadCounts, speedups);

        new SwingWrapper<>(chart).displayChart();
    }

    public static void main(String[] args) {
        double[] threadCounts = {2, 4, 8, 16, 32, 48, 64, 96};
        double[] speedups = {1.5, 2.3, 3.6, 6.1, 10.5, 13.2, 16.7, 19.9};

        plotSpeedup(threadCounts, speedups);
    }
}
