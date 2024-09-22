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
            ExecutorServiceSort executorServiceSort = new ExecutorServiceSort(threads);
            ParallelStreamSort parallelStreamSort = new ParallelStreamSort(threads);
            ForkJoinPoolSort forkJoinPoolSort = new ForkJoinPoolSort(threads);
            ThreadSort parallelThreadSort = new ThreadSort(threads);
            JavaSort parallelJavaSort = new JavaSort(threads);

            measurePerformance(executorServiceSort, "ExecutorServiceSort",
                    sMean, arraySize, threads);
            measurePerformance(parallelStreamSort, "ParallelStreamSort",
                    sMean, arraySize, threads);
            measurePerformance(forkJoinPoolSort, "ForkJoinPoolSort",
                    sMean, arraySize, threads);
            measurePerformance(parallelThreadSort, "ThreadSort",
                    sMean, arraySize, threads);
            measurePerformance(parallelJavaSort, "JavaSort",
                    sMean, arraySize, threads);
        }
    }

    private static void measurePerformance(Sorter sorter, String name,
                                           double seqMean, int arrSize, int threads) {
        // Measure parallel execution time
        long[] parallelTime = Auxiliary.measure(sorter, arrSize, 6, 100);
        double pMean = (double) LongStream.of(parallelTime).sum() / parallelTime.length;
        System.out.println(threads + " threads, " + name + " sort time: " + pMean + " ns");

        // Calculate speedup
        double speedup = seqMean / pMean;
        System.out.println("Speedup with " + threads + " threads: " + speedup);
    }
}
