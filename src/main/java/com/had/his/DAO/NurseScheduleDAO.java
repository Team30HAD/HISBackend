package com.had.his.DAO;

import com.had.his.Entity.NurseSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NurseScheduleDAO extends JpaRepository<NurseSchedule,Integer> {
    @Query("select n from NurseSchedule n where n.nurse.nurseId=?1")
    List<NurseSchedule> getNurseScheduleById(String nurseId);
}
