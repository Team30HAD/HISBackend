package com.had.his.Service;


import com.had.his.DTO.LoginDTO;
import com.had.his.Entity.Medication;
import com.had.his.Entity.Pharmacy;

import java.util.List;


public interface PharmacyService {

    boolean verifyPharmacy(LoginDTO credentials);

    Pharmacy changePassword(LoginDTO credentials);

    Pharmacy findByEmail(String email);

    Pharmacy viewPharmacy(String pharmacyId);

    List<Medication> viewMedication(String patientId);

    void serveMedication(Long medicineId);
}
