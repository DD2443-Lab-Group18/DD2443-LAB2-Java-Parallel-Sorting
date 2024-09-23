/**
 * Sort using Java's ExecutorService.
 */

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ExecutorService provides a powerful and flexible way to manage and execute tasks
 * asynchronously in Java. It simplifies threading issues by managing a pool of threads
 * and offers methods to submit tasks, obtain results, and shut down the service gracefully.
 * */

public class ExecutorServiceSort implements Sorter {

        private final int threads;
        private final static int THRESHOLD = 16;
        private final AtomicInteger activeThreads;

        public ExecutorServiceSort(int threads) {
                // Create a thread pool with the specified number of threads in Constructor!
                this.threads = threads;
                // Apply an atomic integer to count the active threads
                this.activeThreads = new AtomicInteger(0);
        }

        public void sort(int[] arr) {
                ExecutorService executor = Executors.newFixedThreadPool(threads);
                try {
                        myQuickSort(arr, 0, arr.length - 1, executor);
                } finally {
                        executor.shutdown();
                }
        }

        public int getThreads() {
                return threads;
        }

        private void myQuickSort(int[] array, int low, int high, ExecutorService executor) {
            if (low < high) {
                // Switch to Sequential Sort for small arrays
                if (high - low < THRESHOLD) {
                    SequentialSort.quickSort(array, low, high);
                }

                else {
                    int pivot = SequentialSort.partition(array, low, high);

                    if (activeThreads.get() < threads) {
                        activeThreads.incrementAndGet();
                        // Submit tasks to the executor service
                        Future<?> leftTask = executor.submit(() -> {
                            myQuickSort(array, low, pivot - 1, executor);
                            activeThreads.decrementAndGet();
                        });

                        // Only parallel left branches to reduce contention and overheads
                        myQuickSort(array, pivot + 1, high, executor);

                        try {
                            // Wait for the task to complete [Bug Fixed]
                            // Otherwise, it may cause part of the array unsorted.
                            leftTask.get();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        // Run the tasks directly if threads are not available
                        /* Otherwise If the tasks recursively submit more child tasks and
                           there aren't enough threads to execute them, it can lead to the
                           deadlock where child tasks are waiting for parent tasks to finish,
                           but the parent tasks are waiting for the child tasks to complete.
                           <p>
                           BUG: Child tasks would never be scheduled to run because there
                           would be no available worker threads.
                        */
                        SequentialSort.quickSort(array, low, pivot - 1);
                        SequentialSort.quickSort(array, pivot + 1, high);
                    }
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
