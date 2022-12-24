package menu.item;

import java.util.Map;

public record MenuItemDTO(
        String label,
        Map<String, Integer>ingredients,
        double priceDH
) {
}
