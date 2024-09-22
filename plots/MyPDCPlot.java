import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

public class MyPDCPlot {

    public static void main(String[] args) {

        double[] threadCounts = {2, 4, 8, 16, 32, 48, 64, 96};
        double[] speedupsES = {1.07, 1.11, 1.24, 1.42, 1.41, 1.30, 1.41, 1.23};
        double[] speedupsPS = {0.82, 1.62, 2.75, 3.24, 4.19, 4.54, 4.70, 4.35};
        double[] speedupsFS = {1.45, 2.69, 4.06, 4.34, 5.05, 5.20, 5.28, 5.20};
        double[] speedupsTS = {0.84, 1.00, 1.26, 1.32, 1.36, 1.34, 1.20, 1.02};
        double[] speedupsJS = {0.96, 2.60, 3.69, 7.62, 14.4, 18.1, 20.5, 22.9};

        XYChart chart = new XYChartBuilder().width(800)
                .title("Speedup with Different Implementations")
                .xAxisTitle("Threads")
                .yAxisTitle("Speedup")
                .build();

        chart.addSeries("ExecutorServiceSort Speedup", threadCounts, speedupsES);
        chart.addSeries("ParallelStreamSort Speedup", threadCounts, speedupsPS);
        chart.addSeries("ForkJoinPool Speedup", threadCounts, speedupsFS);
        chart.addSeries("ThreadSort Speedup", threadCounts, speedupsTS);
        chart.addSeries("JavaSort Speedup", threadCounts, speedupsJS);

        new SwingWrapper<>(chart).displayChart();
    }
}
