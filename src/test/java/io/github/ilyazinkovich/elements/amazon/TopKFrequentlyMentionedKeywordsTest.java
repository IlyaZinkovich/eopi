package io.github.ilyazinkovich.elements.amazon;

import static java.util.Arrays.asList;
import static java.util.Collections.addAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class TopKFrequentlyMentionedKeywordsTest {

  static List<String> algorithm(int k, List<String> keywords, List<String> reviews) {
    Map<String, Integer> keywordsCount = new HashMap<>();
    for (String keyword : keywords) {
      keywordsCount.put(keyword, 0);
    }
    for (String review : reviews) {
      String normalized = review.toLowerCase().replaceAll(",.;", "");
      String[] words = normalized.split(" ");
      Set<String> uniqueWords = new HashSet<>();
      addAll(uniqueWords, words);
      for (String uniqueWord : uniqueWords) {
        if (keywordsCount.containsKey(uniqueWord)) {
          Integer count = keywordsCount.get(uniqueWord);
          keywordsCount.put(uniqueWord, count + 1);
        }
      }
    }
    keywords.sort((left, right) -> keywordsCount.get(right).compareTo(keywordsCount.get(left)));
    return keywords.subList(0, k);
  }

  @Test
  public void test1() {
    int k = 2;
    List<String> keywords = asList("anacell", "cetracular", "betacellular");
    List<String> reviews = asList(
        "Anacell provides the best services in the city",
        "betacellular has awesome services",
        "Best services provided by anacell, everyone should use anacell");

    assertEquals(asList("anacell", "betacellular"), algorithm(k, keywords, reviews));
  }

  @Test
  public void test2() {
    int k = 2;
    List<String> keywords = asList("anacell", "betacellular", "cetracular", "deltacellular",
        "eurocell");
    List<String> reviews = asList(
        "I love anacell Best services; Best services provided by anacell",
        "betacellular has great services",
        "deltacellular provides much better services than betacellular",
        "cetracular is worse than anacell",
        "Betacellular is better than deltacellular.");

    assertEquals(asList("betacellular", "anacell"), algorithm(k, keywords, reviews));
  }
}
