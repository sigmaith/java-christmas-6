package christmas.controller;

import christmas.domain.Date;
import christmas.domain.PromotionHistory;
import christmas.domain.constants.Orders;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.function.Supplier;

public class ApplyController {
    private final InputView inputView;
    private final OutputView outputView;
    public ApplyController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Date date = retry(this::getDateToVisit); // 방문할 날짜
        Orders orders = retry(this::getOrders); // 주문
        outputView.printOrdersAndOriginalPrice(orders); // 주문 메뉴, 할인 전 총 주문금액
        // 할인 적용
        PromotionHistory promotionHistory = new PromotionHistory(date, orders);
        printGiftPromotions(promotionHistory);
        applyAllPromotions(promotionHistory);
    }

    private Date getDateToVisit() {
        return inputView.getDateToVisit();
    }

    private Orders getOrders() {
        return inputView.getOrders();
    }

    private void printGiftPromotions(final PromotionHistory promotionHistory) {
        outputView.printChampagnePromotion(promotionHistory.getGiftByOriginalPrice());
    }

    private void applyAllPromotions(final PromotionHistory promotionHistory) {
        outputView.printPromotionHistoryPrefix();
        outputView.printGiftPromotion(promotionHistory.getGiftPromotion());
        outputView.printWeekDayOrWeekendPromotion(promotionHistory.getWeekdayOrWeekendPromotion()); // 평일/주말 프로모션
        outputView.printChristmasDDayPromotinon(promotionHistory.getChristmasDDayPromotion()); // 크리스마스 디데이 프로모션
        promotionHistory.getStarDayPromotion(); // 별표 날 프로모션
    }

    private static <T> T retry(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
