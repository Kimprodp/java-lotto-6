package lotto.view;

import lotto.view.contants.Message;

public class OutputView {

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void printMessage(Message message) {
        System.out.println(message.getMessage());
    }

    public static void printMessage(Message message, Object target) {
        System.out.println(String.format(message.getMessage(), target));
    }

    public static void printError(String message) {
        System.out.println(message);
    }

    public static void printResult(Message message, int rank, int amount, int numberOfWinning) {
        System.out.println(String.format(message.getMessage(), rank, amount, numberOfWinning));
    }
}
