package com.had.his.DAO;

import com.had.his.Entity.ReceptionistSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReceptionistScheduleDAO extends JpaRepository<ReceptionistSchedule,Long> {
    @Query("select r from ReceptionistSchedule r where r.receptionist.receptionistId=?1")
    List<ReceptionistSchedule> getReceptionistScheduleById(String receptionistId);
}
