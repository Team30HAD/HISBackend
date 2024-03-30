package com.had.his.Service;

import com.had.his.DAO.ConsentDAO;
import com.had.his.DAO.PatientDAO;
import com.had.his.Entity.Consent;
import com.had.his.Entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsentServiceImpl implements ConsentService{

    @Autowired
    ConsentDAO consentDAO;

    @Autowired
    PatientDAO patientDAO;



    @Override
    public Consent createConsent(String pid) {
        Patient patient = patientDAO.findPatientDetailsById(pid);
        OtpService.sendOTP(patient.getContact());
        return null;
    }

    @Override
    public boolean verifyConsent(String pid, String token) {
        Consent consent = consentDAO.getConsentByPatient(pid);
        if(!consent.getExpired() && consent.getToken().equals(token)){
            return true;
        }
        return false;
    }

}
