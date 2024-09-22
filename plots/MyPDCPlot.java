import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

public class MyPDCPlot {

    public static void main(String[] args) {

        double[] threadCounts = {2, 4, 8, 16, 32, 48, 64, 96};
        double[] speedupsES = {1.5, 2.3, 3.6, 6.1, 10.5, 13.2, 16.7, 19.9};
        double[] speedupsPS = {1.5, 2.3, 3.6, 6.1, 10.5, 13.2, 16.7, 19.9};
        double[] speedupsFS = {1.5, 2.3, 3.6, 6.1, 10.5, 13.2, 16.7, 19.9};
        double[] speedupsTS = {1.5, 2.3, 3.6, 6.1, 10.5, 13.2, 16.7, 19.9};
        double[] speedupsJS = {1.5, 2.3, 3.6, 6.1, 10.5, 13.2, 16.7, 19.9};

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
