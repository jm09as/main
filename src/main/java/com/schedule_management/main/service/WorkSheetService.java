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
        List<WorkSheet> all = workSheetRepository.findAll();
        var sae = new SearchAvailableEmployee(all);
        return sae.searchWorker(sae.getEmployeeOnGivenDay(localDateTime));
    }

}
