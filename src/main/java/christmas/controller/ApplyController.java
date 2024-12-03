package christmas.controller;

import christmas.domain.Date;
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
    }

    public Date getDateToVisit() {
        return inputView.getDateToVisit();
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
