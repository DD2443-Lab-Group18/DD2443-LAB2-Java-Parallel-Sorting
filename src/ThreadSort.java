/**
 * Sort using Java's Thread, Runnable, start(), and join().
 */

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSort implements Sorter {
        private final int threads;
        private final AtomicInteger activeThreads; // Thread-safe int, so no need for lock().
        private static final int THRESHOLD = 1000;

        public ThreadSort(int threads) {
            this.threads = threads;
            this.activeThreads = new AtomicInteger(0);
        }

        public void sort(int[] arr) {
            activeThreads.set(0);
            parallelQuickSort(arr, 0, arr.length - 1);
        }

        private Thread startNewThread(int[] arr, int low, int high) {
            Thread thread = new Thread(() -> {
                activeThreads.incrementAndGet();
                try {
                    parallelQuickSort(arr, low, high);
                } finally {
                    activeThreads.decrementAndGet();
                }
            });
            thread.start();
            return thread;
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
                    parallelQuickSort(arr, low, pivotIndex - 1);
                }

                if (activeThreads.get() < threads) {
                    rightThread = startNewThread(arr, pivotIndex + 1, high);
                } else {
                    parallelQuickSort(arr, pivotIndex + 1, high);
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

        private class Worker implements Runnable {
            private final int low;
            private final int high;
            private final int[] arr;

            public Worker(int[] arr, int low, int high) {
                this.arr = arr;
                this.low = low;
                this.high = high;
            }

            public void run() {
                parallelQuickSort(arr, low, high);
            }
        }
}
