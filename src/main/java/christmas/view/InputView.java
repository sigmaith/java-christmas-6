package christmas.view;

import static camp.nextstep.edu.missionutils.Console.readLine;

import christmas.domain.Date;
import christmas.domain.constants.Orders;
import christmas.exception.CustomException;
import christmas.exception.ErrorMessage;

public class InputView {
    public Date getDateToVisit() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        try {
            return new Date(readLine());
        } catch (NumberFormatException e) {
            throw CustomException.from(ErrorMessage.INVALID_DATE);
        }
    }

    public Orders getOrders() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        return new Orders(readLine());
    }
}
