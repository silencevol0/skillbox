package app;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Main {

    private static final String STAFF_TXT = "data/staff.txt";

    public static void main(String[] args) {
        List<Employee> staff = EmployeeUtils.loadStaffFromFile(STAFF_TXT);

        Employee employeeMaxSalary = findEmployeeWithHighestSalary(staff, 2017);
        System.out.println(employeeMaxSalary);
    }

    public static Employee findEmployeeWithHighestSalary(List<Employee> staff, int year) {
        Optional optional = staff.stream().filter(e -> e.getWorkStart().isAfter(LocalDate.of(year , 1,1)))
                .filter(e -> e.getWorkStart().isBefore(LocalDate.of(year,12,31)))
                .max(Comparator.comparing(Employee::getSalary));
        return (Employee) optional.get();
    }
}