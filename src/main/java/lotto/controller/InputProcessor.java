package lotto.controller;

import java.util.ArrayList;
import java.util.List;
import lotto.view.contants.Message;

public class InputProcessor {

    public static int validateNumber(String inputValue) {
        validateEmpty(inputValue);
        validateInteger(inputValue);
        return Integer.parseInt(inputValue);
    }

    public static List<Integer> validateNumberGroup(String inputValue) {
        validateEmpty(inputValue);
        return validateSplit(inputValue);

    }

    private static void validateEmpty(String inputValue) {
        if (inputValue.isEmpty()) {
            throw new IllegalArgumentException(Message.ERROR_INPUT_EMPTY.getErrorMessage());
        }
    }

    private static void validateInteger(String inputValue) {
        try {
            Integer.parseInt(inputValue);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(Message.ERROR_INPUT_NOT_INTEGER.getErrorMessage());
        }
    }

    private static List<Integer> validateSplit(String inputValue) {
        if (!inputValue.contains(",")) {
            throw new IllegalArgumentException(Message.ERROR_INPUT_NOT_COMMA.getErrorMessage());
        }

        String[] separatedValue = inputValue.split(",");
        List<Integer> numbers = new ArrayList<>();
        for (String value : separatedValue) {
            validateInteger(value);
            numbers.add(Integer.parseInt(value));

        }
        return numbers;
    }
}
