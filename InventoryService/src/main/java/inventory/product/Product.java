package inventory.product;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
public class Product {

    @Id
    private String ref;
    private String label;
    private double unitCostDH;
    private int nbOfUnits;


}
