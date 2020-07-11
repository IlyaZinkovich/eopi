package io.github.ilyazinkovich.elements.amazon;

import java.util.Stack;
import org.junit.jupiter.api.Test;

public class NumberOfIslandsTest {

  public int numIslands(char[][] grid) {
    int count = 0;
    int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        if (grid[i][j] == '1') {
          Stack<int[]> stack = new Stack<>();
          stack.push(new int[]{i, j});
          while (!stack.isEmpty()) {
            int[] indices = stack.peek();
            grid[indices[0]][indices[1]] = '0';
            boolean nowhereToGo = true;
            for (int[] dir : dirs) {
              int ii = indices[0] + dir[0];
              int jj = indices[1] + dir[1];
              if (ii >= 0 && ii < grid.length
                  && jj >= 0 && jj < grid[ii].length) {
                if (grid[ii][jj] == '1') {
                  stack.push(new int[]{ii, jj});
                  nowhereToGo = false;
                  break;
                }
              }
            }
            if (nowhereToGo) {
              stack.pop();
            }
          }
          count++;
        }
      }
    }
    return count;
  }

  @Test
  public void test() {
    System.out.println(numIslands(new char[][]{
        {'1', '1', '1', '1', '0'},
        {'1', '1', '0', '1', '0'},
        {'1', '1', '0', '0', '0'},
        {'0', '0', '0', '0', '0'}}));
  }
}
