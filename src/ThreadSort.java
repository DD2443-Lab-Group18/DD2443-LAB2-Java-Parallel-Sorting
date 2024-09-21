/**
 * Sort using Java's Thread, Runnable, start(), and join().
 */

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSort implements Sorter {
        private final int threads;
        private final AtomicInteger activeThreads; // Thread-safe int, so no need for lock().

        public ThreadSort(int threads) {
            this.threads = threads;
            this.activeThreads = new AtomicInteger(0);
        }

        public void sort(int[] arr) {
            activeThreads.set(0);
            parallelQuickSort(arr, 0, arr.length - 1);
        }

        private boolean threadIsAvailable() {
            return activeThreads.incrementAndGet() <= threads;
        }

        private Thread startNewThread(int[] arr, int low, int high) {
            Thread thread = new Thread(new Worker(arr, low, high));
            thread.start();
            return thread;
        }

        private void parallelQuickSort(int[] arr, int low, int high) {
            if (low < high) {
                int pivotIndex = SequentialSort.partition(arr, low, high);

                Thread leftThread = null, rightThread = null;

                if (threadIsAvailable()) {
                    leftThread = startNewThread(arr, low, pivotIndex - 1);
                } else {
                    activeThreads.decrementAndGet();  // Track active threads
                    parallelQuickSort(arr, low, pivotIndex - 1);
                }

                if (threadIsAvailable()) {
                    rightThread = startNewThread(arr, pivotIndex + 1, high);
                } else {
                    activeThreads.decrementAndGet();  // Track active threads
                    parallelQuickSort(arr, pivotIndex + 1, high);
                }

                if (leftThread != null) {
                    try {
                        leftThread.join();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } finally {
                        activeThreads.decrementAndGet();
                    }
                }

                if (rightThread != null) {
                    try {
                        rightThread.join();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } finally {
                        activeThreads.decrementAndGet();
                    }
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
