package menu.item;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/menu")
public record MenuItemController(MenuItemService menuItemService) {

    @GetMapping
    public List<MenuItem> list() {
        log.info("Listing menu items.");
        return menuItemService.list();
    }

    @GetMapping("{id}/show")
    public MenuItem show(@PathVariable Long id) {
        log.info("Showing menu item {}.", id);
        return menuItemService.show(id);
    }

    @GetMapping("{id}/cost")
    public double getCost(@PathVariable Long id) {
        log.info("Calculating menu item's cost {}.", id);
        return menuItemService.getCost(id);
    }

    @GetMapping("{id}/price")
    public double getPrice(@PathVariable Long id) {
        log.info("Showing menu item's price {}.", id);
        return menuItemService.getPrice(id);
    }

    @PostMapping("/add")
    public void addMenuItem(@RequestBody MenuItemDTO newItem) {
        log.info("Adding new item to the menu.");
        menuItemService.add(newItem);
    }

    @PutMapping("/{id}/update")
    public void update(@PathVariable Long id, @RequestBody MenuItemDTO newItem) {
        log.info("Updating menu item {}.", id);
        menuItemService.update(id, newItem);
    }

}
