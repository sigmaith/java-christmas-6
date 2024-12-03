package christmas.domain;

import christmas.exception.CustomException;
import christmas.exception.ErrorMessage;

public class Date {
    private final int date;
    public Date(final String input) {
        try{
            int date = Integer.parseInt(input);
            validate(date);
            this.date = date;
        } catch (NumberFormatException e) {
            throw CustomException.from(ErrorMessage.INVALID_DATE);
        }
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

