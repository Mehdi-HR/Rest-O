package menu.sequence;

import lombok.AllArgsConstructor;
import menu.item.MenuItem;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MenuItemListener extends AbstractMongoEventListener<MenuItem> {

    private final SequenceGeneratorService sequenceGenerator;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<MenuItem> event) {
        if (event.getSource().getId() < 1) {
            event.getSource().setId(sequenceGenerator.generateSequence(MenuItem.SEQUENCE_NAME));
        }
    }
}
