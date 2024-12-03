package christmas.view;

import christmas.domain.constants.Menu;
import christmas.domain.constants.Orders;

public class OutputView {
    public void printOrdersAndOriginalPrice(final Orders orders) {
        System.out.println("12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        System.out.println("<주문 메뉴>");
        System.out.println(orders.toString());
        System.out.println();
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(orders.getWholePrices() + "원");
    }

    public void printChampagnePromotion(final Menu champagne) {
        if (champagne != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(champagne.getName()).append(" 1개");
            System.out.println(sb);
            return;
        }
        System.out.println("없음");
    }
}
