package christmas.domain.constants;

import christmas.domain.Order;
import christmas.exception.CustomException;
import christmas.exception.ErrorMessage;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.mockito.internal.matchers.Or;

public class Orders {
    private List<Order> orders;
    public Orders(final String input) {
        List<Order> orders = Arrays.stream(input.split(",", -1)).map(Order::new).toList();
        validateNoDuplicates(orders);
        this.orders = orders;
    }

    public int getWholePrices() {
        return orders.stream().mapToInt(Order::getPrice).sum();
    }

    private void validateNoDuplicates(List<Order> orders) {
        Set<Order> orderSet = new HashSet<>(orders);
        if (orderSet.size() != orders.size()) {
            throw CustomException.from(ErrorMessage.INVALID_ORDER);
        }
    }

    @Override
    public String toString() {
        return orders.stream().map(Order::toString).collect(Collectors.joining("\n"));
    }
}
