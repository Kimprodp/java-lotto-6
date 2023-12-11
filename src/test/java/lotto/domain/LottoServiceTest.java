package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoServiceTest {
    @DisplayName("로또의 당첨 결과를 반환한다.")
    @Test
    void generateLottoByPurchaseAmount() {
        //given
        LottoMachine lottoMachine = new LottoMachine();
        List<Lotto> lottoTickets = lottoMachine.purchaseLotto(1000);
        WinningNumber winningNumber = new WinningNumber(List.of(1, 2, 3, 4, 5, 6), 8);
        LottoService lottoService = new LottoService(winningNumber);

        //when, then
        assertThat(lottoService.requestWinningResult(lottoTickets)).isExactlyInstanceOf(WinningResult.class);
    }

}