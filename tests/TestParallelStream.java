/**
 * This test class was generated with the assistance of OpenAI's ChatGPT.
 * ChatGPT only provided test cases for this sorting algorithms.
 */

import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;

public class TestParallelStream {

    @Test
    public void testNormalCase() {
        ParallelStreamSort sorter = new ParallelStreamSort(4); // Use 4 threads
        int[] array = {10, 7, 8, 9, 1, 5};
        int[] expected = {1, 5, 7, 8, 9, 10};

        sorter.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    public void testArrayWithDuplicates() {
        ParallelStreamSort sorter = new ParallelStreamSort(4); // Use 4 threads
        int[] array = {3, 6, 8, 6, 2, 5, 7, 1, 8};
        int[] expected = {1, 2, 3, 5, 6, 6, 7, 8, 8};

        sorter.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    public void testAlreadySortedArray() {
        ParallelStreamSort sorter = new ParallelStreamSort(4); // Use 4 threads
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        sorter.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    public void testReverseSortedArray() {
        ParallelStreamSort sorter = new ParallelStreamSort(4); // Use 4 threads
        int[] array = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        sorter.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    public void testSingleElementArray() {
        ParallelStreamSort sorter = new ParallelStreamSort(4); // Use 4 threads
        int[] array = {42};
        int[] expected = {42};

        sorter.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    public void testEmptyArray() {
        ParallelStreamSort sorter = new ParallelStreamSort(4); // Use 4 threads
        int[] array = {};
        int[] expected = {};

        sorter.sort(array);
        assertArrayEquals(expected, array);
    }
}
