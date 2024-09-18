import java.util.Arrays;
import java.util.Random;

import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;

public class TestExecutorService {

    @Test
    public void testNormalCase() {
        ExecutorServiceSort sorter = new ExecutorServiceSort(4); // Use 4 threads
        int[] array = {10, 7, 8, 9, 1, 5};
        int[] expected = {1, 5, 7, 8, 9, 10};
        sorter.sort(array);
        System.out.print(Arrays.toString(array));
        assertArrayEquals(expected, array);
    }

    @Test
    public void testArrayWithDuplicates() {
        ExecutorServiceSort sorter = new ExecutorServiceSort(4); // Use 4 threads
        int[] array = {3, 6, 8, 6, 2, 5, 7, 1, 8};
        int[] expected = {1, 2, 3, 5, 6, 6, 7, 8, 8};
        sorter.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    public void testAlreadySortedArray() {
        ExecutorServiceSort sorter = new ExecutorServiceSort(4); // Use 4 threads
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        sorter.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    public void testReverseSortedArray() {
        ExecutorServiceSort sorter = new ExecutorServiceSort(4); // Use 4 threads
        int[] array = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        sorter.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    public void testSingleElementArray() {
        ExecutorServiceSort sorter = new ExecutorServiceSort(4); // Use 4 threads
        int[] array = {42};
        int[] expected = {42};
        sorter.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    public void testEmptyArray() {
        ExecutorServiceSort sorter = new ExecutorServiceSort(4); // Use 4 threads
        int[] array = {};
        int[] expected = {};
        sorter.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    public void testLargeArray() {
        ExecutorServiceSort sorter = new ExecutorServiceSort(4); // Use 4 threads
        int[] array = generateRandomArray(10000); // Generating a large array
        int[] expected = array.clone();
        Arrays.sort(expected); // Sort the expected result using a standard algorithm
        sorter.sort(array);
        assertArrayEquals(expected, array);
    }

    private int[] generateRandomArray(int size) {
        Random rand = new Random();
        return rand.ints(size, 0, 10000).toArray();
    }
}
