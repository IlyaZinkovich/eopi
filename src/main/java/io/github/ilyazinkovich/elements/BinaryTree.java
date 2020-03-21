package io.github.ilyazinkovich.elements;

public class BinaryTree {

  public static <T> Node<T> lca(Node<T> root, Node<T> node0, Node<T> node1) {
    return lcaHelper(root, node0, node1).ancestor;
  }

  private static <T> Status<T> lcaHelper(Node<T> root, Node<T> node0, Node<T> node1) {
    if (root == null) {
      return new Status<>(0, null);
    }
    Status<T> leftResult = lcaHelper(root.left, node0, node1);
    if (leftResult.numTargetNodes == 2) {
      return leftResult;
    }
    Status<T> rightResult = lcaHelper(root.right, node0, node1);
    if (rightResult.numTargetNodes == 2) {
      return rightResult;
    }
    int numTargetNodes = leftResult.numTargetNodes + rightResult.numTargetNodes +
        (root == node0 ? 1 : 0) + (root == node1 ? 1 : 0);
    return new Status<>(numTargetNodes, numTargetNodes == 2 ? root : null);
  }

  private static class Status<T> {

    public int numTargetNodes;
    public Node<T> ancestor;

    public Status(int numTargetNodes, Node<T> node) {
      this.numTargetNodes = numTargetNodes;
      this.ancestor = node;
    }
  }

  public static class Node<T> {

    public final T data;
    public final Node<T> left;
    public final Node<T> right;

    public Node(final T data, final Node<T> left, final Node<T> right) {
      this.data = data;
      this.left = left;
      this.right = right;
    }
  }
}
