package com.schedule_management.main.repository;

import com.schedule_management.main.model.WorkSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkSheetRepository extends JpaRepository<WorkSheet, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM testdb.work_sheet where active = 1 order by starting_work")
    List<WorkSheet> findAllWhereActiveTrue();

}
