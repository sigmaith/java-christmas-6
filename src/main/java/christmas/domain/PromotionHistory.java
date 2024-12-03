package christmas.domain;

import christmas.controller.dto.ApplicationInfo;
import christmas.domain.constants.CustomDayOfWeek;
import christmas.domain.constants.Menu;
import christmas.domain.constants.Orders;
import christmas.domain.constants.StarDay;

public class PromotionHistory {
    private Date date;
    private Orders orders;
    private int discountedPrice = 0;
    private boolean christmasDDayPromotion = false;
    private boolean weekdayPromotion = false;
    private boolean weekendPromotion = false;
    private boolean stardayPromotion = false;
    private boolean giftPromotion = false;

    public PromotionHistory(Date date, Orders orders) {
        this.date = date;
        this.orders = orders;
    }

    public Menu getGiftByOriginalPrice() {
        if (orders.getWholePrices() >= 120_000) {
            discountedPrice += Menu.CHAMPAGNE.getPrice();
            giftPromotion = true;
            return Menu.CHAMPAGNE;
        }
        return null;
    }

    public ApplicationInfo getWeekdayOrWeekendPromotion() {
        int criteria = CustomDayOfWeek.FRIDAY.ordinal();
        int today = criteria + (date.getDate() - 1);
        if (CustomDayOfWeek.from(today % 7).isHoliday()) {
            weekendPromotion = true;
            int dcPriceOfMainMenu = orders.getDCPriceOfMainMenu();
            discountedPrice += dcPriceOfMainMenu;// 주말 -> 메인 메뉴 할인
            return new ApplicationInfo(true, dcPriceOfMainMenu);
        }
        weekdayPromotion = true;
        int dcPriceOfDessertMenu = orders.getDCPriceOfDessertMenu();
        discountedPrice += dcPriceOfDessertMenu; // 평일 -> 디저트 메뉴 할인
        return new ApplicationInfo(false, dcPriceOfDessertMenu);
    }

    public int getChristmasDDayPromotion() {
        if (date.getDate() > 25) {
            return 0;
        }
        christmasDDayPromotion = true;
        int dcPriceOfDDayPromotion = 1_000 + (date.getDate() - 1) * 100;
        discountedPrice += dcPriceOfDDayPromotion;
        return dcPriceOfDDayPromotion;
    }

    public int getStarDayPromotion() {
        if (StarDay.isStarday(date.getDate())) { // 스타데이
            stardayPromotion = true;
            discountedPrice += 1_000;
            return 1_000;
        }
        return 0;
    }

    public Menu getGiftPromotion() {
        if (giftPromotion) {
            return Menu.CHAMPAGNE;
        }
        return null;
    }
}
