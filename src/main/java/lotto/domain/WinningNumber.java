package lotto.domain;

import java.util.List;
import lotto.view.contants.Message;

public class WinningNumber {

    private List<Integer> winningNumbers;
    private int bonusNumber;

    public WinningNumber(Lotto winningNumbers, int bonusNumber) {
        this.winningNumbers = winningNumbers.getNumbers();
        this.bonusNumber = validateBonusNumber(bonusNumber);
    }

    public List<Integer> getWinningNumbers() {
        return winningNumbers;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }

    private int validateBonusNumber(int bonusNumber) {
        validateRangeOfBonusNumber(bonusNumber);
        validateDuplicateWithWinningNumbers(bonusNumber);
        return bonusNumber;
    }


    private void validateRangeOfBonusNumber(int bonusNumber) {
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException(Message.ERROR_BONUS_NUMBER_RANGE.getErrorMessage());
        }
    }

    private void validateDuplicateWithWinningNumbers(int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(Message.ERROR_BONUS_NUMBER_DUPLICATE.getErrorMessage());
        }
    }
}
