package com.schedule_management.main.main_activity;

import com.schedule_management.main.model.Employee;
import com.schedule_management.main.model.WorkSheet;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SearchAvailableEmployee {

    private final List<WorkSheet> workSheets;

    public SearchAvailableEmployee(List<WorkSheet> workSheets) {
        this.workSheets = workSheets;
    }

    public List<Employee> searchWorker() {
        List<List<Employee>> lists = workSheets.stream().map(WorkSheet::getEmployees).toList();
        var set = new HashSet<Employee>();
        for (List<Employee> list : lists) {
            set.addAll(list);
        }
        return new ArrayList<>(set);
    }

    public List<Employee> searchWorker(List<WorkSheet> workSheet) {
        List<List<Employee>> lists = workSheet.stream().map(WorkSheet::getEmployees).toList();
        var set = new HashSet<Employee>();
        for (List<Employee> list : lists) {
            set.addAll(list);
        }
        return new ArrayList<>(set);
    }

    public List<WorkSheet> getEmployeeOnGivenDay(LocalDateTime date) {
        return workSheets.stream().filter(w -> w.getStartingWork().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().isBefore(date.plusDays(1L)) && //
                        w.getEndWork().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().isAfter(date.minusDays(1L))) //
                .toList();//
    }


}
