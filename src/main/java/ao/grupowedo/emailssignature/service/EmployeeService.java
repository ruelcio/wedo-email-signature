package ao.grupowedo.emailssignature.service;

import java.util.List;
import org.springframework.stereotype.Service;

import ao.grupowedo.emailssignature.exception.EmployeeNotFoundExeption;
import ao.grupowedo.emailssignature.model.Employee;

@Service
public class EmployeeService {
    private final GoogleSheetsService   googleSheetsService;

    public EmployeeService(GoogleSheetsService googleSheetsService) {
        this.googleSheetsService = googleSheetsService;
    }

    public List<Employee>  getAllEmployees() throws Exception {
        return googleSheetsService.readEmployees();
    }

    public Employee getEmployeeById(Long id) throws Exception {
        List<Employee>  employees = googleSheetsService.readEmployees();

        for (Employee employee : employees) {
            if (id.equals(employee.getId())) {
                return employee;
            }
        }
        throw new EmployeeNotFoundExeption("Funcionário não encontrado.");
    }
}
