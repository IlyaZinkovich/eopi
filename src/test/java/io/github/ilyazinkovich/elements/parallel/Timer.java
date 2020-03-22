package io.github.ilyazinkovich.elements.parallel;

import static java.util.Comparator.comparing;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Random;
import org.junit.jupiter.api.Test;

public class Timer {

  @Test
  public void test() throws InterruptedException {
    Thread main = new Thread(this::runScheduler);
    main.start();
    main.join();
  }

  private void runScheduler() {
    PriorityQueue<Task> schedule = new PriorityQueue<>(comparing(task -> task.startTime));
    Random random = new Random(System.currentTimeMillis());
    int taskId = 0;
    while (taskId < 1000) {
      if (needToRunTask(schedule)) {
        schedule.poll().run();
      }
      scheduleNewTask(schedule, random, taskId++);
      sleepTillNextTaskStartOr100Ms(schedule);
    }
  }

  private void sleepTillNextTaskStartOr100Ms(PriorityQueue<Task> schedule) {
    try {
      Thread.sleep(sleepTimeTillNextTaskStartOr100Ms(schedule));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private boolean needToRunTask(PriorityQueue<Task> schedule) {
    return schedule.peek() != null
        && !schedule.peek().startTime.isAfter(Instant.now());
  }

  private void scheduleNewTask(PriorityQueue<Task> schedule, Random random, int taskId) {
    Instant taskStartTime = Instant.now().plusMillis(random.nextInt(1000));
    schedule.add(new Task(taskId, taskStartTime));
  }

  private Long sleepTimeTillNextTaskStartOr100Ms(PriorityQueue<Task> schedule) {
    return Optional.ofNullable(schedule.peek())
        .map(t -> Duration.between(Instant.now(), t.startTime).toMillis())
        .map(time -> time < 0 ? 0 : time)
        .filter(time -> time < 100)
        .orElse(100L);
  }

  static class Task {

    final int id;
    final Instant startTime;

    Task(int id, Instant startTime) {
      this.id = id;
      this.startTime = startTime;
    }

    void run() {
      System.out.println(
          id + " " + startTime + " " + Duration.between(startTime, Instant.now()).toMillis());
    }
  }
}
