package orders.order;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
public record OrderService(OrderRepository orderRepository, RestTemplate restTemplate) {
    public List<Order> list() {
        return orderRepository.findAll();
    }

    public Order show(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public void add(OrderDTO newOrder) {
        var order = Order.builder()
                .items(newOrder.items())
                .deliveryFeeDH(newOrder.deliveryFeeDH()).build();

        orderRepository.insert(order);
    }


    public void update(long id, OrderDTO newOrder) {
        var oldOptional = orderRepository.findById(id);
        if (oldOptional.isPresent()) {
            var updated = Order.builder()
                    .id(id)
                    .items(newOrder.items())
                    .deliveryFeeDH(newOrder.deliveryFeeDH()).build();
            orderRepository.save(updated);
        }
    }

    @CircuitBreaker(name = "CircuitBreakerService", fallbackMethod = "defaultPrice")
    public double getPrice(Long id) {
        var optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            var order = optionalOrder.get();
            var totalPrice = 0.0;
            for (var entry:order.getItems().entrySet()) {
                var itemId = entry.getKey();
                var quantity = entry.getValue();
                var unitPrice = restTemplate.getForObject("http://MENU/api/v1/menu/{id}/price",Double.class, itemId);
                totalPrice += unitPrice * quantity;
            }
            return totalPrice + order.getDeliveryFeeDH();
        } else return 0;
    }

    public double defaultPrice(Long id) {
        return -1;
    }


    public void cancel(Long id) {
        orderRepository.deleteById(id);
    }
}
