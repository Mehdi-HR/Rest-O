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

    @PostMapping("/add")
    public void addMenuItem(@RequestBody MenuItemDTO newItem) {
        log.info("Adding new item to the menu.");
        menuItemService.add(newItem);
    }

    @PutMapping("/{id}/update")
    public void update(@PathVariable long id, @RequestBody MenuItemDTO newItem) {
        log.info("Updating menu item {}.", id);
        menuItemService.update(id, newItem);
    }

    /*
    @PatchMapping("/{id}/add-ingredient")
    public void increase(@PathVariable long id, @RequestParam String ref, @RequestParam int quantity) {
        menuItemService.addIngredient(id, ref, quantity);
    }*/

}
