package lotto.domain;

import java.util.List;
import lotto.view.contants.Message;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        this.numbers = validateLottoNumber(numbers);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    private List<Integer> validateLottoNumber(List<Integer> numbers) {
        validateSize(numbers);
        validateRange(numbers);
        validateDuplicate(numbers);
        return numbers;
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException(Message.ERROR_LOTTO_NUMBERS_SIZE.getErrorMessage());
        }
    }

    private void validateRange(List<Integer> numbers) {
        boolean isOverRange = numbers.stream()
                .anyMatch(n -> n < 1 || n > 45);

        if (isOverRange) {
            throw new IllegalArgumentException(Message.ERROR_LOTTO_NUMBERS_RANGE.getErrorMessage());
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        int count = (int) numbers.stream()
                .distinct()
                .count();
        if (count != 6) {
            throw new IllegalArgumentException(Message.ERROR_LOTTO_NUMBERS_DUPLICATES.getErrorMessage());
        }
    }
}
