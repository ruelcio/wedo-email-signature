package ao.grupowedo.emailssignature;

import ao.grupowedo.emailssignature.model.Employee;

public class TestEmploye {
    public static void main(String[] args) {
        Employee    employee = new Employee();

        employee.setName("João Manuel");
        System.out.println(employee.getName());

        Employee    employee2 = new Employee(
            "Ruélcio Muliata",
            "Técnico de IT",
            "joao@grupowedo.ao",
            "+244 9XX XXX XXX"
        );
        System.out.println(employee2.getName());
    }
}
