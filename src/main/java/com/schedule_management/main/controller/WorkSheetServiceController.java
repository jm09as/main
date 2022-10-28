package com.schedule_management.main.controller;

import com.schedule_management.main.model.Employee;
import com.schedule_management.main.model.WorkSheet;
import com.schedule_management.main.service.EmployeeService;
import com.schedule_management.main.service.WorkSheetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/")
public class WorkSheetServiceController {
    private WorkSheetService workSheetService;
    private EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public void setWorkSheetService(WorkSheetService workSheetService) {
        this.workSheetService = workSheetService;
    }

    @GetMapping("/create/work")
    public String createWork(WorkSheet workSheet, Model model) {
        model.addAttribute("employ", employeeService.getEmployees());
        return "create_worksheet";

    }

    @PostMapping("/create/work")
    public String addWorkSheet(WorkSheet workSheet, Model model) {
        workSheetService.addWorkSheet(workSheet);
        return "redirect:/create";
    }

    @GetMapping()
    public String employeeOnDate(Model model) {
        model.addAttribute("employee", new ArrayList<String>());
        System.out.println("most");
        return "ask-date";
    }

    @PostMapping()
    public String askOnDay(Model model, @RequestParam Date day) {
        LocalDateTime givenDay = day.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        List<Employee> inWorkEmployees = workSheetService.getInWorkEmployees(givenDay);
        List<Employee> OffEmployees = employeeService.getEmployees();
        OffEmployees.removeAll(inWorkEmployees);
        model.addAttribute("inWorkEmployee", inWorkEmployees.stream().map(Employee::getEmployeeName).toList());
        model.addAttribute("availableEmployee", OffEmployees.stream().map(Employee::getEmployeeName).toList());
        log.info(inWorkEmployees.toString());
        log.info(day.toString());
        return "ask-date";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }
}
