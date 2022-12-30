package menu.item;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public record MenuItemService(MenuItemRepository menuItemRepository, RestTemplate restTemplate) {
    public List<MenuItem> list() {
        return menuItemRepository.findAll();
    }

    public MenuItem show(Long id) {
        return menuItemRepository.findById(id).orElse(null);
    }

    public void add(MenuItemDTO newItem) {
        var menuItem = MenuItem.builder()
                        .label(newItem.label())
                        .ingredients(newItem.ingredients())
                        .priceDH(newItem.priceDH()).build();

        menuItemRepository.insert(menuItem);
    }


    public void update(long id, MenuItemDTO newItem) {
        var oldOptional = menuItemRepository.findById(id);
        if (oldOptional.isPresent()) {
            var updated = MenuItem.builder()
                    .id(id)
                    .label(newItem.label())
                    .ingredients(newItem.ingredients())
                    .priceDH(newItem.priceDH()).build();
            menuItemRepository.save(updated);
        }
    }

    @CircuitBreaker(name = "CircuitBreakerService", fallbackMethod = "defaultCost")
    public double getCost(Long id) {
        var optionalMenuItem = menuItemRepository.findById(id);
        if (optionalMenuItem.isPresent()) {
            var item = optionalMenuItem.get();
            var totalCost = 0.0;
            for (var entry:item.getIngredients().entrySet()) {
                var ref = entry.getKey();
                var quantity = entry.getValue();
                var unitCost = restTemplate.getForObject("http://INVENTORY/api/v1/inventory/{ref}/cost",Double.class, ref);
                totalCost += unitCost * quantity;
            }
            return totalCost;
        } else return 0;
    }

    public double defaultCost(Long id) {
        return -1;
    }
}
