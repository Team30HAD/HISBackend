package com.had.his.Service;

import com.had.his.DTO.LoginDTO;
import com.had.his.Entity.*;
import com.had.his.Response.LoginResponse;

import java.util.List;

public interface AdminService {
    Admin saveAdmin(Admin admin);
    LoginResponse verifyAdmin(LoginDTO credentials);
    Doctor saveDoctor(Doctor doc);
    Nurse saveNurse(Nurse nurse);
    Receptionist saveReceptionist(Receptionist rec);
    Pharmacy savePharmacy(Pharmacy pharma);
    List<Nurse> getAllNurses();
    List<Receptionist> getAllReceptionists();
    List<Doctor> getAllDoctors(String department);
    List<Pharmacy> getAllPharmacies();
    Doctor getDoctor(String doctorId);
    Nurse getNurse(String nurseId);
    Receptionist getReceptionist(String recepId);
    Pharmacy getPharmacy (String pharmaId);

    void deactivateDoctor(String doctorId);

    void deactivateNurse(String nurseId);
    void deactivatePharmacy(String pharmaId);
    void deactivateReceptionist(String recepId);
    long countPatient();
    long countDoctor();
    long countNurse();
    long countReceptionist();

    long countPharmacy();

    Doctor editDoctor(String did,Doctor doctor);
    Nurse editNurse(String nid,Nurse nurse);
    Pharmacy editPharmacy(String phid,Pharmacy pharma);
    Receptionist editReceptionist(String rid,Receptionist recep);
    Hospital getHospital();

    Specialization addSpecialization(Specialization specialization);
}