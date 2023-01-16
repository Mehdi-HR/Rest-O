package human.resources;

import human.resources.employee.EmployeeRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = EmployeeRepository.class)
@EnableEurekaClient
public class HumanResourcesApplication {
    public static void main(String[] args) {
        SpringApplication.run(HumanResourcesApplication.class, args);
    }

}
