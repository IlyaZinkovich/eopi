package io.github.ilyazinkovich.elements;

class Array {

  static int[] rearrangeAroundPivot(int[] array, int i) {
    int pivot = array[i];
    int smaller = 0;
    int equal = 0;
    int larger = array.length - 1;
    while (equal < larger) {
      if (array[equal] < pivot) {
        swap(array, equal, smaller);
        smaller++;
        equal++;
      } else if (array[equal] > pivot) {
        swap(array, equal, larger);
        larger--;
      } else {
        equal++;
      }
    }
    return array;
  }

  static boolean canReachEnd(int[] maxAdvanceSteps) {
    int farthest = 0;
    for (int i = 0; i <= farthest && farthest < maxAdvanceSteps.length; i++) {
      farthest = Math.max(farthest, i + maxAdvanceSteps[i]);
    }
    return farthest >= maxAdvanceSteps.length;
  }

  static int[] deleteDuplicates(final int[] array) {
    if (array.length == 0) {
      return array;
    }
    int writeIndex = 1;
    for (int i = 1; i < array.length; i++) {
      if (array[i] != array[i - 1]) {
        array[writeIndex] = array[i];
        writeIndex++;
      }
    }
    return array;
  }

  static int[] permute(int[] array, int[] permutation) {
    int[] result = new int[array.length];
    for (int i = 0; i < permutation.length; i++) {
      result[permutation[i]] = array[i];
    }
    return result;
  }

  private static void swap(final int[] array, final int i, final int j) {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }
}
