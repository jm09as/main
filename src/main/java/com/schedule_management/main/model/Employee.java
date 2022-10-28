package com.schedule_management.main.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
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
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private long id;
    @NotNull
    @Pattern(regexp = "\\p{L}+", message = "... számot nem lehet megadni!")
    @Size(min = 1, message = "... mező kitöltése kötelező!")
    private String employeeName;
    @Size(max = 250)
    private String address;
    @Size(max = 20)
    private String profession;
    @NotNull(message = "... a mező kitöltése kötelező!")
    private Date birthDay;
    private int hourlyWage;
    @Email(regexp = "[A-Za-z0-9]+@{1}\\p{L}+.{1}[a-z]{3}", message = "... rosszul kitöltött e-mail cím!")
    private String email;
    private String phoneNumber;
    @ManyToMany
    @JoinColumn(name = "work_sheet_id")
    private List<WorkSheet> workSheet;

}
