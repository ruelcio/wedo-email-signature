package ao.grupowedo.emailssignature.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ao.grupowedo.emailssignature.model.Employee;
import ao.grupowedo.emailssignature.service.EmployeeService;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    private final EmployeeService   employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee>   getAllEmployees() throws Exception{
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee         getEmployeeById(@PathVariable Long id) throws Exception {
        return employeeService.getEmployeeById(id);
    }
}
