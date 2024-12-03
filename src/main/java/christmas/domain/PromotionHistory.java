package christmas.domain;

import christmas.domain.constants.CustomDayOfWeek;
import christmas.domain.constants.Menu;
import christmas.domain.constants.Orders;

public class PromotionHistory {
    private Date date;
    private Orders orders;
    private int discountedPrice = 0;
    private boolean christmasDDayPromotion = false;
    private boolean weekdayPromotion = false;
    private boolean weekendPromotion = false;
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

    public void getWeekdayOrWeekendPromotion() {
        int criteria = CustomDayOfWeek.FRIDAY.ordinal();
        int today = criteria + (date.getDate() - 1);
        if (CustomDayOfWeek.from(today % 7).isHoliday()) {
            weekendPromotion = true;
            discountedPrice += orders.getDCPriceOfMainMenu(); // 주말 -> 메인 메뉴 할인
            return;
        }
        weekdayPromotion = true;
        discountedPrice += orders.getDCPriceOfDessertMenu(); // 평일 -> 디저트 메뉴 할인
    }

    public void getChristmasDDayPromotion() {
        if (date.getDate() > 25) {
            return;
        }
        christmasDDayPromotion = true;
        discountedPrice += 1_000 + (date.getDate() - 1) * 100;
    }
}
