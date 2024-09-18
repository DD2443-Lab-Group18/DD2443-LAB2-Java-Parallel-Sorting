/**
 * Sort using Java's ExecutorService.
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * ExecutorService provides a powerful and flexible way to manage and execute tasks
 * asynchronously in Java. It simplifies threading issues by managing a pool of threads
 * and offers methods to submit tasks, obtain results, and shut down the service gracefully.
 * */

public class ExecutorServiceSort implements Sorter {

        public final int threads;
        private ExecutorService executor;

        public ExecutorServiceSort(int threads) {
                this.threads = threads;
        }

        public void sort(int[] arr) {
                // Create a thread pool with the specified number of threads
                executor = Executors.newFixedThreadPool(threads);
                try {
                        myQuickSort(arr, 0, arr.length - 1);
                } finally {
                        executor.shutdown();
                }
        }

        public int getThreads() {
                return threads;
        }

        private void myQuickSort(int[] array, int low, int high) {
                if (low < high) {

                        int pivotIndex = SequentialSort.partition(array, low, high);

                        // Create tasks for sorting the two halves
                        Runnable leftSortTask = () -> myQuickSort(array, low, pivotIndex - 1);
                        Runnable rightSortTask = () -> myQuickSort(array, pivotIndex + 1, high);

                        // Submit tasks to the executor service
                        Future<?> leftFuture = executor.submit(leftSortTask);
                        Future<?> rightFuture = executor.submit(rightSortTask);

                        try {
                                // Wait for both tasks to complete
                                leftFuture.get();
                                rightFuture.get();

                        } catch (Exception e) {
                                throw new RuntimeException(e);
                        }
                }
        }

        private static class Worker implements Runnable {
                Worker(int[] array, int low, int high) {
                }

                public void run() {
                }
        }
}
