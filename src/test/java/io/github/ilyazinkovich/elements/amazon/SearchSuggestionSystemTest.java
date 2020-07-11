package io.github.ilyazinkovich.elements.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class SearchSuggestionSystemTest {

  private static int LEFT = 0;

  static int binarySearch(String[] array, String prefix) {
    int left = LEFT;
    int right = array.length - 1;
    int result = -1;
    while (left <= right) {
      int index = left + Math.floorDiv(right - left, 2);
      //      System.out.println(index);
      if (array[index].startsWith(prefix)) {
        result = index;
        if (index > 0) {
          right = index - 1;
        } else {
          return result;
        }
      } else {
        if (array[index].compareTo(prefix) < 0) {
          if (left == index) {
            left++;
          } else {
            left = index;
          }
        } else {
          right = index;
        }
      }
    }
    LEFT = left;
    return result;
  }

  @Test
  public void test() {
    System.out.println(suggestedProducts(new String[]{"mobile", "moneypot", "monitor", "mouse", "mousepad"}, "mon"));
    System.out.println(suggestedProducts(new String[]{"havana"}, "havana"));
    System.out.println(suggestedProducts(new String[]{"havana"}, "tatiana"));
  }

//  public List<List<String>> suggestedProducts(String[] products, String searchWord) {
//    Arrays.sort(products);
//    System.out.println(Arrays.toString(products));
//    List<List<String>> result = new ArrayList<>();
//    for (int i = 1; i <= searchWord.length(); i++) {
//      String prefix = searchWord.substring(0, i);
//      int index = binarySearch(products, prefix);
//      if (index == -1) {
//        result.add(new ArrayList<>());
//      } else {
//        List<String> subResult = new ArrayList<>();
//        subResult.add(products[index]);
//        for (int j = index + 1; j < products.length && j < index + 3; j++) {
//          if (products[j].startsWith(prefix)) {
//            subResult.add(products[j]);
//          } else {
//            break;
//          }
//        }
//        result.add(subResult);
//      }
//    }
//    return result;
//  }

  public List<List<String>> suggestedProducts(String[] products, String searchWord) {
    Arrays.sort(products);
    List<List<String>> result = new ArrayList<>();
    for (int i = 1; i <= searchWord.length(); i++) {
      String prefix = searchWord.substring(0, i);
      List<String> top3 = new ArrayList<>();
      for (int j = 0; j < products.length; j++) {
        if (products[j].startsWith(prefix)) {
          top3.add(products[j]);
        }
        if (!top3.isEmpty() && products[j].startsWith(prefix)) {
          break;
        }
        if (top3.size() == 3) {
          break;
        }
      }
      result.add(top3);
    }
    return result;
  }

//  static class Solution {
//
//    private static class Node {
//      TreeMap<Character, Node> nodes;
//      boolean isLeaf;
//
//      Node() {
//        nodes = new TreeMap<>();
//        isLeaf = false;
//      }
//
//      Node(boolean isLeaf) {
//        this.nodes = new TreeMap<>();
//        this.isLeaf = isLeaf;
//      }
//
//      Node(TreeMap<Character, Node> nodes, boolean isLeaf) {
//        this.nodes = nodes;
//        this.isLeaf = isLeaf;
//      }
//    }
//
//    public static void addWord(TreeMap<Character, Node> root, String word) {
//      TreeMap<Character, Node> current = root;
//      for (int i = 0; i < word.length(); i++) {
//        char c = word.charAt(i);
//        boolean isLeaf = i == word.length() - 1;
//        if (current.containsKey(c)) {
//          Node node = current.get(c);
//          node.isLeaf = isLeaf;
//          current = node.nodes;
//        } else {
//          Node newNode = new Node(isLeaf);
//          current.put(c, newNode);
//          current = newNode.nodes;
//        }
//      }
//    }
//
//    public static List<String> top3(TreeMap<Character, Node> root) {
//      List<String> list = new ArrayList<>();
//      TreeMap<Character, Node> current = root;
//      Stack<Node> stack = new Stack();
//      return list;
//    }
//
//    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
//      TreeMap<Character, Node> root = new TreeMap<>();
//      for (String product : products) {
//        addWord(root, product);
//      }
//      for (int i = 1; i < searchWord.length(); i++) {
//        TreeMap<Character, Node> current = root;
//        for (int j = 0; j < i; j++) {
//          char c = searchWord.charAt(j);
//          if (current.containsKey(c)) {
//            current = current.get(c).nodes;
//          } else {
//            break;
//          }
//        }
//      }
//      return new ArrayList<>(new ArrayList<>());
//    }
//  }
}
