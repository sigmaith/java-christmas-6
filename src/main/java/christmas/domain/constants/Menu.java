package christmas.domain.constants;

import static christmas.domain.constants.Type.APPETIZER;
import static christmas.domain.constants.Type.BEVERAGE;
import static christmas.domain.constants.Type.DESSERT;
import static christmas.domain.constants.Type.MAIN;

import christmas.exception.CustomException;
import christmas.exception.ErrorMessage;
import java.util.Arrays;

public enum Menu {
    MUSHROOM_SOUP(APPETIZER, "양송이 스프", 6_000),
    TAPAS(APPETIZER, "타파스", 5_500),
    CAESAR_SALAD(APPETIZER, "시저샐러드", 8_000),
    T_BONE_STEAK(MAIN, "티본스테이크", 55_000),
    BBQ_RIB(MAIN, "바비큐립", 54_000),
    SEAFOOD_PASTA(MAIN, "해산물파스타", 35_000),
    CHRISTMAS_PASTA(MAIN, "크리스마스파스타", 25_000),
    CHOCO_CAKE(DESSERT, "초코케이크", 15_000),
    ICE_CREAM(DESSERT, "아이스크림", 5_000),
    ZERO_COLA(BEVERAGE, "제로콜라", 3_000),
    RED_WINE(BEVERAGE, "레드와인", 60_000),
    CHAMPAGNE(BEVERAGE, "샴페인", 25_000);
    private final Type type;
    private final String name;
    private final Integer price;
    Menu(Type type, String name, Integer price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    public static boolean has(final String name) {
        return Arrays.stream(values()).anyMatch(menu -> menu.name.equals(name));
    }

    public static int getPriceBy(final String name) {
        return Arrays.stream(values())
                .filter(menu -> menu.name.equals(name)).map(menu -> menu.price).findFirst()
                .orElseThrow(() -> CustomException.from(ErrorMessage.INVALID_ORDER));
    }

    public static Menu getMenuBy(final String name) {
        return Arrays.stream(values())
                .filter(menu -> menu.name.equals(name)).findFirst()
                .orElseThrow(() -> CustomException.from(ErrorMessage.INVALID_ORDER));
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public boolean isDessertType() {
        return type.equals(DESSERT);
    }

    public boolean isMainType() {
        return type.equals(MAIN);
    }

    public static boolean isBeverageType(String name) {
        return Arrays.stream(values()).filter(menu -> menu.name.equals(name)).allMatch(menu -> menu.type.equals(BEVERAGE));
    }
}
