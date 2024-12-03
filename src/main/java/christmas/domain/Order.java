package christmas.domain;

import christmas.domain.constants.Menu;
import christmas.exception.CustomException;
import christmas.exception.ErrorMessage;
import java.util.List;
import java.util.Objects;

public class Order {
    private final String name;
    private final int quantity;

    public Order(String input) {
        this(List.of(input.split("-", -1)));
    }

    private Order(List<String> input) {
        String name = validateName(input.get(0));
        int quantity = validateQuantity(input.get(1));
        this.name = name;
        this.quantity = quantity;
    }

    private String validateName(final String name) {
        if (Menu.has(name)) {
            return name;
        }
        throw CustomException.from(ErrorMessage.INVALID_ORDER);
    }

    private int validateQuantity(final String input) {
        try {
            int quantity = Integer.parseInt(input);
            if (quantity < 1) {
                throw CustomException.from(ErrorMessage.INVALID_ORDER);
            }
            return quantity;
        } catch (NumberFormatException e) {
            throw CustomException.from(ErrorMessage.INVALID_ORDER);
        }
    }

    public int getPrice() {
        return Menu.getPriceBy(name) * quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Order))
            return false;
        Order order = (Order)o;
        return Objects.equals(name, order.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(" ").append(quantity).append("개");
        return sb.toString();
    }
}
