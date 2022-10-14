package com.schedule_management.main.controller;

import com.schedule_management.main.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/create")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String createEmployeeForm() {
        return "create-employee";
    }

    //    @PostMapping
//    public String createEmployees(Employee e) {
//        employeeService.addEmployee(e);
//        return "redirect:/create";
//    }
//
    @GetMapping("/employee")
    public String listEmployees(Model model) {
        model.addAttribute("employee", employeeService.getEmployees());
        return "list-employee";
    }


}
