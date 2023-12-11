package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LottoMachine {

    public static final int PRICE = 1_000;

    public List<Lotto> purchaseLotto(int amount) {
        int quantity = calculateLottoQuantity(validatePurchaseAmount(amount));
        List<Lotto> lottoTickets = new ArrayList<>();
        for (int i = 0; i < quantity; i ++) {
            lottoTickets.add(generateLotto(generateRandomNumber()));
        }
        return lottoTickets;
    }

    private int validatePurchaseAmount(int amount) {
        if (amount % PRICE != 0) {
            throw new IllegalArgumentException("로또 구매 금액은 1,000원 단위로만 입력 가능합니다.");
        }
        return amount;
    }

    private int calculateLottoQuantity(int amount) {
        return amount / PRICE;
    }

    private List<Integer> generateRandomNumber() {
        List<Integer> numbers;
        do {
            numbers = removeDuplicate(Randoms.pickUniqueNumbersInRange(1, 45, 6));
        } while (numbers.size() != 6);

        return numbers;
    }

    private List<Integer> removeDuplicate(List<Integer> numbers) {
        return numbers.stream()
                .distinct()
                .collect(Collectors.toList());
    }

    private Lotto generateLotto(List<Integer> numbers) {
        return new Lotto(numbers);
    }
}
