/**
 * Sort using Java's Thread, Runnable, start(), and join().
 */

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSort implements Sorter {
        private final int threads;
        private final AtomicInteger activeThreads; // Thread-safe int, so no need for lock().
        /**
         * Use compareAndSet(). AtomicInteger is atomic, but how you use it is now is not.
         * */
        private static final int THRESHOLD = 128;

        public ThreadSort(int threads) {
            this.threads = threads;
            this.activeThreads = new AtomicInteger(0);
        }

        public void sort(int[] arr) {
            activeThreads.set(0);
            parallelQuickSort(arr, 0, arr.length - 1);
        }

        private Thread startNewThread(int[] arr, int low, int high) {
            while (true) {
                // Create a snapshot for the current thread number
                int current = activeThreads.get();
                // Cannot increment as we reached the limit
                if (current >= threads) {
                    return null;
                }
                // Try to atomically increment the thread count
                if (activeThreads.compareAndSet(current, current + 1)) {
                    Thread thread = new Thread(() -> {
                        try {
                            parallelQuickSort(arr, low, high);
                        } finally {
                            activeThreads.decrementAndGet();
                        }
                    });
                    thread.start();
                    return thread;
                }
                // If compareAndSet fails, the thread count has changed, retry the loop
            }
        }

        private void parallelQuickSort(int[] arr, int low, int high) {
            if (low < high) {
                if (high - low < THRESHOLD) {
                    Arrays.sort(arr, low, high + 1);
                    return;
                }

                int pivotIndex = SequentialSort.partition(arr, low, high);

                Thread leftThread = null;
                Thread rightThread = null;

                if (activeThreads.get() < threads) {
                    leftThread = startNewThread(arr, low, pivotIndex - 1);
                } else {
                    SequentialSort.quickSort(arr, low, pivotIndex - 1);
                }

                if (activeThreads.get() < threads) {
                    rightThread = startNewThread(arr, pivotIndex + 1, high);
                } else {
                    SequentialSort.quickSort(arr, pivotIndex + 1, high);
                }

                try {
                    if (leftThread != null) {
                        leftThread.join();
                    }
                    if (rightThread != null) {
                        rightThread.join();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        public int getThreads() {
            return threads;
        }
}
