package io.github.ilyazinkovich.elements;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

class ArrayTest {

  @Test
  void specificRearrangeAroundPivot() {
    assertArrayEquals(new int[]{1, 2, 3, 4, 5},
        Array.rearrangeAroundPivot(new int[]{5, 4, 3, 2, 1}, 2));
    assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6},
        Array.rearrangeAroundPivot(new int[]{6, 5, 4, 3, 2, 1}, 3));
  }

  @Test
  void specificCanReachEnd() {
    assertFalse(Array.canReachEnd(new int[]{0, 1, 2, 3}));
    assertTrue(Array.canReachEnd(new int[]{3,3,1,0,2,0,1}));
  }

  @Test
  void specificDeleteDuplicates() {
    int[] withoutDuplicates = Array.deleteDuplicates(new int[]{1, 1, 2, 3, 3, 3, 4, 4});
    assertArrayEquals(new int[]{1, 2, 3, 4}, Arrays.copyOf(withoutDuplicates, 4));
  }

  @Test
  void specificPermute() {
    assertArrayEquals(new int[]{2, 3, 1, 4}, Array.permute(new int[]{1, 2, 3, 4}, new int[]{2, 0, 1, 3}));
  }
}
