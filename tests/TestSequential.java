import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;

public class TestSequential {

    // Test case 1: Normal case
    @Test
    public void testQuickSort_NormalCase() {
        int[] array = {10, 7, 8, 9, 1, 5};
        int[] expected = {1, 5, 7, 8, 9, 10};
        SequentialSort.quickSort(array, 0, array.length - 1);
        assertArrayEquals(expected, array);
    }

    // Test case 2: Array with duplicates
    @Test
    public void testQuickSort_WithDuplicates() {
        int[] array = {3, 6, 8, 6, 2, 5, 7, 1, 8};
        int[] expected = {1, 2, 3, 5, 6, 6, 7, 8, 8};
        SequentialSort.quickSort(array, 0, array.length - 1);
        assertArrayEquals(expected, array);
    }

    // Test case 3: Already sorted array
    @Test
    public void testQuickSort_AlreadySorted() {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        SequentialSort.quickSort(array, 0, array.length - 1);
        assertArrayEquals(expected, array);
    }

    // Test case 4: Reverse sorted array
    @Test
    public void testQuickSort_ReverseSorted() {
        int[] array = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        SequentialSort.quickSort(array, 0, array.length - 1);
        assertArrayEquals(expected, array);
    }

    // Test case 5: Single element array
    @Test
    public void testQuickSort_SingleElement() {
        int[] array = {42};
        int[] expected = {42};
        SequentialSort.quickSort(array, 0, 0);
        assertArrayEquals(expected, array);
    }

    // Test case 6: Empty array
    @Test
    public void testQuickSort_EmptyArray() {
        int[] array = {};
        int[] expected = {};
        SequentialSort.quickSort(array, 0, array.length - 1);
        assertArrayEquals(expected, array);
    }
}
