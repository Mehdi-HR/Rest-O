package orders.order;

import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@Builder
@Document
public class Order {

    @Transient
    public static final String SEQUENCE_NAME = "orders_sequence";

    @Id
    private long id;
    private Map<Long, Integer> items;
    private double deliveryFeeDH;
}
