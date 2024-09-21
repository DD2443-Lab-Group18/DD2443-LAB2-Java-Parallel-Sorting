/**
 * This test class was generated with the assistance of OpenAI's ChatGPT.
 * ChatGPT only provided test cases for this sorting algorithms.
 */

import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;

public class TestThread {

    @Test
    public void testNormalCase() {
        ThreadSort sorter = new ThreadSort(4); // Using 4 threads for parallelism
        int[] array = {10, 7, 8, 9, 1, 5};
        int[] expected = {1, 5, 7, 8, 9, 10};
        sorter.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    public void testArrayWithDuplicates() {
        ThreadSort sorter = new ThreadSort(4); // Use 4 threads
        int[] array = {3, 6, 8, 6, 2, 5, 7, 1, 8};
        int[] expected = {1, 2, 3, 5, 6, 6, 7, 8, 8};
        sorter.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    public void testAlreadySortedArray() {
        ThreadSort sorter = new ThreadSort(4); // Use 4 threads
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        sorter.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    public void testReverseSortedArray() {
        ThreadSort sorter = new ThreadSort(4); // Use 4 threads
        int[] array = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        sorter.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    public void testSingleElementArray() {
        ThreadSort sorter = new ThreadSort(4); // Use 4 threads
        int[] array = {42};
        int[] expected = {42};
        sorter.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    public void testEmptyArray() {
        ThreadSort sorter = new ThreadSort(4); // Use 4 threads
        int[] array = {};
        int[] expected = {};
        sorter.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    public void testLargeArray() {
        ThreadSort sorter = new ThreadSort(4); // Use 4 threads
        int[] array = new int[1000];
        int[] expected = new int[1000];
        for (int i = 0; i < array.length; i++) {
            array[i] = array.length - i;
            expected[i] = i + 1;
        }
        sorter.sort(array);
        assertArrayEquals(expected, array);
    }
}
