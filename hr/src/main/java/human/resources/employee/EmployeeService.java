package human.resources.employee;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record EmployeeService(EmployeeRepository employeeRepository) {
    public void add(EmployeeDTO employeeDTO) {
        var employee = Employee.builder()
                .id(employeeDTO.id())
                .firstName(employeeDTO.firstName())
                .lastName(employeeDTO.lastName())
                .role(employeeDTO.role())
                .salaryDH(employeeDTO.salaryDH()).build();
        employeeRepository.insert(employee);
    }

    public List<Employee> list() {
        return employeeRepository.findAll();
    }

    public Employee show(String id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee update(String id, EmployeeDTO employeeDTO) {
        var optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            var employee = optionalEmployee.get();
            var updated = Employee.builder()
                    .id(employee.getId())
                    .firstName(employeeDTO.firstName())
                    .lastName(employeeDTO.lastName())
                    .role(employeeDTO.role())
                    .salaryDH(employeeDTO.salaryDH()).build();
            return employeeRepository.save(updated);
        }
        return null;
    }

    public void delete(String id) {
        employeeRepository.deleteById(id);
    }
}