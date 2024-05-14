package com.had.his.DAO;

import com.had.his.Entity.Consent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

<<<<<<< HEAD
import java.time.LocalDate;
import java.util.List;

=======
>>>>>>> 8e0f9a839520fed7932bb660778a56592ca8bdb2
public interface ConsentDAO extends JpaRepository<Consent,Integer> {

    @Query("select co from Consent co where co.patient.patientId=?1")
    Consent getConsentByPatient(String pid);
<<<<<<< HEAD

    @Query("select co.token from Consent co where co.patient.patientId=?1")
    String getConsentTokenByPatient(String pid);

    @Query("select c from Consent c where c.takenOn=?1 and c.expired=false ")
    List<Consent> findByConsentTakenOnBeforeAndIsExpiredIsFalse(LocalDate date);
=======
>>>>>>> 8e0f9a839520fed7932bb660778a56592ca8bdb2
}
