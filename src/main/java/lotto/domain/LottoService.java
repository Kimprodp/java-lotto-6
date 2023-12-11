package lotto.domain;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import lotto.domain.constants.WinningRank;

public class LottoService {

    private final WinningNumber winningNumber;
    private LinkedHashMap<WinningRank, Integer> result;

    public LottoService(WinningNumber winningNumber) {
        this.winningNumber = winningNumber;
        result = setResult();
    }

    public WinningResult requestWinningResult(List<Lotto> lottoTickets) {
        for (Lotto lotto : lottoTickets) {
            WinningRank winningRank = checkWiningResult(lotto);
            result.put(winningRank, checkValue(result, winningRank));
        }
        return new WinningResult(result);
    }

    private LinkedHashMap<WinningRank, Integer> setResult() {
        LinkedHashMap<WinningRank, Integer> result = new LinkedHashMap<>();
        for (WinningRank rank : WinningRank.values()) {
            result.put(rank, 0);
        }
        return result;
    }

    private WinningRank checkWiningResult(Lotto lotto) {
        int matchCount = checkWinningNumberMatch(lotto);
        return Arrays.stream(WinningRank.values())
                .filter(rank -> {
                    if (matchCount == WinningRank.SECOND.getNumberOfMatches()) {
                        return rank.getNumberOfMatches() == matchCount
                                && rank.isBonusMatch() == checkBonusNumberMatch(lotto);
                    }
                    return rank.getNumberOfMatches() == matchCount;
                })
                .findFirst()
                .orElse(WinningRank.NONE);
    }

    private int checkWinningNumberMatch(Lotto lotto) {
        return (int) lotto.getNumbers().stream()
                .filter(n -> winningNumber.getWinningNumbers().contains(n))
                .count();
    }

    private boolean checkBonusNumberMatch(Lotto lotto) {
        return lotto.getNumbers().stream()
                .anyMatch(n -> n == winningNumber.getBonusNumber());
    }

    private int checkValue( LinkedHashMap<WinningRank, Integer> winningResult, WinningRank winningRank) {
        if (winningResult.get(winningRank) != 0) {
            return winningResult.get(winningRank) + 1;
        }
        return 1;
    }
}
