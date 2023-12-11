package lotto.domain.constants;

public enum WinningRank {

    NONE(0, false, 0),
    FIFTH(3, false, 5_000),
    FOURTH(4, false, 50_000),
    THIRD(5, false, 1_500_000),
    SECOND(5, true, 30_000_000),
    FIRST(6, false, 2_000_000_000);

    private final int numberOfMatches;
    private final boolean bonusMatch;
    private final int winningAmount;

    WinningRank(final int numberOfMatches, final boolean bonusMatch, final int winningAmount) {
        this.numberOfMatches = numberOfMatches;
        this. bonusMatch = bonusMatch;
        this. winningAmount = winningAmount;
    }

    public int getNumberOfMatches() {
        return numberOfMatches;
    }

    public boolean isBonusMatch() {
        return bonusMatch;
    }

    public int getWinningAmount() {
        return winningAmount;
    }
}
