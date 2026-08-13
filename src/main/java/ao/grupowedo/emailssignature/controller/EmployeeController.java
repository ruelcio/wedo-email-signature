package ao.grupowedo.emailssignature.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ao.grupowedo.emailssignature.model.Employee;
import ao.grupowedo.emailssignature.service.GoogleSheetsService;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    private final GoogleSheetsService   googleSheetsService;

    public EmployeeController(GoogleSheetsService googleSheetsService) {
        this.googleSheetsService = googleSheetsService;
    }

    @GetMapping
    public List<Employee>   getEmployee() throws Exception{
        return googleSheetsService.readEmployees();
    }
}
