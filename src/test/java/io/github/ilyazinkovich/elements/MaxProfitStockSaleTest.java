package io.github.ilyazinkovich.elements;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxProfitStockSaleTest {

  @Test
  public void maxProfitForEmptyStockPrices() {
    float[] stockPrices = new float[0];

    assertEquals(0, MaxProfitStockSale.calculate(stockPrices));
  }

  @Test
  public void maxProfitForSingleDayStockPrices() {
    float[] stockPrices = new float[]{1.5F};

    assertEquals(0, MaxProfitStockSale.calculate(stockPrices));
  }

  @Test
  public void maxProfitForDescendingStockPrices() {
    float max = Float.MAX_VALUE;
    float[] stockPrices = new float[100];
    for (int i = 0; i < stockPrices.length; i++) {
      stockPrices[i] = max--;
    }

    assertEquals(0, MaxProfitStockSale.calculate(stockPrices));
  }

  @Test
  public void maxProfitForAscendingStockPrices() {
    float max = 101.5F;
    final int n = 100;
    float[] stockPrices = new float[n];
    for (int i = 0; i < stockPrices.length; i++) {
      stockPrices[stockPrices.length - i - 1] = max--;
    }

    assertEquals(stockPrices[n - 1] - stockPrices[0], MaxProfitStockSale.calculate(stockPrices));
  }
}