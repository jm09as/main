package com.schedule_management.main.controller;

import com.schedule_management.main.model.Employee;
import com.schedule_management.main.model.WorkSheet;
import com.schedule_management.main.service.EmployeeService;
import com.schedule_management.main.service.WorkSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/create")
public class EmployeeController {

    private EmployeeService employeeService;
    private WorkSheetService workSheetService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public void setWorkSheetService(WorkSheetService workSheetService) {
        this.workSheetService = workSheetService;
    }

    @GetMapping
    public String createEmployeeForm(Employee employee) {
        // model.addAttribute("employee", new Employee());
        return "create-employee";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Employee id) {
        employeeService.deleteEmployeeById(id);
        return "redirect:/create/employee";
    }

    @GetMapping("/update/{employee}")
    public String updateEmployee(@PathVariable Employee employee, Model model) {
        Employee employee1 = employeeService.findEmployeeById(employee);
        model.addAttribute("employee", employee1);
        return "create-employee";
    }

    @PostMapping("/update/{id}")
    public String editEmployees(Employee employee) {
        employeeService.update(employee);
        return "redirect:/create/employee";
    }

    @PostMapping
    public String createEmployees(Employee employee) {
        employeeService.addEmployee(employee);
        return "redirect:/create/employee";
    }

    @GetMapping("/employee")
    public String listEmployees(Model model) {
        model.addAttribute("employees", employeeService.getEmployees());
        return "list-employee";
    }

    @GetMapping("/work")
    public String createWork(Model model) {
        List<String> employeeNames = employeeService.getEmployees().stream().map(e -> e.getName()).toList();
        System.out.println("employee" + employeeNames);
        model.addAttribute("empl", employeeNames);
        return "create_worksheet";
    }

    @PostMapping("/work")
    public String addWorkSheet(WorkSheet workSheet, Model model) {
        Object cat = model.getAttribute("cat");
        System.out.println(cat);
        WorkSheet ws = (WorkSheet) model.getAttribute("workSheet");
        System.out.println(ws);
        workSheetService.addWorkSheet(workSheet);
        return "list-employee";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }


}
