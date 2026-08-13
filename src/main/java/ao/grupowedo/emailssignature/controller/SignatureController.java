package ao.grupowedo.emailssignature.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ao.grupowedo.emailssignature.model.Employee;
import ao.grupowedo.emailssignature.service.GoogleSheetsService;

@Controller
public class SignatureController {
    private final GoogleSheetsService   googleSheetsService;

    public SignatureController(GoogleSheetsService googleSheetsService) {
        this.googleSheetsService = googleSheetsService;
    }

    @GetMapping("/signature")
    public String   signature(Model model) throws Exception{
        List<Employee>  employees = googleSheetsService.readEmployees();
        Employee        employee = employees.get(0);

        model.addAttribute("employee", employee);
        return "signature";
    }
}
