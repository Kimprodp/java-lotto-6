package lotto.domain;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import lotto.domain.constants.WinningRank;

public class LottoService {

    private WinningNumber winningNumber;

    public LottoService(WinningNumber winningNumber) {
        this.winningNumber = winningNumber;
    }

    public WinningResult requestWinningResult(List<Lotto> lottoTickets) {
        LinkedHashMap<WinningRank, Integer> winningResult = new LinkedHashMap<>();
        for (Lotto lotto : lottoTickets) {
            WinningRank winningRank = checkWiningResult(lotto);
            winningResult.put(winningRank, checkValue(winningResult, winningRank));
        }
        return new WinningResult(winningResult);
    }

    private WinningRank checkWiningResult(Lotto lotto) {
        int matchCount = checkWinningNumberMatch(lotto);
        return Arrays.stream(WinningRank.values())
                .filter(rank -> {
                    if (matchCount == WinningRank.SECOND.getNumberOfMatches()) {
                        return rank.isBonusMatch() == checkBonusNumberMatch(lotto);
                    }
                    return matchCount == rank.getNumberOfMatches();
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
        if (winningResult.containsKey(winningRank)) {
            return winningResult.get(winningRank) + 1;
        }
        return 1;
    }
}
