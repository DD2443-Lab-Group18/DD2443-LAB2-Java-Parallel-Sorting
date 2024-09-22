import java.util.stream.LongStream;

/**
 * This should be run on PDC.
 * */

public class MyPDCCode {

    public static void main(String[] args) {

        // Generate a large random array (10 million elements)
        int arraySize = 10_000_000;

        // Sequential sort (baseline)
        SequentialSort sequentialSort = new SequentialSort();
        long[] sequentialTime = Auxiliary.measure(sequentialSort, arraySize, 6, 100);
        double sMean = (double) LongStream.of(sequentialTime).sum() / sequentialTime.length;
        System.out.println("Sequential sort time: " + sMean + " ns");

        // Thread counts to test
        int[] threadCounts = {2, 4, 8, 16, 32, 48, 64, 96};

        // Measure ForkJoinPool Sort with different thread counts
        for (int threads : threadCounts) {
            ForkJoinPoolSort parallelSort = new ForkJoinPoolSort(threads);
            long[] parallelTime = Auxiliary.measure(parallelSort, arraySize, 6, 100);
            double pMean = (double) LongStream.of(parallelTime).sum() / parallelTime.length;
            System.out.println(threads + " threads, ForkJoinPool sort time: " + pMean + " ns");

            // Calculate speedup
            double speedup = sMean / pMean;
            System.out.println("Speedup with " + threads + " threads: " + speedup);
        }
    }
}
