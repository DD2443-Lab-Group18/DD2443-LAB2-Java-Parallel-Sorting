/**
 * Sort using Java's Thread, Runnable, start(), and join().
 */

import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSort implements Sorter {
        private final int threads;
        private int activeThreads; // Shared variable for active threads
        private final ReentrantLock lock = new ReentrantLock(); // Lock for thread count synchronization
        private static final int THRESHOLD = 16;

        public ThreadSort(int threads) {
            this.threads = threads;
            this.activeThreads = 0;
        }

        public void sort(int[] arr) {
            parallelQuickSort(arr, 0, arr.length - 1);
        }

        private Thread startNewThread(int[] arr, int low, int high) {
            this.lock.lock();
            if (this.activeThreads >= this.threads) {
                this.lock.unlock();
                SequentialSort.quickSort(arr, low, high);
            }
            else {
                this.activeThreads++;
                this.lock.unlock();
                Thread thread = new Thread(() -> {
                    try {
                        parallelQuickSort(arr, low, high);
                    } finally {
                        this.lock.lock();
                        this.activeThreads--;
                        this.lock.unlock();
                    }
                });
                thread.start();
                return thread;
            }
            return null;
        }

        private void parallelQuickSort(int[] arr, int low, int high) {
            if (low < high) {
                if (high - low < THRESHOLD) {
                    Arrays.sort(arr, low, high + 1);
                    return;
                }

                int pivotIndex = SequentialSort.partition(arr, low, high);

                Thread leftThread = startNewThread(arr, low, pivotIndex - 1);
                Thread rightThread = startNewThread(arr, pivotIndex + 1, high);

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
