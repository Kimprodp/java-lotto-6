package lotto.domain;

import java.util.List;

public class WinningNumber {

    private List<Integer> winningNumbers;
    private int bonusNumber;

    public WinningNumber(List<Integer> winningNumbers, int bonusNumber) {
        this.winningNumbers = validateWinNumber(winningNumbers);
        this.bonusNumber = validateBonusNumber(bonusNumber);
    }

    public List<Integer> getWinningNumbers() {
        return winningNumbers;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }

    private List<Integer> validateWinNumber(List<Integer> winNumber) {
        validateSize(winNumber);
        validateRangeOfWinNumber(winNumber);
        validateDuplicate(winNumber);
        return winNumber;
    }

    private int validateBonusNumber(int bonusNumber) {
        validateRangeOfBonusNumber(bonusNumber);
        validateDuplicateWithWinningNumbers(bonusNumber);
        return bonusNumber;
    }

    private void validateSize(List<Integer> winNumber) {
        if (winNumber.size() != 6) {
            throw new IllegalArgumentException("당첨 번호의 개수는 6개 이어야 합니다.");
        }
    }

    private void validateRangeOfWinNumber(List<Integer> winNumber) {
        boolean isOverRange = winNumber.stream()
                .anyMatch(n -> n < 1 || n > 45);

        if (isOverRange) {
            throw new IllegalArgumentException("당첨 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
    }

    private void validateDuplicate(List<Integer> winNumber) {
        int count = (int) winNumber.stream()
                .distinct()
                .count();
        if (count != 6) {
            throw new IllegalArgumentException("중복된 숫자가 있습니다.");
        }
    }

    private void validateRangeOfBonusNumber(int bonusNumber) {
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException("보너스 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
    }

    private void validateDuplicateWithWinningNumbers(int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 달라야 합니다.");
        }
    }
}
