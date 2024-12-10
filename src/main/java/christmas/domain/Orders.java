package christmas.domain;

import christmas.domain.constants.Menu;
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
        validateMax(orders);
        validateAllBeverage(orders);
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

    private void validateMax(List<Order> orders) {
        int sum = orders.stream().mapToInt(Order::getQuantity).sum();
        if (sum > 20) {
            throw CustomException.from(ErrorMessage.INVALID_ORDER);
        }
    }

    private void validateAllBeverage(List<Order> orders) {
        if (orders.stream().map(Order::getName).allMatch(Menu::isBeverageType)) {
            throw CustomException.from(ErrorMessage.INVALID_ORDER);
        }
    }

    public int getDCPriceOfMainMenu() {
        List<Order> mainTypeOrders = orders.stream().filter(order -> Menu.getMenuBy(order.getName()).isMainType()).toList();
        int dcPriceOfMainTypes = mainTypeOrders.stream().mapToInt(order -> order.getQuantity() * 2_023).sum();
        return dcPriceOfMainTypes;
    }

    public int getDCPriceOfDessertMenu() {
        List<Order> dessertTypeOrders = orders.stream().filter(order -> Menu.getMenuBy(order.getName()).isDessertType()).toList();
        int dcPriceOfDessertTypes = dessertTypeOrders.stream().mapToInt(order -> order.getQuantity() * 2_023).sum();
        return dcPriceOfDessertTypes;
    }

    @Override
    public String toString() {
        return orders.stream().map(Order::toString).collect(Collectors.joining("\n"));
    }
}
