package io.github.ilyazinkovich.elements.amazon;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayDeque;
import java.util.Queue;
import org.junit.jupiter.api.Test;

public class ZombieInMatrixTest {

  static int algorithm(int rows, int columns, int[][] grid) {
    Queue<int[]> queue = new ArrayDeque<>();
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        if (grid[i][j] == 1) {
          queue.add(new int[]{i, j});
        }
      }
    }
    int[][] directions = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    int days = -1;
    int zombiesCount = queue.size();
    while (!queue.isEmpty()) {
      int[] zombie = queue.poll();
      zombiesCount--;
      for (int[] direction : directions) {
        int i = zombie[0] + direction[0];
        int j = zombie[1] + direction[1];
        if (i >= 0 && i < rows && j >= 0 && j < columns
            && grid[i][j] == 0) {
          grid[i][j] = 1;
          queue.offer(new int[]{i, j});
        }
      }
      if (zombiesCount == 0) {
        days++;
        zombiesCount = queue.size();
      }
    }
    return days;
  }

  @Test
  public void test1() {
    int rows = 4;
    int columns = 5;
    int[][] grid = new int[][]{
        {1, 1, 1, 1, 1},
        {1, 1, 0, 1, 1},
        {1, 0, 0, 0, 1},
        {1, 1, 0, 1, 1}
    };

    assertEquals(2, algorithm(rows, columns, grid));
  }
}
