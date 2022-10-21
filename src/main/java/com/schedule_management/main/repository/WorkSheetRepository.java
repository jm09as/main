package com.schedule_management.main.repository;

import com.schedule_management.main.model.WorkSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkSheetRepository extends JpaRepository<WorkSheet, Long> {

}
