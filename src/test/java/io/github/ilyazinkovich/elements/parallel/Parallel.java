package io.github.ilyazinkovich.elements.parallel;

import static java.util.Arrays.stream;
import static java.util.concurrent.CompletableFuture.supplyAsync;
import static java.util.concurrent.Executors.newFixedThreadPool;
import static java.util.stream.IntStream.range;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.function.Supplier;

class Parallel {

  private static final ExecutorService PARALLEL_EXECUTOR = newFixedThreadPool(24);

  static boolean check(Supplier<Boolean> action)
      throws InterruptedException, ExecutionException {
    CompletableFuture[] results = range(0, 1000000)
        .mapToObj(i -> supplyAsync(action, PARALLEL_EXECUTOR))
        .toArray(CompletableFuture[]::new);
    CompletableFuture.allOf(results).get();
    return completedSuccessfully(results);
  }

  private static boolean completedSuccessfully(CompletableFuture[] results) {
    return stream(results).allMatch(future -> {
      try {
        return (Boolean) future.get();
      } catch (InterruptedException | ExecutionException e) {
        throw new RuntimeException(e);
      }
    });
  }
}
