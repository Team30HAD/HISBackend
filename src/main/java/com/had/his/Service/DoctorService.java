package com.had.his.Service;

import com.had.his.DTO.LoginDTO;
import com.had.his.Entity.*;
import com.had.his.Response.LoginResponse;

import java.util.List;

public interface DoctorService {

    LoginResponse verifyDoctor(LoginDTO credentials);

    Doctor changePassword(LoginDTO credentials);

    Doctor findByEmail(String email);

    List<Patient> getPatients(String email);

    List<Patient> getEmergencyPatients(String email);

    Patient getPatientDetails(String pid);

    Vitals getVitals(String pid);

    Symptoms getSymptoms(String pid);

    List<SymptomImages> getSymptomImages(String pid);

    List<PastHistory> getPastHistory(String pid);

    List<PastImages> getPastImages(Integer phid);

    List<Medication> getPastMedications(String pid);

    List<Test> getPastTests(String pid);

    Progress saveProgress(String pid,Progress progress);

    List<Progress> getProgressHistory(String pid);

    List<Medication> getMedications(String pid);

    Medication getMedicationById(String pid,Integer mid);

    Medication saveMedication(String pid,Medication med);

    Medication editMedication(String pid,Integer mid,Medication med);

    void deleteMedication(String pid,Integer mid);

    List<Test> getTests(String pid);

    Test getTestById(String pid,Integer mid);

    Test saveTest(String pid,Test test);

    Test editTest(String pid,Integer mid,Test test);

    void deleteTest(String pid,Integer mid);

    List<TestImages> getTestImages(Integer tid);

    Visit addDisease(String pid,String disease);

    Patient recommendIP(String pid, String did);

    Patient dischargePatient(String pid);

    Long admittedPatientCount(String email);

    Long treatedPatientCount(String email);

    List<String> getSpecializations();

    List<Doctor> getDoctorsBySpecialization(String specialization);

    Doctor exitDoctor(String email);

}
