package com.schedule_management.main.main_activity;

import com.schedule_management.main.model.WorkSheet;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class CalculateEndSheet {
    private static final CalculateEndSheet INSTANCE = new CalculateEndSheet();
    public static CalculateEndSheet getInstance() {
        return INSTANCE;
    }

    public Date calculateEnd(WorkSheet workSheet) {
        long plusDays = Long.parseLong(workSheet.getWorkDuration());
        LocalDateTime instant = workSheet.getStartingWork().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime endWorkDay = LocalDateTime.from(instant).plusDays(plusDays);
        return Date.from(endWorkDay.atZone(ZoneId.systemDefault()).toInstant());
    }
}

