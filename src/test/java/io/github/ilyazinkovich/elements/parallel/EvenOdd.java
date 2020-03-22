package io.github.ilyazinkovich.elements.parallel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.junit.jupiter.api.Test;

public class EvenOdd {

  private final Lock lock = new ReentrantLock(true);
  private AtomicBoolean odd = new AtomicBoolean(true);
  private AtomicInteger counter = new AtomicInteger(0);

  @Test
  public void test() throws InterruptedException {
    List<Integer> order = new ArrayList<>();
    Thread t1 = new Thread(() -> outputOdd(order));
    Thread t2 = new Thread(() -> outputEven(order));
    t1.start();
    t2.start();
    t1.join();
    t2.join();
    int lastThread = 0;
    for (int thread : order) {
      if (lastThread == 1) {
        assertEquals(2, thread);
        lastThread = 2;
      } else {
        assertEquals(1, thread);
        lastThread = 1;
      }
    }
  }

  private void outputOdd(List<Integer> order) {
    while (counter.get() < 99) {
      lock.lock();
      try {
        if (odd.get()) {
          System.out.printf("t1: %d%n", counter.incrementAndGet());
          order.add(1);
          odd.set(false);
        }
      } finally {
        lock.unlock();
      }
    }
  }

  private void outputEven(List<Integer> order) {
    while (counter.get() < 99) {
      lock.lock();
      try {
        if (!odd.get()) {
          System.out.printf("t2: %d%n", counter.incrementAndGet());
          order.add(2);
          odd.set(true);
        }
      } finally {
        lock.unlock();
      }
    }
  }
}
