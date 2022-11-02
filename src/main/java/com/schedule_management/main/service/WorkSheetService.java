package com.schedule_management.main.service;

import com.schedule_management.main.main_activity.CalculateEndSheet;
import com.schedule_management.main.main_activity.SearchAvailableEmployee;
import com.schedule_management.main.model.Employee;
import com.schedule_management.main.model.WorkSheet;
import com.schedule_management.main.repository.WorkSheetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class WorkSheetService {

    private WorkSheetRepository workSheetRepository;

    @Autowired
    public void setWorkSheetRepository(WorkSheetRepository workSheetRepository) {
        this.workSheetRepository = workSheetRepository;
    }

    public void addWorkSheet(WorkSheet workSheet) {
        workSheet.setEndWork(CalculateEndSheet.getInstance().calculateEnd(workSheet));
        workSheetRepository.save(workSheet);
        log.info("%s".formatted(workSheet));
    }

    public List<Employee> getInWorkEmployees(LocalDateTime localDateTime) {
        var sae = new SearchAvailableEmployee(workSheetRepository.findAllWhereActiveTrue());
        return sae.searchWorker(sae.getEmployeeOnGivenDay(localDateTime));
    }

    public List<Employee> getEmployeesFromPeriod(WorkSheet workSheet) {
        workSheet.setEndWork(CalculateEndSheet.getInstance().calculateEnd(workSheet));
        var sae = new SearchAvailableEmployee(workSheetRepository.findAllWhereActiveTrue());
        return sae.searchWorker(sae.getEmployeesOnGivenDays(workSheet));
    }

    public List<WorkSheet> getWorkSheets() {
        return workSheetRepository.findAll();
    }
    public List<WorkSheet> getWorkSheetsWhereActive() {
        return workSheetRepository.findAllWhereActiveTrue();
    }

    public List<Employee> getEmployeesOnGivenPeriod(Date date, int number) {
        var sae = new SearchAvailableEmployee(workSheetRepository.findAllWhereActiveTrue());
        List<WorkSheet> employeesOnGivenDays = sae.getEmployeesOnGivenDays(date, CalculateEndSheet.getInstance().calculatePeriod(date, number));
        return sae.searchWorker(employeesOnGivenDays);
        // TODO make up
    }

    public WorkSheet getWorkSheetById(WorkSheet workSheet) {
        return workSheetRepository.findById(workSheet.getId()).orElse(null);
    }

    public void updateWorksheet(WorkSheet workSheet) {
        workSheet.setEndWork(CalculateEndSheet.getInstance().calculateEnd(workSheet));
        System.out.println(workSheet.getSheetName());
        workSheetRepository.save(workSheet);
    }

    public WorkSheet findById(WorkSheet workSheet) {
       return workSheetRepository.findById(workSheet.getId()).orElse(null);
    }

}
