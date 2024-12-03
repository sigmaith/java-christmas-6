package christmas.domain;

import christmas.exception.CustomException;
import christmas.exception.ErrorMessage;

public class Date {
    private final int date;
    public Date(final String input) {
        this(Integer.parseInt(input));
    }

    public Date(final int date) {
        validate(date);
        this.date = date;
    }

    private void validate(int date) {
        if (date < 1 || date > 31) {
            throw CustomException.from(ErrorMessage.INVALID_DATE);
        }
    }

    public int getDate() {
        return date;
    }
}

