package io.github.ilyazinkovich.elements;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.junit.jupiter.api.Test;

public class HashMapTest {

  @Test
  void testSequentialCovering() {
    String paragraph =
        "My paramount object in this struggle is to save the Union, and is not either to save or to destroy slavery. If I could save the Union without freeing any slave I would do it, and if I could save it by freeing all the slaves I would do it; and if I could save it by freeing some and leaving others alone I would also do that.";
    sequentialCovering(paragraph.split("\\W+"), new String[]{"and", "the"});
  }

  @Test
  void testLongestDistinct() {
    String[] input = {"f", "s", "f", "e", "t", "w", "e", "n", "w", "e"};
    SubArray subArray = longestWithDistinctElements(input);
    assertArrayEquals(new String[]{"s", "f", "e", "t", "w"},
        Arrays.copyOfRange(input, subArray.left, subArray.right + 1));
  }

  private static SubArray sequentialCovering(String[] paragraph, String[] keywords) {
    Map<String, Integer> keywordToIndex = new HashMap<>();
    int[] latestOccurrence = new int[keywords.length];
    int[] shortestSubArrayLength = new int[keywords.length];
    for (int i = 0; i < keywords.length; i++) {
      keywordToIndex.put(keywords[i], i);
      latestOccurrence[i] = -1;
      shortestSubArrayLength[i] = Integer.MAX_VALUE;
    }
    int shortestDistance = Integer.MAX_VALUE;
    SubArray result = new SubArray(-1, -1);
    for (int i = 0; i < paragraph.length; i++) {
      Integer keywordIndex = keywordToIndex.get(paragraph[i]);
      if (keywordIndex != null) {
        if (keywordIndex == 0) {
          shortestSubArrayLength[0] = 1;
        } else if (shortestSubArrayLength[keywordIndex - 1] != Integer.MAX_VALUE) {
          shortestSubArrayLength[keywordIndex] =
              Math.min(
                  i - latestOccurrence[keywordIndex - 1] + shortestSubArrayLength[keywordIndex - 1],
                  shortestSubArrayLength[keywordIndex]);
        }
        latestOccurrence[keywordIndex] = i;
        if (keywordIndex == keywords.length - 1
            && shortestSubArrayLength[keywordIndex] < shortestDistance) {
          shortestDistance = shortestSubArrayLength[keywordIndex];
          result.left = i - shortestSubArrayLength[keywordIndex] + 1;
          result.right = i;
        }
      }
    }
    System.out.println(result);
    System.out
        .println(Arrays.toString(Arrays.copyOfRange(paragraph, result.left, result.right + 1)));
    return result;
  }

  private static SubArray longestWithDistinctElements(String[] array) {
    SubArray result = new SubArray(0, 0);
    Map<String, Integer> frequencies = new HashMap<>();
    Map<String, Integer> lastElementIndex = new HashMap<>();
    SubArray current = new SubArray(0, 0);
    for (int i = 0; i < array.length; i++) {
      String element = array[i];
      Integer frequency = frequencies.getOrDefault(element, 0);
      if (frequency == 0) {
        frequencies.put(element, frequency + 1);
        current.right++;
      } else {
        frequencies.put(element, 0);
        if (current.right - current.left > result.right - result.left) {
          result.right = current.right;
          result.left = current.left;
        }
        current.left = lastElementIndex.get(element) + 1;
        current.right = i;
      }
      lastElementIndex.put(element, i);
    }
    return result;
  }

  @Test
  void testLongestInterval() {
    longestInterval(new int[]{3, -2, 7, 9, 8, 1, 2, 0, -1, 5, 8});
  }

  private static int longestInterval(int[] array) {
    Map<Integer, Boolean> predecessors = new HashMap<>();
    for (int element : array) {
      predecessors.put(element, false);
    }
    for (int element : array) {
      if (predecessors.containsKey(element - 1)) {
        predecessors.put(element, true);
      }
    }
    System.out.println(predecessors);
    return 1;
  }

  static class SubArray {

    int left;
    int right;

    SubArray(final int left, final int right) {
      this.left = left;
      this.right = right;
    }

    @Override
    public boolean equals(final Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      final SubArray subArray = (SubArray) o;
      return left == subArray.left &&
          right == subArray.right;
    }

    @Override
    public int hashCode() {
      return Objects.hash(left, right);
    }

    @Override
    public String toString() {
      return "SubArray{" +
          "left=" + left +
          ", right=" + right +
          '}';
    }
  }
}
