package christmas.domain.constants;

import christmas.exception.CustomException;
import christmas.exception.ErrorMessage;
import java.util.Arrays;

public enum Menu {
    MUSHROOM_SOUP("appetizer", "양송이 스프", 6_000),
    TAPAS("appetizer", "타파스", 5_500),
    CAESAR_SALAD("appetizer", "시저샐러드", 8_000),
    T_BONE_STEAK("main", "티본스테이크", 55_000),
    BBQ_RIB("main", "바비큐립", 54_000),
    SEAFOOD_PASTA("main", "해산물파스타", 35_000),
    CHRISTMAS_PASTA("main", "크리스마스파스타", 25_000),
    CHOCO_CAKE("dessert", "초코케이크", 15_000),
    ICE_CREAM("dessert", "아이스크림", 5_000),
    ZERO_COLA("beverage", "제로콜라", 3_000),
    RED_WINE("beverage", "레드와인", 60_000),
    CHAMPAGNE("beverage", "샴페인", 25_000);
    private final String type;
    private final String name;
    private final Integer price;
    Menu(String type, String name, Integer price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    public static boolean has(final String name) {
        return Arrays.stream(values()).anyMatch(menu -> menu.name.equals(name));
    }

    public static int getPriceBy(final String name) {
        Arrays.stream(values())
                .filter(menu -> menu.name.equals(name)).map(menu -> menu.price).findFirst()
                .orElseThrow(() -> CustomException.from(ErrorMessage.INVALID_ORDER));
    }
}
