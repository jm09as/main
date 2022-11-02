package com.schedule_management.main.controller;

import com.schedule_management.main.model.Employee;
import com.schedule_management.main.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
@RequestMapping("/create")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String createEmployeeForm(Employee employee) {
        return "create-employee";
    }

    @PostMapping
    public String createEmployees(@Valid Employee employee, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "create-employee";
        }
        employeeService.addEmployee(employee);
        return "redirect:/create/employee";
    }

    @GetMapping("/update/{employee}")
    public String updateEmployee(@PathVariable Employee employee, Model model) {
        model.addAttribute("employee", employeeService.findEmployeeById(employee));
        return "create-employee";
    }

    @PostMapping("/update/{id}")
    public String editEmployees(@Valid Employee employee, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "create-employee";
        }
        employeeService.update(employee);
        return "redirect:/create/employee";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Employee id) {
        employeeService.deleteEmployeeById(id);
        return "redirect:/create/employee";
    }

    @GetMapping("/employee")
    public String listEmployees(Model model) {
        model.addAttribute("employees", employeeService.getEmployees());
        return "list-employee";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }

}
