import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

public class MyPDCPlot {

    public static void main(String[] args) {

        double[] threadCounts = {2, 4, 8, 16, 32, 48, 64, 96};
        double[] speedupsES = {0.93, 1.02, 1.24, 1.63, 1.92, 2.00, 1.81, 1.96};
        double[] speedupsPS = {0.94, 1.70, 3.04, 3.34, 4.11, 4.64, 4.23, 4.08};
        double[] speedupsFS = {1.49, 2.87, 4.62, 4.78, 5.51, 5.56, 5.28, 5.19};
        double[] speedupsTS = {0.80, 0.91, 1.10, 1.38, 1.52, 1.48, 1.47, 1.41};
        double[] speedupsJS = {0.90, 2.39, 4.37, 8.88, 16.9, 19.8, 24.0, 25.2};

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
