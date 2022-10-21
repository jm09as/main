package com.schedule_management.main.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class WorkSheet {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @NotNull
    private long id;
    @Size(max = 40)
    private String sheetName;
    private Date workDuration;
    private Date startingWork;
    private int materialCost;
    @OneToMany
    @ToString.Exclude
    private List<Employee> employees;
}
