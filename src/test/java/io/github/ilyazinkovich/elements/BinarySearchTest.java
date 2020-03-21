package io.github.ilyazinkovich.elements;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BinarySearchTest {

  @Test
  public void test() {
    assertEquals(4, binarySearch(new int[]{1, 3, 5, 7, 11}, 11));
  }

  private static int binarySearch(int[] array, int element) {
    int left = 0;
    int right = array.length - 1;
    while (left <= right) {
      int middle = left + (right - left) / 2;
      int middleElement = array[middle];
      if (middleElement == element) {
        return middle;
      } else if (middleElement < element) {
        left = middle + 1;
      } else {
        right = middle - 1;
      }
    }
    return -1;
  }
}
