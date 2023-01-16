package human.resources.employee;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
public class Employee {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String role;
    private double salaryDH;
}
