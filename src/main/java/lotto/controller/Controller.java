package lotto.controller;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.LottoService;
import lotto.domain.WinningNumber;
import lotto.domain.WinningResult;
import lotto.domain.constants.WinningRank;
import lotto.view.InputView;
import lotto.view.OutputView;
import lotto.view.contants.Message;

public class Controller {

    public void run() {
        List<Lotto> lottoTickets = setLotto();
        WinningNumber winningNumber = setWinningNumber();
        WinningResult winningResult = requestLottoResult(lottoTickets, winningNumber);
        showLottoResult(winningResult);
    }

    private List<Lotto> setLotto() {
        OutputView.printMessage(Message.INPUT_PURCHASE_AMOUNT);
        List<Lotto> lottoTickets = ExceptionHandler.retryInput(this::purchaseLotto);
        showPurchaseResult(lottoTickets);
        return lottoTickets;
    }

    private List<Lotto> purchaseLotto() {
        LottoMachine lottoMachine = new LottoMachine();
        return lottoMachine.purchaseLotto(inputPurchaseAmount());
    }

    private int inputPurchaseAmount() {
        return InputProcessor.validateNumber(InputView.readLine());
    }

    private void showPurchaseResult(List<Lotto> lottoTickets) {
        OutputView.printMessage(Message.OUTPUT_PURCHASE_QUANTITY, lottoTickets.size());
        for (Lotto lotto : lottoTickets) {
            OutputView.printMessage(String.valueOf(lotto.getNumbers()));
        }
    }

    private WinningNumber setWinningNumber() {
        OutputView.printMessage(Message.INPUT_WINNING_NUMBER);
        Lotto winningNumbers = ExceptionHandler.retryInput(this::setWinningLotto);

        OutputView.printMessage(Message.INPUT_BONUS_NUMBER);
        return ExceptionHandler.retryInput(() -> createWinningNumber(winningNumbers));
    }

    private Lotto setWinningLotto() {
        return new Lotto(InputProcessor.validateNumberGroup(InputView.readLine()));
    }

    private WinningNumber createWinningNumber(Lotto winningNumbers) {
        return new WinningNumber(winningNumbers, inputBonusNumber());
    }

    private int inputBonusNumber() {
        return InputProcessor.validateNumber(InputView.readLine());
    }

    private WinningResult requestLottoResult(List<Lotto> lottoTickets, WinningNumber winningNumber) {
        LottoService lottoService = new LottoService(winningNumber);
        return lottoService.requestWinningResult(lottoTickets);
    }

    private void showLottoResult(WinningResult winningResult) {
        OutputView.printMessage(Message.OUTPUT_WINNING_RESULT);
        OutputView.printMessage(Message.OUTPUT_SEPARATION_INDICATION);
        printWinningInformation(winningResult);
        printRateOfReturn(winningResult);
    }

    private void printWinningInformation(WinningResult winningResult) {
        for (WinningRank rank : winningResult.getResult().keySet()) {
            if (rank != WinningRank.NONE && rank != WinningRank.SECOND) {
                OutputView.printResult(Message.OUTPUT_WINNING_RESULT_HISTORY, rank.getNumberOfMatches(),
                        rank.getWinningAmount(), winningResult.getResult().get(rank));
            }
            if (rank == WinningRank.SECOND) {
                OutputView.printResult(Message.OUTPUT_WINNING_RESULT_HISTORY_WITH_BONUS, rank.getNumberOfMatches(),
                        rank.getWinningAmount(), winningResult.getResult().get(rank));
            }
        }
    }

    private void printRateOfReturn(WinningResult winningResult) {
        OutputView.printMessage(Message.OUTPUT_RATE_OF_RETURN, winningResult.getRateOfReturn());
    }
}
