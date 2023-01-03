package menu.item;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record MenuItemService(MenuItemRepository menuItemRepository,ProductUnitCostProxy unitCostProxy ) {
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

    public double getCost(Long id) {
        var optionalMenuItem = menuItemRepository.findById(id);
        if (optionalMenuItem.isPresent()) {
            var item = optionalMenuItem.get();
            var totalCost = 0.0;
            for (var entry:item.getIngredients().entrySet()) {
                var ref = entry.getKey();
                var quantity = entry.getValue();
                Double unitCost = unitCostProxy.getUnitCost(ref);
                totalCost += unitCost * quantity;
            }
            return totalCost;
        } else return 0;
    }

    public double getPrice(Long id) {
        var optional = menuItemRepository.findById(id);
        return optional.map(MenuItem::getPriceDH).orElse(0.0);
    }
}
