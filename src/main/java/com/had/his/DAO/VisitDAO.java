package com.had.his.DAO;

import com.had.his.Entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VisitDAO extends JpaRepository<Visit,Integer> {

    @Query("select v from Visit v,Patient p where v.patient.patientId=?1 and v.dischargedDate is null order by v.admittedDate desc")
    Visit getRecentVisit(String pid);

}
