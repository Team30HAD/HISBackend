package com.had.his.DAO;

import com.had.his.Entity.Consent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ConsentDAO extends JpaRepository<Consent,Integer> {

    @Query("select co from Consent co where co.patient.patientId=?1")
    Consent getConsentByPatient(String pid);
}
