/**
 * Sort using Java's ParallelStreams and Lambda functions.
 * <p>
 * Hints:
 * - Do not take advice from StackOverflow.
 * - Think outside the box.
 *      - Stream of threads?
 *      - Stream of function invocations?
 * <p>
 * By default, the number of threads in parallel stream is limited by the
 * number of cores in the system. You can limit the number of threads used by
 * parallel streams by wrapping it in a ForkJoinPool.
 *      ForkJoinPool myPool = new ForkJoinPool(threads);
 *      myPool.submit(() -> "my parallel stream method / function");
 */

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

/**
 * Parallel Stream: A parallel stream splits its elements into multiple chunks,
 * which can then be processed concurrently in separate threads. This is done
 * using the Fork/Join Framework introduced in Java 7, where work is recursively
 * split into smaller tasks that can run in parallel and combined when complete.
 * <p>
 * Example:
 * List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
 * Create a parallel stream:
 * int sum = numbers.parallelStream()
 *                  .filter(n -> n % 2 == 0)
 *                  .mapToInt(n -> n * n)
 *                  .sum();
 * */

public class ParallelStreamSort implements Sorter {

        public final int threads;
        private final ForkJoinPool pool;

        public ParallelStreamSort(int threads) {
            // Create a ForkJoinPool with the specified number of threads in Constructor!
            this.pool = new ForkJoinPool(threads);
            this.threads = threads;
        }

        @Override
        public void sort(int[] arr) {
            try {
                pool.submit(() -> {
                    parallelQuickSort(arr, 0, arr.length - 1);
                }).get(); // Manage threads with ForkJoinPool
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        // Quicksort algorithm (Parallelized)
        private void parallelQuickSort(int[] arr, int low, int high) {
            if (low < high) {
                // Partition the array and get the pivot index
                int pivotIndex = SequentialSort.partition(arr, low, high);

                try {
                    // Parallelize the two recursive calls with streams
                    pool.submit(() -> {
                        Arrays.stream(new int[] {0, 1}).parallel().forEach(i -> {
                            if (i == 0) {
                                parallelQuickSort(arr, low, pivotIndex - 1);
                            } else {
                                parallelQuickSort(arr, pivotIndex + 1, high);
                            }
                        });
                    }).get();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

        public int getThreads() {
                return threads;
        }
}
