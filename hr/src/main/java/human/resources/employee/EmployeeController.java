package human.resources.employee;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/employees")
public record EmployeeController(EmployeeService employeeService) {

    @GetMapping
    public List<Employee> list() {
        log.info("Listing employees");
        return employeeService.list();
    }

    @GetMapping("{id}/show")
    public Employee show(@PathVariable String id) {
        log.info("Showing employee : {}.", id);
        return employeeService.show(id);
    }

    @PostMapping("/add")
    public void addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        log.info("Adding employee");
        employeeService.add(employeeDTO);
    }

    @PutMapping("{id}/update")
    public Employee update(@PathVariable String id, @RequestBody EmployeeDTO employeeDTO) {
        log.info("Updating employee : {}.", id);
        return employeeService.update(id, employeeDTO);
    }

    @DeleteMapping("{id}/delete")
    public void delete(@PathVariable String id) {
        log.info("Deleting employee : {}.", id);
        employeeService.delete(id);
    }


}
