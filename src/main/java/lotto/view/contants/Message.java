package lotto.view.contants;

public enum Message {

    /* Input */
    INPUT_PURCHASE_AMOUNT("구입금액을 입력해 주세요."),
    INPUT_WINNING_NUMBER("\n" + "당첨 번호를 입력해 주세요."),
    INPUT_BONUS_NUMBER("\n" + "보너스 번호를 입력해 주세요."),

    /* Output */
    OUTPUT_PURCHASE_QUANTITY("\n" + "%d개를 구매했습니다."),
    OUTPUT_WINNING_RESULT("\n" + "당첨 통계"),
    OUTPUT_SEPARATION_INDICATION("---"),
    OUTPUT_WINNING_RESULT_HISTORY("%d개 일치 (%,d원) - %d개"),
    OUTPUT_WINNING_RESULT_HISTORY_WITH_BONUS("%d개 일치, 보너스 볼 일치 (%,d원) - %d개"),
    OUTPUT_RATE_OF_RETURN("총 수익률은 %.1f%%입니다."),

    /* ERROR */
    ERROR_INPUT_EMPTY("입력값이 없습니다."),
    ERROR_INPUT_NOT_INTEGER("숫자만 입력 가능합니다."),
    ERROR_INPUT_NOT_COMMA("숫자는 콤마(',')를 통해 구분해서 입력해주세요."),
    ERROR_PURCHASE_AMOUNT("로또 구매 금액은 1,000원 단위로만 입력 가능합니다."),
    ERROR_LOTTO_NUMBERS_SIZE("로또 번호의 개수는 6개 이어야 합니다."),
    ERROR_LOTTO_NUMBERS_RANGE("로또 번호는 1부터 45 사이의 숫자여야 합니다."),
    ERROR_LOTTO_NUMBERS_DUPLICATES("중복된 숫자가 있습니다."),
    ERROR_BONUS_NUMBER_RANGE("보너스 번호는 1부터 45 사이의 숫자여야 합니다."),
    ERROR_BONUS_NUMBER_DUPLICATE("보너스 번호는 당첨 번호와 달라야 합니다.");

    public static final String ERROR_PREFIX = "[ERROR] ";
    private String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getErrorMessage() {
        return ERROR_PREFIX + message;
    }
}
