package com.schedule_management.main.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
    private int id;
    @Size(max = 40)
    private String sheetName;
    @Pattern(regexp = "[0-9]+")
    private String workDuration;
    @NotNull(message = "... meg kell adnod a munka kezdő napját!")
    private Date startingWork;
    private Date endWork;
    private int distanceFromYard;
    private int materialCost;
    @ManyToMany
    private List<Employee> employees;
}
