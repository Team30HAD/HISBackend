package com.had.his.Service;

import com.had.his.DAO.ConsentDAO;
import com.had.his.DAO.PatientDAO;
import com.had.his.Entity.Consent;
import com.had.his.Entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import java.time.LocalDate;

=======
>>>>>>> 8e0f9a839520fed7932bb660778a56592ca8bdb2
@Service
public class ConsentServiceImpl implements ConsentService{

    @Autowired
    ConsentDAO consentDAO;

    @Autowired
    PatientDAO patientDAO;



    @Override
<<<<<<< HEAD
    public Consent createConsent(String pid, String email) {
        Patient patient = patientDAO.findPatientDetailsById(pid);
        Consent consent = new Consent();
        consent.generateNewToken();
        consent.setExpired(false);
        consent.setTakenOn(LocalDate.now());
        consent.setTakenBy(email);
        consent.setPatient(patient);
        return consentDAO.save(consent);
    }

    @Override
    public Consent consentforExisting(String pid, String email) {
        Consent consent = consentDAO.getConsentByPatient(pid);
        if(consent.getExpired()){
            consent.generateNewToken();
            consent.setExpired(false);
            consent.setTakenOn(LocalDate.now());
            consent.setTakenBy(email);
            return consentDAO.save(consent);
        }
        else{
            return consent;
        }
=======
    public Consent createConsent(String pid) {
        Patient patient = patientDAO.findPatientDetailsById(pid);
        OtpService.sendOTP(patient.getContact());
        return null;
>>>>>>> 8e0f9a839520fed7932bb660778a56592ca8bdb2
    }

    @Override
    public boolean verifyConsent(String pid, String token) {
        Consent consent = consentDAO.getConsentByPatient(pid);
<<<<<<< HEAD
        return !consent.getExpired() && consent.getToken().equals(token);
=======
        if(!consent.getExpired() && consent.getToken().equals(token)){
            return true;
        }
        return false;
>>>>>>> 8e0f9a839520fed7932bb660778a56592ca8bdb2
    }

}
