package orders.order;

import java.util.Map;

public record OrderDTO(
        Map<Long, Integer> items,
        double deliveryFeeDH
) {
}
