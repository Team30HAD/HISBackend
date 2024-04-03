package com.had.his.Controller;

import com.had.his.DAO.PatientDAO;
import com.had.his.DTO.LoginDTO;
import com.had.his.Entity.*;
import com.had.his.Response.LoginResponse;
import com.had.his.Service.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/nurse")
public class NurseController {

    @Autowired
    private NurseService nurseService;

    @PostMapping("/login")
    public ResponseEntity<?> NurseLogin(@RequestBody LoginDTO loginDto)
    {
        try {
            LoginResponse loginResponse=nurseService.NurseLogin(loginDto);
            return ResponseEntity.ok(loginResponse);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }


    @GetMapping("/getNurseDetailsByEmail/{email}")
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<Nurse> getNurseDetailsByEmail(@PathVariable String email) {
        Nurse nurseDto = nurseService.getNurseDetailsByEmail(email);
        return new ResponseEntity<>(nurseDto, HttpStatus.OK);
    }

    @GetMapping("/getEmergencyPatients")
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<List<Patient>> getEmergencyPatients() {
        List<Patient> patients = nurseService.getEmergencyPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping("/getAllPatients")
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = nurseService.getAllPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping("/viewNurseScheduleById/{nurseId}")
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<List<NurseSchedule>> viewNurseScheduleById(@PathVariable String nurseId) {
        List<NurseSchedule> nurseSchedules = nurseService.viewNurseScheduleById(nurseId);
        return new ResponseEntity<>(nurseSchedules, HttpStatus.OK);
    }

    @GetMapping("/getPatientDetailsById/{patientId}")
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<Patient> getPatientDetailsById(@PathVariable String patientId)
    {
        Patient patient=nurseService.getPatientDetailsById(patientId);
        return new ResponseEntity<>(patient,HttpStatus.OK);
    }

    @PostMapping(path = "/addVitals/{patientId}")
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<Vitals> addVitals(@PathVariable String patientId, @RequestBody Vitals vitals) {
        Vitals savevitals = nurseService.addVitals(patientId, vitals);
        return new ResponseEntity<>(savevitals, HttpStatus.CREATED);
    }

    @PutMapping(path = "/editVitals/{vitalid}")
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<Vitals> editVitals(@PathVariable Long vitalid, @RequestBody Vitals vitals) {
        Vitals savevitals = nurseService.editVitals(vitalid, vitals);
        return new ResponseEntity<>(savevitals, HttpStatus.OK);
    }

    @GetMapping("/viewVitals/{patientId}")
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<Vitals> viewVitals(@PathVariable String patientId) {
        Vitals vitals = nurseService.viewVitals(patientId);
        return new ResponseEntity<>(vitals, HttpStatus.OK);
    }

    @GetMapping("/viewVitalsById/{patientId}/{vitalid}")
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<Vitals> viewVitalsById(@PathVariable String patientId,@PathVariable Long vitalid) {
        Vitals vitals = nurseService.viewVitalsById(patientId,vitalid);
        return new ResponseEntity<>(vitals, HttpStatus.OK);
    }

    @GetMapping("/vitals-and-symptoms/{patientId}")
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<?> checkVitalsAndSymptoms(@PathVariable String patientId) {
        try {
            return ResponseEntity.ok(nurseService.checkVitalsAndSymptoms(patientId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error occurred: " + e.getMessage());
        }
    }


    @DeleteMapping("/deleteVitals/{vitalid}")
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<String> deleteVitals(@PathVariable Long vitalid) {
        nurseService.deleteVitals(vitalid);
        return ResponseEntity.ok("Vital Deleted");
    }

    @PostMapping(path = "/addSymptoms/{patientId}")
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<Symptoms> addSymptoms(@PathVariable String patientId, @RequestBody Symptoms symptoms) {
        Symptoms savesymptoms = nurseService.addSymptoms(patientId, symptoms);
        return new ResponseEntity<>(savesymptoms, HttpStatus.CREATED);
    }

    @PutMapping(path = "/editSymptoms/{symptomid}")
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<Symptoms> editSymptoms(@PathVariable Long symptomid, @RequestBody Symptoms symptoms) {
        Symptoms savesymptoms = nurseService.editSymptoms(symptomid, symptoms);
        return new ResponseEntity<>(savesymptoms, HttpStatus.OK);
    }

    @GetMapping("/viewSymptoms/{patientId}")
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<Symptoms> viewSymptoms(@PathVariable String patientId) {
        Symptoms symptoms = nurseService.viewSymptoms(patientId);
        return new ResponseEntity<>(symptoms, HttpStatus.OK);
    }

    @GetMapping("/viewSymptomsById/{patientId}/{symptomid}")
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<Symptoms> viewSymptomsById(@PathVariable String patientId,@PathVariable Long symptomid) {
        Symptoms symptoms = nurseService.viewSymptomsById(patientId,symptomid);
        return new ResponseEntity<>(symptoms, HttpStatus.OK);
    }

    @DeleteMapping("/deleteSymptoms/{symptomid}")
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<String> deleteSymptoms(@PathVariable Long symptomid) {
        nurseService.deleteSymptoms(symptomid);
        return ResponseEntity.ok("Symptom Deleted");
    }

    @PostMapping(path = "/addPastHistory/{patientId}")
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<PastHistory> addPastHistory(@PathVariable String patientId, @RequestBody PastHistory pastHistory) {
        PastHistory savepastHistory = nurseService.addPastHistory(patientId, pastHistory);
        return new ResponseEntity<>(savepastHistory, HttpStatus.CREATED);
    }

    @PutMapping(path = "/editPastHistory/{historyid}")
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<PastHistory> editPastHistory(@PathVariable Long historyid, @RequestBody PastHistory pastHistory) {
        PastHistory savepastHistory = nurseService.editPastHistory(historyid, pastHistory);
        return new ResponseEntity<>(savepastHistory, HttpStatus.OK);
    }

    @GetMapping("/viewPastHistory/{patientId}")
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<List<PastHistory>> viewPastHistory(@PathVariable String patientId) {
        List<PastHistory> pastHistoryList = nurseService.viewPastHistory(patientId);
        return new ResponseEntity<>(pastHistoryList, HttpStatus.OK);
    }

    @GetMapping("/viewPastHistoryById/{patientId}/{historyId}")
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<PastHistory> viewPastHistoryById(@PathVariable String patientId,@PathVariable Long historyId) {
        PastHistory pastHistory = nurseService.viewPastHistoryById(patientId,historyId);
        return new ResponseEntity<>(pastHistory, HttpStatus.OK);
    }

    @DeleteMapping("/deletePastHistory/{historyid}")
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<String> deletePastHistory(@PathVariable Long historyid) {
        nurseService.deletePastHistory(historyid);
        return ResponseEntity.ok("Past History Deleted");
    }

    @PostMapping(path = "/addSymptomImages/{patientId}")
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<SymptomImages> addSymptomImages(@PathVariable String patientId, @RequestBody SymptomImages symptomImages) {
        SymptomImages savesymptomimages = nurseService.addSymptomImages(patientId, symptomImages);
        return new ResponseEntity<>(savesymptomimages, HttpStatus.CREATED);
    }

    @PutMapping(path = "/editSymptomImages/{id}")
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<SymptomImages> editSymptomImages(@PathVariable Integer id, @RequestBody SymptomImages symptomImages) {
        SymptomImages savesymptomImages = nurseService.editSymptomImages(id, symptomImages);
        return new ResponseEntity<>(savesymptomImages, HttpStatus.OK);
    }

    @GetMapping("/viewSymptomImages/{patientId}")
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<List<SymptomImages>> viewSymptomImages(@PathVariable String patientId) {
        List<SymptomImages> symptomImages = nurseService.viewSymptomImages(patientId);
        return new ResponseEntity<>(symptomImages, HttpStatus.OK);
    }

    @GetMapping("/viewSymptomImagesById/{patientId}/{id}")
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<SymptomImages> viewSymptomImagesById(@PathVariable String patientId,@PathVariable Integer id) {
        SymptomImages symptomImages = nurseService.viewSymptomImagesById(patientId,id);
        return new ResponseEntity<>(symptomImages, HttpStatus.OK);
    }


    @DeleteMapping("/deleteSymptomImages/{id}")
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<String> deleteSymptomImages(@PathVariable Integer id) {
        nurseService.deleteSymptomImages(id);
        return ResponseEntity.ok("Symptom Image deleted");
    }

    @PostMapping(path = "/addPastImages/{historyId}")
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<PastImages> addPastImages(@PathVariable Long historyId, @RequestBody PastImages pastImages) {
        PastImages savepastImages = nurseService.addPastImages(historyId, pastImages);
        return new ResponseEntity<>(savepastImages, HttpStatus.OK);
    }

    @PutMapping(path = "/editPastImages/{imgId}")
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<PastImages> editPastImages(@PathVariable Integer imgId, @RequestBody PastImages pastImages) {
        PastImages savepastImages = nurseService.editPastImages(imgId, pastImages);
        return new ResponseEntity<>(savepastImages, HttpStatus.OK);
    }

    @GetMapping("/viewPastImages/{historyId}")
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<List<PastImages>> viewPastImages(@PathVariable Integer historyId) {
        List<PastImages> pastImages = nurseService.viewPastImages(historyId);
        return new ResponseEntity<>(pastImages, HttpStatus.OK);
    }

    @GetMapping("/viewPastImagesById/{historyId}/{imgId}")
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<PastImages> viewPastImagesById(@PathVariable Integer historyId,@PathVariable Integer imgId) {
        PastImages pastImages = nurseService.viewPastImagesById(historyId,imgId);
        return new ResponseEntity<>(pastImages, HttpStatus.OK);
    }

    @DeleteMapping("/deletePastImages/{imgId}")
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<String> deletePastImages(@PathVariable Integer imgId) {
        nurseService.deletePastImages(imgId);
        return ResponseEntity.ok("Past Image Deleted");
    }

    @GetMapping("/viewTestName/{patientId}")
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<List<Test>> viewTestName(@PathVariable String patientId) {
        List<Test> tests = nurseService.viewTestName(patientId);
        return new ResponseEntity<>(tests, HttpStatus.OK);
    }

    @GetMapping("/viewTest/{patientId}")
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<List<Test>> viewTest(@PathVariable String patientId) {
        List<Test> tests = nurseService.viewTest(patientId);
        return new ResponseEntity<>(tests, HttpStatus.OK);
    }

    @GetMapping("/viewTestById/{patientId}/{id}")
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<Test> viewTest(@PathVariable String patientId,@PathVariable Integer id) {
        Test test = nurseService.viewTestById(patientId,id);
        return new ResponseEntity<>(test, HttpStatus.OK);
    }

    @PostMapping("/addTestResult/{id}")
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<Test> addTestResult(@PathVariable Integer id, @RequestBody Test test) {
        Test savetest = nurseService.addTestResult(id, test);
        return new ResponseEntity<>(savetest, HttpStatus.OK);
    }

    @PutMapping("/editTestResult/{id}")
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<Test> editTestResult(@PathVariable Integer id, @RequestBody Test test) {
        Test savetest = nurseService.editTestResult(id, test);
        return new ResponseEntity<>(savetest, HttpStatus.OK);
    }

    @DeleteMapping("/deleteTestResult/{id}")
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<String> deleteTestResult(@PathVariable Integer id){
        nurseService.deleteTestResult(id);
        return ResponseEntity.ok("Test Result removed");
    }

    @PostMapping("/addTestImage/{id}")
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<TestImages> addTestImages(@PathVariable Integer id, @RequestBody TestImages testImages) {
        TestImages savetestimage= nurseService.addTestImages(id, testImages);
        return new ResponseEntity<>(savetestimage, HttpStatus.CREATED);
    }

    @PutMapping ("/editTestImage/{testimageId}")
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<TestImages> editTestImages(@PathVariable Long testimageId, @RequestBody TestImages testImages) {
        TestImages savetestimage= nurseService.editTestImages(testimageId, testImages);
        return new ResponseEntity<>(savetestimage, HttpStatus.OK);
    }

    @GetMapping("/viewTestImages/{id}")
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<List<TestImages>>  viewTestImages(@PathVariable Integer id)
    {
        List<TestImages> testImages=nurseService.viewTestImages(id);
        return new ResponseEntity<>(testImages,HttpStatus.OK);
    }

    @GetMapping("/viewTestImagesById/{id}/{testimageId}")
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<TestImages>  viewTestImagesById(@PathVariable Integer id,@PathVariable Long testimageId)
    {
        TestImages testImages=nurseService.viewTestImagesById(id,testimageId);
        return new ResponseEntity<>(testImages,HttpStatus.OK);
    }

    @DeleteMapping("/deleteTestImage/{testimageId}")
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<String> deleteTestImages(@PathVariable Long testimageId)
    {
        nurseService.deleteTestImages(testimageId);
        return ResponseEntity.ok("Test Image Deleted");
    }


}
