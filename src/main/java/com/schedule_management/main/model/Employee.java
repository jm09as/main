package com.schedule_management.main.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private long id;
    @Size(max = 100)
    private String name;
    @Size(max = 250)
    private String address;
    @Size(max = 20)
    private String profession;
    private Date birthDay;
    private int hourlyWage;

}
