package lotto.domain;

import java.util.LinkedHashMap;
import lotto.domain.constants.WinningRank;

public class WinningResult {

    private static final int PERCENTAGE = 100;

    private final LinkedHashMap<WinningRank, Integer> result;
    private int winningAmount;
    private double rateOfReturn;

    public WinningResult(LinkedHashMap<WinningRank, Integer> result, int purchaseAmount) {
        this.result = result;
        calculateWinningAmount();
        calculateRateOfReturn(purchaseAmount);
    }

    public LinkedHashMap<WinningRank, Integer> getResult() {
        return result;
    }

    public int getWinningAmount() {
        return winningAmount;
    }

    public double getRateOfReturn() {
        return rateOfReturn;
    }

    private void calculateWinningAmount() {
        for (WinningRank rank : result.keySet()) {
            winningAmount += rank.getWinningAmount() * result.get(rank);
        }
    }

    private void calculateRateOfReturn(int purchaseAmount) {
        rateOfReturn = (double) winningAmount / purchaseAmount * PERCENTAGE;
    }
}
