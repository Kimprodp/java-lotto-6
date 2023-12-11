package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import lotto.domain.constants.WinningRank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningResultTest {

    private LottoService lottoService;

    @BeforeEach
    void beforeEachTest() {
        lottoService = new LottoService(new WinningNumber(List.of(1, 2, 3, 4, 5, 6), 8));
    }

    @DisplayName("6개가 일치할 경우 로또 1등 당첨이다.")
    @Test
    void confirmResultByAllMatch() {
        //given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6); // 구매자 로또 번호
        Lotto lotto = new Lotto(numbers);
        List<Lotto> lottoTickets = new ArrayList<>();
        lottoTickets.add(lotto);

        //when
        WinningResult winningResult = lottoService.requestWinningResult(lottoTickets);
        LinkedHashMap<WinningRank, Integer> result = winningResult.getResult();

        //then
        assertResult(result, 1, 0, 0, 0, 0, 0);
    }


    @DisplayName("5개가 일치하고, 보너스 번호가 일치할 경우 로또 2등 당첨이다.")
    @Test
    void confirmResultByFiveMatchAndBonusMatch() {
        //given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 8); // 구매자 로또 번호
        Lotto lotto = new Lotto(numbers);
        List<Lotto> lottoTickets = new ArrayList<>();
        lottoTickets.add(lotto);

        //when
        WinningResult winningResult = lottoService.requestWinningResult(lottoTickets);
        LinkedHashMap<WinningRank, Integer> result = winningResult.getResult();

        //then
        assertResult(result, 0, 1, 0, 0, 0, 0);
    }

    @DisplayName("5개가 일치하고, 보너스 번호가 일치하지 않을 경우 로또 3등 당첨이다.")
    @Test
    void confirmResultByFiveMatch() {
        //given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 10); // 구매자 로또 번호
        Lotto lotto = new Lotto(numbers);
        List<Lotto> lottoTickets = new ArrayList<>();
        lottoTickets.add(lotto);

        //when
        WinningResult winningResult = lottoService.requestWinningResult(lottoTickets);
        LinkedHashMap<WinningRank, Integer> result = winningResult.getResult();

        //then
        assertResult(result, 0, 0, 1, 0, 0, 0);
    }

    @DisplayName("4개가 일치할 경우 로또 4등 당첨이다.")
    @Test
    void confirmResultByFourMatch() {
        //given
        List<Integer> numbers = List.of(1, 2, 3, 4, 9, 10); // 구매자 로또 번호
        Lotto lotto = new Lotto(numbers);
        List<Lotto> lottoTickets = new ArrayList<>();
        lottoTickets.add(lotto);

        //when
        WinningResult winningResult = lottoService.requestWinningResult(lottoTickets);
        LinkedHashMap<WinningRank, Integer> result = winningResult.getResult();

        //then
        assertResult(result, 0, 0, 0, 1, 0, 0);
    }

    @DisplayName("3개가 일치할 경우 로또 5등 당첨이다.")
    @Test
    void confirmResultByThreeMatch() {
        //given
        List<Integer> numbers = List.of(1, 2, 3, 8, 9, 10); // 구매자 로또 번호
        Lotto lotto = new Lotto(numbers);
        List<Lotto> lottoTickets = new ArrayList<>();
        lottoTickets.add(lotto);

        //when
        WinningResult winningResult = lottoService.requestWinningResult(lottoTickets);
        LinkedHashMap<WinningRank, Integer> result = winningResult.getResult();

        //then
        assertResult(result, 0, 0, 0, 0, 1, 0);
    }

    @DisplayName("일치하는 숫자가 3개 미만일 경우 로또 당첨되지 않는다.")
    @Test
    void confirmResultByNoneMatch() {
        //given
        List<Integer> numbers = List.of(1, 2, 7, 8, 9, 10); // 구매자 로또 번호
        Lotto lotto = new Lotto(numbers);
        List<Lotto> lottoTickets = new ArrayList<>();
        lottoTickets.add(lotto);

        //when
        WinningResult winningResult = lottoService.requestWinningResult(lottoTickets);
        LinkedHashMap<WinningRank, Integer> result = winningResult.getResult();

        //then
        assertResult(result, 0, 0, 0, 0, 0, 1);
    }


    private void assertResult(LinkedHashMap<WinningRank, Integer> result, int first, int second, int third, int fourth,
                              int fifth, int none) {
        assertThat(result.get(WinningRank.FIRST)).isEqualTo(first);
        assertThat(result.get(WinningRank.SECOND)).isEqualTo(second);
        assertThat(result.get(WinningRank.THIRD)).isEqualTo(third);
        assertThat(result.get(WinningRank.FOURTH)).isEqualTo(fourth);
        assertThat(result.get(WinningRank.FIFTH)).isEqualTo(fifth);
        assertThat(result.get(WinningRank.NONE)).isEqualTo(none);
    }

}