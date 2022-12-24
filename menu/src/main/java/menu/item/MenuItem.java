package menu.item;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@Builder
@Document
public class MenuItem {


    @Transient
    public static final String SEQUENCE_NAME = "items_sequence";

    @Id
    private long id;
    private String label;
    private Map<String, Integer> ingredients;
    private double priceDH;
}
