package io.github.ilyazinkovich.elements.parallel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.Test;

public class DeadlockTransfer {

  private static final ExecutorService multi = Executors.newFixedThreadPool(24);

  @Test
  public void test() throws ExecutionException, InterruptedException {
    Account from = new Account(100);
    Account to = new Account(200);
    List<CompletableFuture> futures = new ArrayList<>();
    for (int i = 0; i < 100000; i++) {
      futures.add(Account.transfer(from, to, 30));
      futures.add(Account.transfer(to, from, 30));
    }
    CompletableFuture.allOf(futures.toArray(new CompletableFuture[1000])).get();
  }

  public static class Account {

    private int balance;
    private int id;
    private static int globalId;

    Account(int balance) {
      this.balance = balance;
      this.id = ++globalId;
    }

    private boolean move(Account to, int amount) {
      Account lock1 = id < to.id ? this : to;
      Account lock2 = id < to.id ? to : this;
      synchronized (lock1) {
        synchronized (lock2) {
          if (amount > balance) {
            return false;
          }
          to.balance += amount;
          this.balance -= amount;
          System.out.println("returning true");
          return true;
        }
      }
    }

    public static CompletableFuture transfer(Account from, Account to, int amount) {
      return CompletableFuture.supplyAsync(() -> from.move(to, amount), multi);
    }
  }
}
