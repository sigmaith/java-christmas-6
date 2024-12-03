package christmas.view;

import christmas.controller.dto.ApplicationInfo;
import christmas.domain.PromotionHistory;
import christmas.domain.constants.Menu;
import christmas.domain.constants.Orders;
import java.text.NumberFormat;

public class OutputView {
    public void printOrdersAndOriginalPrice(final Orders orders) {
        System.out.println("12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        System.out.println();
        System.out.println("<주문 메뉴>");
        System.out.println(orders.toString());
        System.out.println();
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(NumberFormat.getInstance().format(orders.getWholePrices()) + "원");
        System.out.println();
    }

    public void printChampagnePromotion(final Menu champagne) {
        System.out.println("<증정 메뉴>");
        if (champagne != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(champagne.getName()).append(" 1개").append("\n");
            System.out.println(sb);
            return;
        }
        System.out.println("없음");
    }

    public void printPromotionHistoryPrefix() {
        System.out.println("<혜택 내역>");
    }

    public void printGiftPromotion(final Menu champagne) {
        if (champagne != null) {
            System.out.printf("증정 이벤트: -%s원\n", NumberFormat.getInstance().format(champagne.getPrice()));
        }
    }

    public void printWeekDayOrWeekendPromotion(ApplicationInfo applicationInfo) {
        StringBuilder sb = new StringBuilder();
        if (applicationInfo.isWeekend()) {
            System.out.printf("주말 할인: -%s원\n", NumberFormat.getInstance().format(applicationInfo.dcPrice()));
            return;
        }
        System.out.printf("평일 할인: -%s원\n", NumberFormat.getInstance().format(applicationInfo.dcPrice()));
    }

    public void printChristmasDDayPromotinon(int dcPrice) {
        if (dcPrice != 0) {
            System.out.printf("크리스마스 디데이 할인: -%s원\n", NumberFormat.getInstance().format(dcPrice));
        }
    }

    public void printStarDayPromotion(int dcPrice) {
        if (dcPrice != 0) {
            System.out.printf("특별 할인: -%s원", NumberFormat.getInstance().format(dcPrice));
        }
    }

    public void printTotalBenefitAmountAndEstimatedPaymentAmount(PromotionHistory promotionHistory) {
        System.out.println("<총혜택 금액>");
        System.out.printf("-%s원", NumberFormat.getInstance().format(promotionHistory.getDiscountedPrice()));
        System.out.println();
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.printf("%s원", NumberFormat.getInstance().format(promotionHistory.getFinalPayment()));
    }

    public void printBadge(String badgeName) {
        System.out.println("<12월 이벤트 배지>");
        System.out.println(badgeName);
    }
}
