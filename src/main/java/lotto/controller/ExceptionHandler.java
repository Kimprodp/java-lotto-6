package lotto.controller;

import java.util.function.Supplier;
import lotto.view.OutputView;

public class ExceptionHandler {

    public static <T> T retryInput(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }
}
