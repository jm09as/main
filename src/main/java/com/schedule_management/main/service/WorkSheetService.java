package com.schedule_management.main.service;

import com.schedule_management.main.model.WorkSheet;
import com.schedule_management.main.repository.WorkSheetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WorkSheetService {

    private WorkSheetRepository workSheetRepository;

    @Autowired
    public void setWorkSheetRepository(WorkSheetRepository workSheetRepository) {
        this.workSheetRepository = workSheetRepository;
    }

    public void addWorkSheet(WorkSheet workSheet) {
        workSheetRepository.save(workSheet);
        log.info("%s".formatted(workSheet));
    }
}
