package menu.item;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record MenuItemService(MenuItemRepository menuItemRepository) {
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
            var old = oldOptional.get();
            var updated = MenuItem.builder()
                    .id(id)
                    .label(newItem.label())
                    .ingredients(newItem.ingredients())
                    .priceDH(newItem.priceDH()).build();
            menuItemRepository.save(updated);
        }

    }
}
