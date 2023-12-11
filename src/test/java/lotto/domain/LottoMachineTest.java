package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoMachineTest {
    @DisplayName("로또 구매 금액이 1,000원 단위가 아닐 경우 예외가 발생한다.")
    @Test
    void confirmPurchaseAmount() {
        //given
        LottoMachine lottoMachine = new LottoMachine();
        int purchaseAmount = 1004;

        //when, then
        assertThatThrownBy(() -> lottoMachine.purchaseLotto(purchaseAmount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 구매 금액만큼 로또를 발행한다.")
    @Test
    void generateLottoByPurchaseAmount() {
        //given
        LottoMachine lottoMachine = new LottoMachine();
        int purchaseAmount = 3000;

        //when
        List<Lotto> lottoTickets = lottoMachine.purchaseLotto(purchaseAmount);

        //then
        assertThat(lottoTickets.size()).isEqualTo(3);
    }

}