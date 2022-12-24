package inventory.product;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record ProductService(ProductRepository productRepository) {
    public void add(ProductDTO productDTO) {
        var product = Product.builder()
                .ref(productDTO.ref())
                .label(productDTO.label())
                .unitCostDH(productDTO.unitCostDH())
                .nbOfUnits(0).build();
        productRepository.insert(product);
    }

    public void increase(String ref, int nbUnits) {
        var productOptional = productRepository.findById(ref);
        if (productOptional.isPresent() && nbUnits > 0) {
            var product = productOptional.get();
            product.setNbOfUnits(product.getNbOfUnits() + nbUnits);
            productRepository.save(product);
        }
    }

    public void decrease(String ref, int nbUnits) {
        var productOptional = productRepository.findById(ref);
        if (productOptional.isPresent() && nbUnits > productOptional.get().getNbOfUnits()) {
            var product = productOptional.get();
            product.setNbOfUnits(product.getNbOfUnits() - nbUnits);
            productRepository.save(product);
        }
    }


    public List<Product> list() {
        return productRepository.findAll();
    }

    public Product show(String ref) {
        return productRepository.findById(ref).orElse(null);
    }

    public double getUnitCost(String ref) {
        var productOptional = productRepository.findById(ref);
        if (productOptional.isPresent()) {
            var product = productOptional.get();
            return product.getUnitCostDH();
        }
        else return 0;
    }
}
