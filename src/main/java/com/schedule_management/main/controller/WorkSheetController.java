package com.schedule_management.main.controller;

import com.schedule_management.main.model.WorkSheet;
import com.schedule_management.main.service.EmployeeService;
import com.schedule_management.main.service.WorkSheetService;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Controller
@RequestMapping("/")
public class WorkSheetController {
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
    public String createWorkSheet(@Valid WorkSheet workSheet, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "create_worksheet";
        }
        System.out.println(workSheet.getWorkDuration());
        System.out.println(workSheet.getStartingWork());
        var employees = employeeService.getEmployees();
        employees.removeAll(workSheetService.getEmployeesFromPeriod(workSheet));
        model.addAttribute("employ", employees);
        return "create_worksheet";

    }

    @PostMapping("/create/work")
    public String addWorkSheet(@Valid WorkSheet workSheet, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/create/work";
        }
        workSheetService.addWorkSheet(workSheet);
        // TODO make up
        return "create_worksheet";
    }

    @GetMapping("/work/update/{worksheets}")
    public String workSheetUpdate(@PathVariable WorkSheet worksheets, Model model) {
        model.addAttribute("workSheet", workSheetService.getWorkSheetById(worksheets));
        System.out.println(worksheets.getId());
        WorkSheet byId = workSheetService.findById(worksheets);
        byId.setActive(false);
        workSheetService.addWorkSheet(byId);
        var employees = employeeService.getEmployees();
        model.addAttribute("employ", employees);
        return "create_worksheet";
    }

    @GetMapping("/list")
    public String listWorkSheet(Model model) {
        model.addAttribute("worksheets", workSheetService.getWorkSheetsWhereActive());
        return "list-worksheets";
    }

    @GetMapping("/calculate")
    public String getCalculate() {
        return "calculate";
    }

    @PostMapping("/update")
    public String updateWorksheet(WorkSheet workSheet) {
        workSheetService.updateWorksheet(workSheet);
        return "redirect:/list";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }
}
