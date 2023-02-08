package ibf2022.ssf.day13.repository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import ibf2022.ssf.day13.model.Employee;

@Repository
public class EmployeeRepo {
    private List<Employee> employees;

    public EmployeeRepo() throws ParseException {
        if (employees == null) {
            employees = new ArrayList<Employee>();
        }
        DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        Date birthday = df.parse("01-10-1980");

        Employee firstEMP = new Employee("Darth", "Vader", "deathstar@gmail.com", "90096006", 100000, birthday,
                "Tatooine", 665665);
        employees.add(firstEMP);

        Employee secondEMP = new Employee("Harry", "Potter", "harrypotter@hogwarts.com", "60069009", 150000, birthday,
                "Privet Drive", 643256);
        employees.add(secondEMP);

    }

    public List<Employee> findAll() {
        return employees;
    }

    public Boolean saved(Employee employee) {
        Boolean result = employees.add(employee);
        return result;
    }

    public Boolean deleted(Employee employee) {
        // Employee e = employees.stream().filter(emp ->
        // emp.getEmail().equalsIgnoreCase(employee.getEmail())).findFirst()
        // .get();

        Boolean result = false;

        int employeeIndex = employees.indexOf(employee);
        if (employeeIndex >= 0) {
            employees.remove(employeeIndex);
            result = true;
        }
        return result;

    }
}
