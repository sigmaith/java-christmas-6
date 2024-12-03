package christmas.domain.constants;

import christmas.exception.CustomException;
import christmas.exception.ErrorMessage;
import java.util.Arrays;

public enum CustomDayOfWeek {
    MONDAY(false),
    TUESDAY(false),
    WEDNESDAY(false),
    THURSDAY(false),
    FRIDAY(true),
    SATURDAY(true),
    SUNDAY(false);

    private boolean isHoliday;
    CustomDayOfWeek(boolean isHoliday) {
        this.isHoliday = isHoliday;
    }

    public boolean isHoliday(){
        return isHoliday;
    }

    public static CustomDayOfWeek from(int number) {
        return Arrays.stream(values()).filter(c -> c.ordinal() == number)
                .findFirst()
                .orElseThrow(() -> CustomException.from(ErrorMessage.INVALID_DATE));
    }
}
