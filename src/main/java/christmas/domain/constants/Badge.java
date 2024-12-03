package christmas.domain.constants;


import java.awt.font.NumericShaper.Range;
import java.util.stream.IntStream;

public enum Badge {
    STAR(IntStream.range(5_000, 10_000), "별"),
    TREE(IntStream.range(10_000, 20_000), "트리"),
    SANTA(IntStream.range(20_000, Integer.MAX_VALUE), "산타");
    private IntStream range;
    private String name;

    Badge(IntStream range, String name) {
        this.range = range;
        this.name = name;
    }

    public static String getBadgeNameBy(int dcPrice) {
        if (dcPrice >= 20_000) {
            return SANTA.name;
        }
        if (dcPrice >= 10_000) {
            return TREE.name;
        }
        if (dcPrice >= 5_000) {
            return STAR.name;
        }
        return "없음";
    }
}
