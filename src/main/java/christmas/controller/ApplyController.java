package christmas.controller;

import christmas.domain.Date;
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
        Integer originalPrice = orders.getWholePrices(), discountedPrice = 0;
        getChampagneBy(originalPrice, discountedPrice); // 샴페인 얻기

    }

    private Date getDateToVisit() {
        return inputView.getDateToVisit();
    }

    private Orders getOrders() {
        return inputView.getOrders();
    }

    private void getChampagneBy(Integer originalPrice, Integer discountedPrice) {
        boolean champagne = false;
        if (originalPrice >= 120_000) {
            discountedPrice += 25_000;
            champagne = true;
        }
        outputView.printChampagnePromotion(champagne);
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
