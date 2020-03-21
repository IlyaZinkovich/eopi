package io.github.ilyazinkovich.elements;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class BitwiseOperationsTest {

  @Test
  public void specificRightPropagation() {
    assertEquals(0b01011111, BitwiseOperations.rightPropagation(0b01010000));
  }

  @Test
  public void specificModuloAPowerOfTwo() {
    assertEquals(13, BitwiseOperations.moduloAPowerOfTwo(77, 64));
    assertEquals(1, BitwiseOperations.moduloAPowerOfTwo(129, 64));
  }

  @Test
  public void specificIsAPowerOfTwo() {
    assertTrue(BitwiseOperations.isAPowerOfTwo(128));
    assertFalse(BitwiseOperations.isAPowerOfTwo(489));
  }
}
