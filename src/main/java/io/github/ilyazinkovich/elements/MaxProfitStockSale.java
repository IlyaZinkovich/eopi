package io.github.ilyazinkovich.elements;

class MaxProfitStockSale {

  static float calculate(float[] stockPrices) {
    float maxProfit = 0F;
    float minPrice = Float.MAX_VALUE;
    for (final float stockPrice : stockPrices) {
      if (stockPrice < minPrice) {
        minPrice = stockPrice;
      }
      if (stockPrice - minPrice > maxProfit) {
        maxProfit = stockPrice - minPrice;
      }
    }
    return maxProfit;
  }
}
