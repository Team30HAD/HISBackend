package com.had.his.Service;

import com.had.his.DAO.MedicationDAO;
import com.had.his.DAO.PharmacyDAO;
import com.had.his.DAO.VisitDAO;
import com.had.his.DTO.LoginDTO;
import com.had.his.Entity.Medication;
import com.had.his.Entity.Pharmacy;
import com.had.his.Entity.Visit;
import com.had.his.Response.LoginResponse;
import com.had.his.Security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PharmacyServiceImpl implements PharmacyService {


    @Autowired
    private PharmacyDAO pharmacyDao;
    @Autowired
    private MedicationDAO medicationDao;
    @Autowired
    private VisitDAO visitDao;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    public LoginResponse verifyPharmacy(LoginDTO credentials) {
        String email = credentials.getEmail();
        String enteredPassword = credentials.getPassword();

        Pharmacy pharmacy = pharmacyDao.findByEmail(email);
        if (pharmacy != null && pharmacy.getActive()) {
            if( pharmacy.isPasswordMatch(enteredPassword))
            {
                String jwttoken= jwtTokenProvider.generateToken(pharmacy);
                return new LoginResponse("Login Successful",true,jwttoken);
            } else {
                return new LoginResponse("Password not matched", false, null);
            }
        }
        else{
            return new LoginResponse("Invalid User.", false, null);
        }
    }

    public Pharmacy changePassword(LoginDTO credentials){
        Pharmacy pharmacy = pharmacyDao.findByEmail(credentials.getEmail());
        pharmacy.setPassword(credentials.getPassword());
        return pharmacyDao.save(pharmacy);
    }

    public Pharmacy findByEmail(String email){
        return pharmacyDao.findByEmail(email);
    }
    @Transactional
    public Pharmacy viewPharmacy(String pharmacyId)
    {
        return pharmacyDao.findPharmacyByPharmacyId(pharmacyId);
    }

    @Transactional
    public List<Medication> viewMedication(String patientId)
    {
        Visit visit=visitDao.getRecentVisit(patientId);
        List<Medication> Medications = new ArrayList<>();
        List<Medication> medsForVisit = medicationDao.findMedication(visit.getVisitId());
        for( Medication medication: medsForVisit){
            if(!medication.getServed())
            {
                Medications.add(medication);
            }
        }
        return Medications;
    }

    @Transactional
    public void serveMedication(Long medicineId) {
        Medication medication = medicationDao.findById(medicineId)
                .orElseThrow(() -> new RuntimeException("Medication not found with id: " + medicineId));
        medication.setServed(true);
        medicationDao.save(medication);
    }

}
