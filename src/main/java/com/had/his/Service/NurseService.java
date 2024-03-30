package com.had.his.Service;

import com.had.his.DTO.LoginDTO;
import com.had.his.Entity.*;
import com.had.his.Response.LoginResponse;

import java.util.List;

public interface NurseService {

    LoginResponse NurseLogin(LoginDTO loginDTO);

    Nurse getNurseDetailsByEmail(String email);

    List<NurseSchedule> viewNurseScheduleById(String nurseId);
    List<Patient> getEmergencyPatients();

    List<Patient> getAllPatients();

    Patient getPatientDetailsById(String patientId);

    Vitals addVitals(String patientId, Vitals vitals);

    Vitals editVitals(Long vitalid,Vitals vitals);

    Vitals viewVitals(String patientId);

    Vitals viewVitalsById(String patientId,Long vitalid);

    void deleteVitals(Long vitalid);

    Symptoms addSymptoms(String patientId, Symptoms symptoms);

    Symptoms editSymptoms(Long symptomid,Symptoms symptoms);

    Symptoms viewSymptoms(String patientId);

    Symptoms viewSymptomsById(String patientId,Long symptomid);

    void deleteSymptoms(Long symptomid);

    PastHistory addPastHistory(String patientId, PastHistory pastHistory);

    PastHistory editPastHistory(Long historyid,PastHistory pastHistory);

    List<PastHistory> viewPastHistory(String patientId);
    PastHistory viewPastHistoryById(String patientId,Long historyId);

    void deletePastHistory(Long historyid);

    SymptomImages addSymptomImages(String patientId,SymptomImages symptomImages);

    SymptomImages editSymptomImages(Integer id,SymptomImages symptomImages);

    List<SymptomImages> viewSymptomImages(String patientId);

    SymptomImages viewSymptomImagesById(String patientId,Integer id);


    void deleteSymptomImages(Integer id);

    PastImages addPastImages(Long historyId,PastImages pastImages);

    PastImages editPastImages(Integer imgId,PastImages pastImages);

    List<PastImages> viewPastImages(Integer historyId);

    PastImages viewPastImagesById(Integer historyId,Integer imgId);

    void deletePastImages(Integer imgId);

    List<Test> viewTestName(String patientId);

    List<Test> viewTest(String patientId);

    Test  addTestResult(Integer id,Test test);

    Test editTestResult(Integer id,Test test);

    void deleteTestResult(Integer id);

    TestImages addTestImages(Integer id,TestImages testImages);

    TestImages editTestImages(Long testimageId,TestImages testImages);

    List<TestImages> viewTestImages(Integer id);

    TestImages viewTestImagesById(Integer id,Long testimageid);


    void deleteTestImages(Long testimageId);

    Test viewTestById(String patientId,Integer id);


}
