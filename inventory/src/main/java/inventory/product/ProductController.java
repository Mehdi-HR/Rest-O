package inventory.product;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/inventory")
public record ProductController(ProductService productService) {

    @GetMapping
    public List<Product> list() {
        log.info("Listing inventory products");
        return productService.list();
    }

    @GetMapping("{ref}/show")
    public Product show(@PathVariable String ref) {
        log.info("Showing inventory product : {}.", ref);
        return productService.show(ref);
    }

    @GetMapping("{ref}/cost")
    public double getPrice(@PathVariable String ref) {
        log.info("Showing inventory product's price : {}.", ref);
        return productService.getUnitCost(ref);
    }

    @PostMapping("/add")
    public void addProduct(@RequestBody ProductDTO productDTO) {
        log.info("Adding product to inventory");
        productService.add(productDTO);
    }

    @PatchMapping("{ref}/increase")
    public void increase(@PathVariable String ref, @RequestParam int nbUnits) {
        log.info("Increasing inventory product : {} by {} units.", ref, nbUnits);
        productService.increase(ref, nbUnits);
    }

    @PatchMapping("{ref}/decrease")
    public void decrease(@PathVariable String ref, @RequestParam int nbUnits) {
        log.info("Decreasing inventory product : {} by {} units.", ref, nbUnits);
        productService.decrease(ref, nbUnits);
    }

}
