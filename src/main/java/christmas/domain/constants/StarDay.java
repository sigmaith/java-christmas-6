package christmas.domain.constants;

import java.time.Month;
import java.util.Arrays;

public enum StarDay {
    STAR_DAY_1(Month.of(12), 3),
    STAR_DAY_2(Month.of(12), 10),
    STAR_DAY_3(Month.of(12), 17),
    STAR_DAY_4(Month.of(12), 24),
    STAR_DAY_5(Month.of(12), 25),
    STAR_DAY_6(Month.of(12), 31);

    private Month month;
    private int date;

    StarDay(Month month, int date) {
        this.month = month;
        this.date = date;
    }

    public static boolean isStarday(int date) {
        return Arrays.stream(values()).anyMatch(starDay -> starDay.date == date);
    }
}
