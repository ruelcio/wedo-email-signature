package ao.grupowedo.emailssignature.service;

import org.springframework.stereotype.Service;

import ao.grupowedo.emailssignature.model.Employee;

@Service
public class SignatureService {
    public Employee getEmployee() {
        return new Employee(
            "João Manuel",
            "Técnico de IT",
            "+244 923 000 000",
            "joao@grupowedo.ao"
        );
    }
}
