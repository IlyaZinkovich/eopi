package io.github.ilyazinkovich.elements;

class BitwiseOperations {

  static int rightPropagation(int number) {
    return number + ((number & (~(number - 1))) - 1);
  }

  static int moduloAPowerOfTwo(int number, int mod) {
    return number & (mod - 1);
  }

  static boolean isAPowerOfTwo(int number) {
    return (number & (number - 1)) == 0;
  }
}
