package com.had.his.Controller;

import com.had.his.DTO.LoginDTO;
import com.had.his.Entity.*;
import com.had.his.Response.LoginResponse;
import com.had.his.Service.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nurse")
public class NurseController {

    @Autowired
    private NurseService nurseService;

    @PostMapping("/login")
    public ResponseEntity<?> NurseLogin(@RequestBody LoginDTO loginDto)
    {
        LoginResponse loginResponse=nurseService.NurseLogin(loginDto);
        return ResponseEntity.ok(loginResponse);
    }


    @GetMapping("/getNurseDetailsById/{nurseId}")
    public ResponseEntity<Nurse> getNurseDetailsById(@PathVariable String nurseId) {
        Nurse nurseDto = nurseService.getNurseDetailsById(nurseId);
        return new ResponseEntity<>(nurseDto, HttpStatus.OK);
    }

    @GetMapping("/getEmergencyPatients")
    public ResponseEntity<List<Patient>> getEmergencyPatients() {
        List<Patient> patients = nurseService.getEmergencyPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping("/getAllPatients")
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = nurseService.getAllPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping("/getPatientDetailsById/{patientId}")
    public ResponseEntity<Patient> getPatientDetailsById(@PathVariable String patientId)
    {
        Patient patient=nurseService.getPatientDetailsById(patientId);
        return new ResponseEntity<>(patient,HttpStatus.OK);
    }

    @PostMapping(path = "/addVitals/{patientId}")
    public ResponseEntity<Vitals> addVitals(@PathVariable String patientId, @RequestBody Vitals vitals) {
        Vitals savevitals = nurseService.addVitals(patientId, vitals);
        return new ResponseEntity<>(savevitals, HttpStatus.CREATED);
    }

    @PutMapping(path = "/editVitals/{vitalid}")
    public ResponseEntity<Vitals> editVitals(@PathVariable Long vitalid, @RequestBody Vitals vitals) {
        Vitals savevitals = nurseService.editVitals(vitalid, vitals);
        return new ResponseEntity<>(savevitals, HttpStatus.OK);
    }

    @GetMapping("/viewVitals/{patientId}")
    public ResponseEntity<Vitals> viewVitals(@PathVariable String patientId) {
        Vitals vitals = nurseService.viewVitals(patientId);
        return new ResponseEntity<>(vitals, HttpStatus.OK);
    }

    @DeleteMapping("/deleteVitals/{vitalid}")
    public ResponseEntity<String> deleteVitals(@PathVariable Long vitalid) {
        nurseService.deleteVitals(vitalid);
        return ResponseEntity.ok("Vital Deleted");
    }

    @PostMapping(path = "/addSymptoms/{patientId}")
    public ResponseEntity<Symptoms> addSymptoms(@PathVariable String patientId, @RequestBody Symptoms symptoms) {
        Symptoms savesymptoms = nurseService.addSymptoms(patientId, symptoms);
        return new ResponseEntity<>(savesymptoms, HttpStatus.CREATED);
    }

    @PutMapping(path = "/editSymptoms/{symptomid}")
    public ResponseEntity<Symptoms> editSymptoms(@PathVariable Long symptomid, @RequestBody Symptoms symptoms) {
        Symptoms savesymptoms = nurseService.editSymptoms(symptomid, symptoms);
        return new ResponseEntity<>(savesymptoms, HttpStatus.OK);
    }

    @GetMapping("/viewSymptoms/{patientId}")
    public ResponseEntity<Symptoms> viewSymptoms(@PathVariable String patientId) {
        Symptoms symptoms = nurseService.viewSymptoms(patientId);
        return new ResponseEntity<>(symptoms, HttpStatus.OK);
    }

    @DeleteMapping("/deleteSymptoms/{symptomid}")
    public ResponseEntity<String> deleteSymptoms(@PathVariable Long symptomid) {
        nurseService.deleteSymptoms(symptomid);
        return ResponseEntity.ok("Symptom Deleted");
    }

    @PostMapping(path = "/addPastHistory/{patientId}")
    public ResponseEntity<PastHistory> addPastHistory(@PathVariable String patientId, @RequestBody PastHistory pastHistory) {
        PastHistory savepastHistory = nurseService.addPastHistory(patientId, pastHistory);
        return new ResponseEntity<>(savepastHistory, HttpStatus.CREATED);
    }

    @PutMapping(path = "/editPastHistory/{historyid}")
    public ResponseEntity<PastHistory> editPastHistory(@PathVariable Long historyid, @RequestBody PastHistory pastHistory) {
        PastHistory savepastHistory = nurseService.editPastHistory(historyid, pastHistory);
        return new ResponseEntity<>(savepastHistory, HttpStatus.OK);
    }

    @GetMapping("/viewPastHistory/{patientId}")
    public ResponseEntity<List<PastHistory>> viewPastHistory(@PathVariable String patientId) {
        List<PastHistory> pastHistoryList = nurseService.viewPastHistory(patientId);
        return new ResponseEntity<>(pastHistoryList, HttpStatus.OK);
    }

    @DeleteMapping("/deletePastHistory/{historyid}")
    public ResponseEntity<String> deletePastHistory(@PathVariable Long historyid) {
        nurseService.deletePastHistory(historyid);
        return ResponseEntity.ok("Past History Deleted");
    }

    @PostMapping(path = "/addSymptomImages/{patientId}")
    public ResponseEntity<SymptomImages> addSymptomImages(@PathVariable String patientId, @RequestBody SymptomImages symptomImages) {
        SymptomImages savesymptomimages = nurseService.addSymptomImages(patientId, symptomImages);
        return new ResponseEntity<>(savesymptomimages, HttpStatus.CREATED);
    }

    @PutMapping(path = "/editSymptomImages/{id}")
    public ResponseEntity<SymptomImages> editSymptomImages(@PathVariable Integer id, @RequestBody SymptomImages symptomImages) {
        SymptomImages savesymptomImages = nurseService.editSymptomImages(id, symptomImages);
        return new ResponseEntity<>(savesymptomImages, HttpStatus.OK);
    }

    @GetMapping("/viewSymptomImages/{patientId}")
    public ResponseEntity<List<SymptomImages>> viewSymptomImages(@PathVariable String patientId) {
        List<SymptomImages> symptomImages = nurseService.viewSymptomImages(patientId);
        return new ResponseEntity<>(symptomImages, HttpStatus.OK);
    }

    @DeleteMapping("/deleteSymptomImages/{id}")
    public ResponseEntity<String> deleteSymptomImages(@PathVariable Integer id) {
        nurseService.deleteSymptomImages(id);
        return ResponseEntity.ok("Symptom Image deleted");
    }

    @PostMapping(path = "/addPastImages/{historyId}")
    public ResponseEntity<PastImages> addPastImages(@PathVariable Long historyId, @RequestBody PastImages pastImages) {
        PastImages savepastImages = nurseService.addPastImages(historyId, pastImages);
        return new ResponseEntity<>(savepastImages, HttpStatus.OK);
    }

    @PutMapping(path = "/editPastImages/{imgId}")
    public ResponseEntity<PastImages> editPastImages(@PathVariable Integer imgId, @RequestBody PastImages pastImages) {
        PastImages savepastImages = nurseService.editPastImages(imgId, pastImages);
        return new ResponseEntity<>(savepastImages, HttpStatus.OK);
    }

    @GetMapping("/viewPastImages/{historyId}")
    public ResponseEntity<List<PastImages>> viewPastImages(@PathVariable Integer historyId) {
        List<PastImages> pastImages = nurseService.viewPastImages(historyId);
        return new ResponseEntity<>(pastImages, HttpStatus.OK);
    }

    @DeleteMapping("/deletePastImages/{imgId}")
    public ResponseEntity<String> deletePastImages(@PathVariable Integer imgId) {
        nurseService.deletePastImages(imgId);
        return ResponseEntity.ok("Past Image Deleted");
    }

    @GetMapping("/viewTestName/{patientId}")
    public ResponseEntity<List<Test>> viewTestName(@PathVariable String patientId) {
        List<Test> tests = nurseService.viewTestName(patientId);
        return new ResponseEntity<>(tests, HttpStatus.OK);
    }

    @GetMapping("/viewTest/{patientId}")
    public ResponseEntity<List<Test>> viewTest(@PathVariable String patientId) {
        List<Test> tests = nurseService.viewTest(patientId);
        return new ResponseEntity<>(tests, HttpStatus.OK);
    }

    @PostMapping("/addTestResult/{id}")
    public ResponseEntity<Test> addTestResult(@PathVariable Integer id, @RequestBody Test test) {
        Test savetest = nurseService.addTestResult(id, test);
        return new ResponseEntity<>(savetest, HttpStatus.OK);
    }

    @PutMapping("/editTestResult/{id}")
    public ResponseEntity<Test> editTestResult(@PathVariable Integer id, @RequestBody Test test) {
        Test savetest = nurseService.editTestResult(id, test);
        return new ResponseEntity<>(savetest, HttpStatus.OK);
    }

    @DeleteMapping("/deleteTestResult/{id}")
    public ResponseEntity<String> deleteTestResult(@PathVariable Integer id){
        nurseService.deleteTestResult(id);
        return ResponseEntity.ok("Test Result removed");
    }

    @PostMapping("/addTestImage/{id}")
    public ResponseEntity<TestImages> addTestImages(@PathVariable Integer id, @RequestBody TestImages testImages) {
        TestImages savetestimage= nurseService.addTestImages(id, testImages);
        return new ResponseEntity<>(savetestimage, HttpStatus.CREATED);
    }

    @PutMapping ("/editTestImage/{testimageId}")
    public ResponseEntity<TestImages> editTestImages(@PathVariable Long testimageId, @RequestBody TestImages testImages) {
        TestImages savetestimage= nurseService.editTestImages(testimageId, testImages);
        return new ResponseEntity<>(savetestimage, HttpStatus.OK);
    }

    @GetMapping("/viewTestImages/{id}")
    public ResponseEntity<List<TestImages>>  viewTestImages(@PathVariable Integer id)
    {
        List<TestImages> testImages=nurseService.viewTestImages(id);
        return new ResponseEntity<>(testImages,HttpStatus.OK);
    }

    @DeleteMapping("/deleteTestImage/{testimageId}")
    public ResponseEntity<String> deleteTestImages(@PathVariable Long testimageId)
    {
        nurseService.deleteTestImages(testimageId);
        return ResponseEntity.ok("Test Image Deleted");
    }


}
