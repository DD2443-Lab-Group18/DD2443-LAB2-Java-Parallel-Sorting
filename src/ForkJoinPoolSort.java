/**
 * Sort using Java's ForkJoinPool.
 */

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ForkJoinPoolSort implements Sorter {

        public final int threads;
        private final ForkJoinPool pool;
        private static final int THRESHOLD = 8;

        public ForkJoinPoolSort(int threads) {
                // Create a ForkJoinPool with the specified number of threads in Constructor!
                this.threads = threads;
                this.pool = new ForkJoinPool(threads);
        }

        public void sort(int[] arr) {
                // Submitting the first task to the ForkJoinPool
                pool.invoke(new Worker(arr, 0, arr.length - 1));
        }

        public int getThreads() {
                return threads;
        }

        private static class Worker extends RecursiveAction {
            private final int low;
            private final int high;
            private final int[] array;

            public Worker(int[] array, int low, int high) {
                this.array = array;
                this.low = low;
                this.high = high;
            }

            @Override
            protected void compute() {
                if (low < high) {
                    // Switch to sequential quicksort for small sub-arrays
                    if (high - low + 1 < THRESHOLD) {
                        SequentialSort.quickSort(array, low, high);
                    }
                    else {
                        // Partition the array and get the pivot index
                        int pivot = SequentialSort.partition(array, low, high);

                        // Create two new tasks for the left and right sub-arrays
                        Worker leftTask = new Worker(array, low, pivot - 1);
                        Worker rightTask = new Worker(array, pivot + 1, high);

                        // Fork the tasks to execute them in parallel
                        invokeAll(leftTask, rightTask);
                    }
                }
            }
        }
}
