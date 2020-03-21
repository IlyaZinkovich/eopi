package io.github.ilyazinkovich.elements;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

public class HeapTest {

  @Test
  public void test() {
    final PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
    IntStream.of(3, 1, 2).forEach(queue::offer);
    IntStream.range(0, 3).forEach(i -> System.out.println(queue.poll()));
  }
}
