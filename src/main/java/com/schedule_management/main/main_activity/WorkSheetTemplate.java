package com.schedule_management.main.main_activity;

import com.schedule_management.main.model.WorkSheet;

import java.util.Date;

public class WorkSheetTemplate {

    private WorkSheet workSheet;

    public WorkSheetTemplate(Date startingDate, int workDuration) {
        workSheet.setStartingWork(startingDate);
        workSheet.setWorkDuration(Integer.toString(workDuration));
        workSheet.setEndWork(CalculateEndSheet.getInstance().calculatePeriod(startingDate, workDuration));
    }

    public WorkSheet getWorkSheet() {
        return workSheet;
    }
}
