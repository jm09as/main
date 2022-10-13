package com.schedule_management.main.model;

import lombok.Data;

import java.util.Date;

@Data
public class Employee {

    private int id;
    private String name;
    private String address;
    private String position;
    private Date birthDay;
    private int hourlyWage;
}
