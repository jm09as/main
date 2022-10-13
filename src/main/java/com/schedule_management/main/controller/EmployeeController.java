package com.schedule_management.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeController {

    @GetMapping("/")
    public String createEmployeeForm() {
        return "create-employee";
    }
}
