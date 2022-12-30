package orders.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/orders")
public record OrderController(OrderService orderService) {

    @GetMapping
    public List<Order> list() {
        log.info("Listing orders.");
        return orderService.list();
    }

    @GetMapping("{id}/show")
    public Order show(@PathVariable Long id) {
        log.info("Showing order {}.", id);
        return orderService.show(id);
    }

    @GetMapping("{id}/price")
    public double getPrice(@PathVariable Long id) {
        log.info("Calculating order's price {}.", id);
        return orderService.getPrice(id);
    }

    @PostMapping("/add")
    public void addOrder(@RequestBody OrderDTO newOrder) {
        log.info("Adding new order.");
        orderService.add(newOrder);
    }

    @PutMapping("/{id}/update")
    public void update(@PathVariable Long id, @RequestBody OrderDTO newOrder) {
        log.info("Updating order {}.", id);
        orderService.update(id, newOrder);
    }

    @DeleteMapping("{id}/cancel")
    public void cancel(@PathVariable Long id) {
        log.info("Cancelling order {}.", id);
        orderService.cancel(id);
    }
}
