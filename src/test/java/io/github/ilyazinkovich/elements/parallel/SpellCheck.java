package io.github.ilyazinkovich.elements.parallel;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;
import org.junit.jupiter.api.Test;

public class SpellCheck {

  private static final Random RANDOM = new Random(System.currentTimeMillis());

  @Test
  public void test() throws ExecutionException, InterruptedException {
    String[] dictionary = {"a", "b", "c", "d"};
    Supplier<Boolean> unsafe = () ->
        UnsafeSpellCheckService.service(dictionary[RANDOM.nextInt(dictionary.length)]) != null;
    Supplier<Boolean> safe = () ->
        SafeSpellCheckService.service(dictionary[RANDOM.nextInt(dictionary.length)]) != null;
    assertTrue(Sequential.check(unsafe));
    // assertFalse(Parallel.check(unsafe));
    assertTrue(Parallel.check(safe));
  }

  static class UnsafeSpellCheckService {

    private static final int MAX_ENTRIES = 3;
    private static LinkedHashMap<String, String[]> cachedClosestStrings = new LinkedHashMap<String, String[]>() {
      protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > MAX_ENTRIES;
      }
    };

    public static String[] service(String word) {
      if (cachedClosestStrings.containsKey(word)) {
        return cachedClosestStrings.get(word);
      }
      String[] closestToLastWord = closestInDictionary(word);
      cachedClosestStrings.put(word, closestToLastWord);
      return closestToLastWord;
    }

    private static String[] closestInDictionary(String word) {
      return new String[]{word, word.toUpperCase(), word.toLowerCase()};
    }
  }

  static class SafeSpellCheckService {

    private static final int MAX_ENTRIES = 3;
    private static LinkedHashMap<String, String[]> cachedClosestStrings = new LinkedHashMap<String, String[]>() {
      protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > MAX_ENTRIES;
      }
    };

    public static String[] service(String word) {
      synchronized (SafeSpellCheckService.class) {
        if (cachedClosestStrings.containsKey(word)) {
          return cachedClosestStrings.get(word);
        }
      }
      String[] closestToLastWord = closestInDictionary(word);
      synchronized (SafeSpellCheckService.class) {
        cachedClosestStrings.put(word, closestToLastWord);
      }
      return closestToLastWord;
    }

    private static String[] closestInDictionary(String word) {
      return new String[]{word, word.toUpperCase(), word.toLowerCase()};
    }
  }
}
