package ibf2022.ssf.day13.repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.io.PrintWriter;
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
    final String fileName = "employee.txt";
    final String dirPath = System.getProperty("user.dir") + File.separator + "storage";
    private List<Employee> employees;

    public EmployeeRepo() throws ParseException, FileNotFoundException {
        // File f = new File(dirPath + File.separator + fileName);
        // FileReader fr = new FileReader(f);
        // BufferedReader br = new BufferedReader(fr);
        if (employees == null) {
            employees = new ArrayList<Employee>();
        }
        DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        Date birthday = df.parse("01-10-1980");

        Employee emp = new Employee("Darth", "Vader", "deathstar@gmail.com", "90096006", 100000, birthday,
                "Tatooine", 665665);
        employees.add(emp);

        emp = new Employee("Harry", "Potter", "harrypotter@hogwarts.com", "60069009", 150000, birthday,
                "Privet Drive", 643256);
        employees.add(emp);

    }

    public List<Employee> findAll() {
        return employees;
    }

    public Boolean save(Employee employee) throws FileNotFoundException {
        Boolean result = employees.add(employee);
        File f = new File(dirPath + File.separator + fileName);
        OutputStream os = new FileOutputStream(f, true);
        PrintWriter pw = new PrintWriter(os);
        pw.println(employee.toString());
        pw.flush();
        pw.close();
        return result;
    }

    public Boolean delete(Employee employee) {
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

    public Employee findByEmail(String email) {
        Employee emp = employees.stream().filter(e -> e.getEmail().equalsIgnoreCase(email)).findFirst().get();
        return emp;
    }

    public Boolean updateEmployee(Employee employee) {
        Employee emp = employees.stream().filter(e -> e.getEmail().equalsIgnoreCase(employee.getEmail())).findFirst()
                .get();

        int idx = 0;
        if (idx >= 0) {
            idx = employees.indexOf(emp);
            employees.remove(idx);

            // employees.get(idx).setAddress(employee.getAddress());
            // employees.get(idx).setFirstName(employee.getFirstName());
            // employees.get(idx).setLastName(employee.getLastName());
            // employees.get(idx).setSalary(employee.getSalary());
            // employees.get(idx).setPhoneNumber(employee.getPhoneNumber());
            // employees.get(idx).setPostalCode(employee.getPostalCode());
            // employees.get(idx).setBirthDay(employee.getBirthDay());
        }
        employees.add(employee);
        return true;
    }
}
