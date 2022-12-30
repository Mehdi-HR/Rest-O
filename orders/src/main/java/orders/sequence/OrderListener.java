package orders.sequence;

import lombok.AllArgsConstructor;
import orders.order.Order;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderListener extends AbstractMongoEventListener<Order> {

    private final SequenceGeneratorService sequenceGenerator;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Order> event) {
        if (event.getSource().getId() < 1) {
            event.getSource().setId(sequenceGenerator.generateSequence(Order.SEQUENCE_NAME));
        }
    }
}
