package io.github.ilyazinkovich.elements;

import java.util.Random;
import java.util.function.IntSupplier;

public class PrimitiveTypes {

  static int parity(int number) {
    short result = 0;
    while (number != 0) {
      result ^= number & 1;
      number >>>= 1;
    }
    return result % 2;
  }

  static int parityOptimised(int number) {
    short result = 0;
    while (number != 0) {
      result ^= number & 1;
      number &= (number - 1);
    }
    return result % 2;
  }

  static int parityXOR(long number) {
    number ^= number >>> 32;
    number ^= number >>> 16;
    number ^= number >>> 8;
    number ^= number >>> 4;
    number ^= number >>> 2;
    number ^= number >>> 1;
    return (short) (number & 0x1);
  }

  static long swapBits(long number, int i, int j) {
    long ithPowerOf2 = 1 << i;
    long jthPowerOf2 = 1 << j;
    boolean ithBitIs1 = (number & ithPowerOf2) == ithPowerOf2;
    boolean jthBitIs1 = (number & jthPowerOf2) == jthPowerOf2;
    if (ithBitIs1 ^ jthBitIs1) {
      if (ithBitIs1) {
        return number - ithPowerOf2 + jthPowerOf2;
      } else {
        return number - jthPowerOf2 + ithPowerOf2;
      }
    }
    return number;
  }

  static long swapBitsAlternative(long number, int i, int j) {
    if (((number >>> i) & 1) != ((number >>> j) & 1)) {
      long mask = (1L << i) | (1L << j);
      return number ^ mask;
    }
    return number;
  }

  static long reverseBits(long number) {
    long result = 0;
    while (number != 0) {
      result <<= 1;
      result |= (number & 1);
      number >>= 1;
    }
    return result;
  }

  static long closestIntSameBitCount(long x) {
    int unsignBits = 63;
    for (int i = 0; i < unsignBits - 1; ++i) {
      if ((((x >>> i) & 1) != ((x >>> (i + 1)) & 1))) {
        x ^= (1L << i) | (1L << (i + 1));
        return x;
      }
    }
    throw new IllegalArgumentException("All bits are 0s or 1s");
  }

  static long closestIntSameBitCountImproved(long x) {
    long lowestSetBit = x & (~(x - 1));
    if (lowestSetBit == 1) {
      x = (x + 1) | (x >> 1);
    } else {
      x ^= lowestSetBit | (lowestSetBit >>> 1);
    }
    return x;
  }

  static long multiply(long x, long y) {
    long result = 0;
    while (y != 0) {
      if ((y & 1) == 1) {
        result += x; // implement using bit operations
      }
      y >>>= 1;
      x <<= 1;
    }
    return result;
  }

  static long divide(long x, long y) {
    long result = 0;
    int power = 32;
    long yPower = y << power;
    while (x >= y) {
      while (yPower > x) {
        yPower >>>= 1;
        --power;
      }
      result += 1L << power;
      x -= yPower;
    }
    return result;
  }

  static double power(double x, int y) {
    double result = 1.0;
    long power = y;
    if (y < 0) {
      power = -power;
      x = 1.0 / x;
    }
    while (power != 0) {
      if ((power & 1) != 0) {
        result *= x;
      }
      x *= x;
      power >>>= 1;
    }
    return result;
  }

  static long reverse(int x) {
    long result = 0;
    long xRemaining = Math.abs(x);
    while (xRemaining != 0) {
      result = result * 10 + xRemaining % 10;
      xRemaining /= 10;
    }
    return x < 0 ? -result : result;
  }

  static boolean isPalindrome(int x) {
    if (x < 0) {
      return false;
    }
    int n = (int) Math.floor(Math.log10(x)) + 1;
    int mask = (int) Math.pow(10, n - 1);
    while (x > 0) {
      if (x < 10) {
        return true;
      } else if (x % 10 == x / mask) {
        x %= mask;
        x /= 10;
        mask /= 100;
      } else {
        return false;
      }
    }
    return true;
  }

  static int uniformRandom(int a, int b) {
    Random random = new Random();
    IntSupplier zeroOrOne = () -> random.nextInt(2);
    int numberOfOutcomes = b - a + 1;
    int result;
    do {
      result = 0;
      for (int i = 0; (1 << i) < numberOfOutcomes; ++i) {
        result = (result << 1) | zeroOrOne.getAsInt();
      }
    } while (result >= numberOfOutcomes);
    return result + a;
  }
}