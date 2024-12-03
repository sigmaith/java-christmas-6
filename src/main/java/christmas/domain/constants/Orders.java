package christmas.domain.constants;

import christmas.domain.Order;
import christmas.exception.CustomException;
import christmas.exception.ErrorMessage;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.mockito.internal.matchers.Or;

public class Orders {
    private List<Order> orders;
    public Orders(final String input) {
        List<Order> orders = Arrays.stream(input.split(",", -1)).map(Order::new).toList();
        validateNoDuplicates(orders);
        this.orders = orders;
    }

    private void validateNoDuplicates(List<Order> orders) {
        Set<Order> orderSet = new HashSet<>(orders);
        if (orderSet.size() != orders.size()) {
            throw CustomException.from(ErrorMessage.INVALID_ORDER);
        }
    }
}
