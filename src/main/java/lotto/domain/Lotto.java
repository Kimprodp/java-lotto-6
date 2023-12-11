package lotto.domain;

import java.util.List;

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
            throw new IllegalArgumentException("로또 번호의 개수는 6개 이어야 합니다.");
        }
    }

    private void validateRange(List<Integer> numbers) {
        boolean isOverRange = numbers.stream()
                .anyMatch(n -> n < 1 || n > 45);

        if (isOverRange) {
            throw new IllegalArgumentException("로또 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        int count = (int) numbers.stream()
                .distinct()
                .count();
        if (count != 6) {
            throw new IllegalArgumentException("중복된 숫자가 있습니다.");
        }
    }
}
