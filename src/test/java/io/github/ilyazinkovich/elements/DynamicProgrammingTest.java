package io.github.ilyazinkovich.elements;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class DynamicProgrammingTest {

  @Test
  void testNumberOfCombinationsForFinalScore() {
    int finalScore = 12;
    int[] individualPlayScores = new int[]{2, 3, 7};
    int[][] combinations = new int[individualPlayScores.length][finalScore + 1];
    for (int i = 0; i < individualPlayScores.length; i++) {
      combinations[i][0] = 1;
      for (int j = 1; j <= finalScore; j++) {
        if (i == 0) {
          combinations[i][j] = j % individualPlayScores[i] == 0 ? 1 : 0;
        } else {
          int scoreToAdd = j - individualPlayScores[i];
          combinations[i][j] = combinations[i - 1][j] +
              (scoreToAdd > 0 ? combinations[i][scoreToAdd]
                  : (j % individualPlayScores[i] == 0 ? 1 : 0));
        }
      }
    }
    for (int[] combination : combinations) {
      System.out.println(Arrays.toString(combination));
    }
    assertEquals(4, combinations[individualPlayScores.length - 1][finalScore]);
  }

  @Test
  void testLevensteinDistance() {
    String a = "Carthorse";
    String b = "Orchestra";
    int[][] distanceBetweenPrefixes = new int[a.length()][b.length()];
    for (int[] row : distanceBetweenPrefixes) {
      Arrays.fill(row, -1);
    }
    assertEquals(8, computeDistanceBetweenPrefixes(a, a.length() - 1, b, b.length() - 1,
        distanceBetweenPrefixes));
  }

  @Test
  void testLevensteinDistanceSpaceOptimised() {
    String a = "Carthorse";
    String b = "Orchestra";
    int minLength = Math.min(a.length(), b.length());
    int[] distanceBetweenPrefixes = new int[minLength];
    Arrays.fill(distanceBetweenPrefixes, -1);
    String min;
    String max;
    if (a.length() == minLength) {
      min = a;
      max = b;
    } else {
      max = a;
      min = b;
    }
    assertEquals(8,
        computeDistanceBetweenPrefixesOptimised(min, min.length() - 1, max, max.length() - 1,
            distanceBetweenPrefixes));
  }

  @Test
  void testTraverse2d() {
    int n = 5;
    int m = 5;
    assertEquals(70, traverse(Math.min(n - 1, m - 1), Math.max(n - 1, m - 1), new int[Math.min(n, m)]));
  }

  private int traverse(int i, int j, int[] traversal) {
    if (i == 0 && j == 0) {
      return 1;
    } else {
      traversal[i] = (i == 0 ? 0 : traverse(i - 1, j, traversal))
          + (j == 0 ? 0 : traverse(i, j - 1, traversal));
      return traversal[i];
    }
  }

  private int computeDistanceBetweenPrefixesOptimised(final String a, int ai, final String b,
      int bi, final int[] distanceBetweenPrefixes) {
    if (ai < 0) {
      return bi + 1;
    } else if (bi < 0) {
      return ai + 1;
    } else {
      if (a.charAt(ai) == b.charAt(bi)) {
        distanceBetweenPrefixes[ai] = computeDistanceBetweenPrefixesOptimised(a, ai - 1, b, bi - 1,
            distanceBetweenPrefixes);
      } else {
        int distanceWithoutLastAChar = computeDistanceBetweenPrefixesOptimised(a, ai - 1, b, bi,
            distanceBetweenPrefixes);
        int distanceWithoutLastBChar = computeDistanceBetweenPrefixesOptimised(a, ai, b, bi - 1,
            distanceBetweenPrefixes);
        int distanceWithoutLastBothChars = computeDistanceBetweenPrefixesOptimised(a, ai - 1, b,
            bi - 1, distanceBetweenPrefixes);
        distanceBetweenPrefixes[ai] = 1 + Math
            .min(Math.min(distanceWithoutLastAChar, distanceWithoutLastBChar),
                distanceWithoutLastBothChars);
      }
    }
    return distanceBetweenPrefixes[ai];
  }

  private int computeDistanceBetweenPrefixes(String a, int ai, String b,
      int bi, int[][] distanceBetweenPrefixes) {
    if (ai < 0) {
      return bi + 1;
    } else if (bi < 0) {
      return ai + 1;
    } else if (distanceBetweenPrefixes[ai][bi] == -1) {
      if (a.charAt(ai) == b.charAt(bi)) {
        distanceBetweenPrefixes[ai][bi] = computeDistanceBetweenPrefixes(a, ai - 1, b, bi - 1,
            distanceBetweenPrefixes);
      } else {
        int distanceWithoutLastAChar = computeDistanceBetweenPrefixes(a, ai - 1, b, bi,
            distanceBetweenPrefixes);
        int distanceWithoutLastBChar = computeDistanceBetweenPrefixes(a, ai, b, bi - 1,
            distanceBetweenPrefixes);
        int distanceWithoutLastBothChars = computeDistanceBetweenPrefixes(a, ai - 1, b, bi - 1,
            distanceBetweenPrefixes);
        distanceBetweenPrefixes[ai][bi] = 1 + Math
            .min(Math.min(distanceWithoutLastAChar, distanceWithoutLastBChar),
                distanceWithoutLastBothChars);
      }
    }
    return distanceBetweenPrefixes[ai][bi];
  }
}
