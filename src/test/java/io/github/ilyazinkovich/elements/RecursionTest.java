package io.github.ilyazinkovich.elements;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class RecursionTest {

  @Test
  void towersOfHanoi() {
    int pegsCount = 3;
    List<Deque<Integer>> pegs = new ArrayList<>(pegsCount);
    for (int i = 0; i < pegsCount; i++) {
      pegs.add(new LinkedList<>());
    }
    int ringsCount = 10;
    for (int i = 1; i <= ringsCount; i++) {
      pegs.get(0).addLast(i);
    }
    System.out.println(pegs);
    computeSteps(ringsCount, pegs, 0, 1, 2);
  }

  private void computeSteps(int ringsCount, List<Deque<Integer>> pegs, int from, int to, int use) {
    if (ringsCount >= 1) {
      computeSteps(ringsCount - 1, pegs, from, use, to);
      pegs.get(to).addFirst(pegs.get(from).removeFirst());
      System.out.println(pegs);
      computeSteps(ringsCount - 1, pegs, use, to, from);
    }
  }
}
