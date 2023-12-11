package lotto.domain;

import java.util.LinkedHashMap;
import lotto.domain.constants.WinningRank;

public class WinningResult {

    private LinkedHashMap<WinningRank, Integer> result;

    public WinningResult(LinkedHashMap<WinningRank, Integer> result) {
        this.result = result;
    }

    public LinkedHashMap<WinningRank, Integer> getResult() {
        return result;
    }
}
