package ibf2022.ssf.day13.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ibf2022.ssf.day13.model.Employee;
import ibf2022.ssf.day13.repository.EmployeeRepo;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeRepo empRepo;

    @GetMapping("/home")
    public String employeeListPage(Model model) {
        List<Employee> employees = empRepo.findAll();
        model.addAttribute("employeeList", employees);
        return "employee-list";
    }

    @GetMapping("/add-new")
    public String addPage(Model model) {
        Employee emp = new Employee();
        model.addAttribute("employee", emp);
        return "employee-add";
    }

    @PostMapping("/add-new")
    public String addEmployee(@Valid @ModelAttribute("employee") Employee employeeForm, BindingResult bResult,
            Model model) {

        if (bResult.hasErrors()) {
            return "employee-add";
        }

        Boolean result = false;
        result = empRepo.save(employeeForm);
        return "redirect:/employees/home";
    }

    @GetMapping("/delete-employee/{email}")
    public String delete(@PathVariable("email") String email, Model model) {
        Employee emp = empRepo.findByEmail(email);
        Boolean result = empRepo.delete(emp);
        return "redirect:/employees/home";
    }

}
