package io.github.ilyazinkovich.elements;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PrimitiveTypesTest {

  @Test
  void specificParity() {
    assertEquals(0, PrimitiveTypes.parity(0b11110000));
    assertEquals(1, PrimitiveTypes.parity(0b11110001));
    assertEquals(0, PrimitiveTypes.parityOptimised(0b11110000));
    assertEquals(1, PrimitiveTypes.parityOptimised(0b11110001));
  }

  @Test
  public void specificSwap() {
    assertEquals(0b00001110, PrimitiveTypes.swapBits(0b10000110, 3, 7));
    assertEquals(0b10000110, PrimitiveTypes.swapBits(0b10000110, 2, 7));
    assertEquals(0b00001110, PrimitiveTypes.swapBitsAlternative(0b10000110, 3, 7));
    assertEquals(0b10000110, PrimitiveTypes.swapBitsAlternative(0b10000110, 2, 7));
  }

  @Test
  public void specificReverse() {
    assertEquals(0b01010101, PrimitiveTypes.reverseBits(0b10101010));
    assertEquals(0b00001111, PrimitiveTypes.reverseBits(0b11110000));
  }

  @Test
  public void specificClosestIntSameBitCount() {
    assertEquals(0b0100, PrimitiveTypes.closestIntSameBitCount(0b01000));
    assertEquals(0b01011, PrimitiveTypes.closestIntSameBitCount(0b0111));
    assertEquals(0b0100, PrimitiveTypes.closestIntSameBitCountImproved(0b01000));
    assertEquals(0b01011, PrimitiveTypes.closestIntSameBitCountImproved(0b0111));
  }

  @Test
  public void specificMultiply() {
    assertEquals(56, PrimitiveTypes.multiply(7, 8));
  }

  @Test
  public void specificDivide() {
    assertEquals(30, PrimitiveTypes.divide(360, 12));
  }

  @Test
  public void specificPalindrome() {
    assertTrue(PrimitiveTypes.isPalindrome(1221));
    assertTrue(PrimitiveTypes.isPalindrome(363));
    assertFalse(PrimitiveTypes.isPalindrome(1231));
  }

  @Test
  public void specificUniformRandom() {
    int random = PrimitiveTypes.uniformRandom(4, 9);
    assertTrue(random <= 9 && random >= 4);
  }
}