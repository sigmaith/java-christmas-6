package christmas;

import christmas.controller.ApplyController;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        ApplyController applyController = new ApplyController(new InputView(), new OutputView());
        applyController.run();
    }
}
