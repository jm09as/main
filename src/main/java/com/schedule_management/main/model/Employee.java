package com.schedule_management.main.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Employee {

    @Id
    @GeneratedValue
    @NotNull
    private long id;
    @Size(max = 100)
    private String name;
    @Size(max = 250)
    private String address;
    private String position;
    private Date birthDay;
    private int hourlyWage;

}
