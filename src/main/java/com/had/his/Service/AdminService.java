package com.had.his.Service;

import com.had.his.DTO.LoginDTO;
import com.had.his.Entity.*;

import java.util.List;

public interface AdminService {
    Admin saveAdmin(Admin admin);
    boolean verifyAdmin(LoginDTO credentials);
    Doctor saveDoctor(Doctor doc);
    Nurse saveNurse(Nurse nurse);
    Receptionist saveReceptionist(Receptionist rec);
    Pharmacy savePharmacy(Pharmacy pharma);
    List<Nurse> getAllNurses();
    List<Receptionist> getAllReceptionists();
    List<Doctor> getAllDoctors(String department);
    List<Pharmacy> getAllPharmacies();
    public void deactivateDoctor(String doctorId);

    public void deactivateNurse(String nurseId);
    public void deactivatePharmacy(String pharmaId);
    public void deactivateReceptionist(String recepId);
    public long countPatient();
    public long countDoctor();
    public long countNurse();

    public Doctor editDoctor(String did,Doctor doctor);
    public Nurse editNurse(String nid,Nurse nurse);
    public Pharmacy editPharmacy(String phid,Pharmacy pharma);
    public Receptionist editReceptionist(String rid,Receptionist recep);



}
