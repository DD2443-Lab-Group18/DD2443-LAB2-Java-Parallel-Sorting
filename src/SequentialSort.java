public class SequentialSort implements Sorter {

        private final boolean isQuicksort;

        public SequentialSort(boolean isQuicksort) {
                this.isQuicksort = isQuicksort;
        }

        public SequentialSort() {
                this.isQuicksort = true;
        }

        // Main function to call QuickSort
        public static void quickSort(int[] array, int low, int high) {
                // Exit condition
                if (low < high) {
                        // Find the pivot such that small elements < pivot < large elements
                        int pivot = partition(array, low, high);

                        quickSort(array, low, pivot - 1);
                        quickSort(array, pivot + 1, high);
                }
        }

        // Main function to call MergeSort
        public static void mergeSort(int[] array, int left, int right) {
                // Code that hasn't been implemented
                throw new UnsupportedOperationException("NOT YET IMPLEMENTED.");
        }

        // Function to partition the array based on the pivot
        public static int partition(int[] array, int low, int high) {
                // Always choose the rightest element as the pivot.
                int pivot = array[high];

                // left marks the last position where an element smaller than or equal
                // to the pivot is placed. All elements before or at index left should
                // be smaller than or equal to the pivot.
                int left = low - 1;

                for (int i = low; i < high; i++) {
                        if (array[i] <= pivot) {
                                left++;
                                // Swap array[left] and array[i]
                                int temp = array[left];
                                array[left] = array[i];
                                array[i] = temp;
                        }
                }

                // Swap the pivot element with the element at index left + 1
                int idx = left + 1;
                int temp = array[idx];
                array[idx] = array[high];
                array[high] = temp;

                // Return the index of the pivot
                return idx;
        }

        // Utility function to print the array
        public static void printArray(int[] arr) {
                // Sort
                if (arr.length == 0) {
                        throw new IllegalArgumentException("EMPTY ARRAY.");
                }
                quickSort(arr, 0, arr.length - 1);
                // Print
                for (int num : arr) {
                        System.out.print(num + " ");
                }
                System.out.println();
        }

        public void sort(int[] arr) {
                // TODO Implement your sorting algorithm.
                if (arr.length == 0) {
                        throw new IllegalArgumentException("EMPTY ARRAY.");
                }
                if (isQuicksort)
                        quickSort(arr, 0, arr.length - 1);
                else
                        mergeSort(arr, 0, arr.length - 1);
        }

        public int getThreads() {
                return 1;
        }
}
