package menu.item;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record ProductUnitCostProxy(RestTemplate restTemplate) {

    //@CircuitBreaker(name = "CircuitBreakerService", fallbackMethod = "defaultUnitCost")
    @Retry(name = "retryApi", fallbackMethod = "defaultUnitCost")
    public Double getUnitCost(String ref) {
        return restTemplate.getForObject("http://INVENTORY/api/v1/inventory/{ref}/cost",Double.class, ref);
    }

    private double defaultUnitCost(Long id) {
        return -Integer.MAX_VALUE;
    }

}
