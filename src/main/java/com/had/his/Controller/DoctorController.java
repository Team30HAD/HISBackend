package com.had.his.Controller;

import com.had.his.DTO.LoginDTO;
import com.had.his.Entity.*;
import com.had.his.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;


    @PostMapping("/login")
    public ResponseEntity<String> doctorLogin(@RequestBody LoginDTO credentials) {
        if (doctorService.verifyDoctor(credentials)) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Login failed");
        }
    }

    @PostMapping("/passwordChange")
    public ResponseEntity<Doctor> changeDoctorPassword(@RequestBody LoginDTO credentials) {
        try {
            Doctor newDoctor = doctorService.changePassword(credentials);
            return ResponseEntity.ok(newDoctor);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/home/{email}")
    private ResponseEntity<Doctor> findByEmail(@PathVariable("email") String email) {
        try {
            Doctor newDoctor = doctorService.findByEmail(email);
            return ResponseEntity.ok(newDoctor);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/viewPatients/{email}")
    private ResponseEntity<List<Patient>> getPatients(@PathVariable("email") String email) {
        try {
            List<Patient> patients = doctorService.getPatients(email);
            return ResponseEntity.ok(patients);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/viewEmergencyPatients/{email}")
    private ResponseEntity<List<Patient>> getEmergencyPatients(@PathVariable("email") String email) {
        try {
            List<Patient> patients = doctorService.getEmergencyPatients(email);
            return ResponseEntity.ok(patients);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/patientDetails/{pid}")
    private ResponseEntity<Patient> getPatientDetails(@PathVariable("pid") String pid) {
        try {
            Patient patient = doctorService.getPatientDetails(pid);
            return ResponseEntity.ok(patient);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/patientVitals/{pid}")
    private ResponseEntity<Vitals> getVitalDetails(@PathVariable("pid") String pid) {
        try {
            Vitals vitals = doctorService.getVitals(pid);
            return ResponseEntity.ok(vitals);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/patientSymptoms/{pid}")
    private ResponseEntity<Symptoms> getSymptoms(@PathVariable("pid") String pid) {
        try {
            Symptoms symptoms = doctorService.getSymptoms(pid);
            return ResponseEntity.ok(symptoms);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/symptomImages/{pid}")
    private ResponseEntity<List<SymptomImages>> getSymptomImages(@PathVariable("pid") String pid) {
        try {
            List<SymptomImages> symptomImages = doctorService.getSymptomImages(pid);
            return ResponseEntity.ok(symptomImages);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/pastHistory/{pid}")
    private ResponseEntity<List<PastHistory>> getPastHistory(@PathVariable("pid") String pid) {
        try {
            List<PastHistory> pastHistories = doctorService.getPastHistory(pid);
            return ResponseEntity.ok(pastHistories);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/pastImages/{phid}")
    private ResponseEntity<List<PastImages>> getPastImages(@PathVariable("phid") Integer phid) {
        try {
            List<PastImages> pastImages = doctorService.getPastImages(phid);
            return ResponseEntity.ok(pastImages);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/pastMedications/{pid}")
    private ResponseEntity<List<Medication>> getPastMedications(@PathVariable("pid") String pid) {
        try {
            List<Medication> medications = doctorService.getPastMedications(pid);
            return ResponseEntity.ok(medications);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/pastTests/{pid}")
    private ResponseEntity<List<Test>> getPastTests(@PathVariable("pid") String pid) {
        try {
            List<Test> tests = doctorService.getPastTests(pid);
            return ResponseEntity.ok(tests);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/recordProgress/{pid}")
    private ResponseEntity<Progress> addProgress(@PathVariable("pid") String pid, @RequestBody Progress progress) {
        try {
            Progress newProgress=doctorService.saveProgress(pid,progress);
            return ResponseEntity.ok(newProgress);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/progressHistory/{pid}")
    private ResponseEntity<List<Progress>> getProgressHistory(@PathVariable("pid") String pid) {
        try {
            List<Progress> progresses = doctorService.getProgressHistory(pid);
            return ResponseEntity.ok(progresses);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/viewMedications/{pid}")
    private ResponseEntity<List<Medication>> getMedications(@PathVariable("pid") String pid) {
        try {
            List<Medication> medications = doctorService.getMedications(pid);
            return ResponseEntity.ok(medications);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getMedication/{pid}/{mid}")
    private ResponseEntity<Medication> getMedication(@PathVariable("pid") String pid, @PathVariable("mid") Integer mid) {
        try {
            Medication medication = doctorService.getMedicationById(pid,mid);
            return ResponseEntity.ok(medication);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/addMedication/{pid}")
    private ResponseEntity<Medication> addMedication(@PathVariable("pid") String pid, @RequestBody Medication med){
        try {
            Medication newMedication = doctorService.saveMedication(pid,med);
            return ResponseEntity.ok(newMedication);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/editMedication/{pid}/{mid}")
    private ResponseEntity<Medication> editMedication(@PathVariable("pid") String pid, @PathVariable("mid") Integer mid, @RequestBody Medication med){
        try {
            Medication newMedication = doctorService.editMedication(pid,mid,med);
            return ResponseEntity.ok(newMedication);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/deleteMedication/{pid}/{mid}")
    private ResponseEntity<Void> deleteMedication(@PathVariable("pid") String pid, @PathVariable("mid") Integer mid){
        try {
            doctorService.deleteMedication(pid, mid);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/viewTests/{pid}")
    private ResponseEntity<List<Test>> getTests(@PathVariable("pid") String pid) {
        try {
            List<Test> tests = doctorService.getTests(pid);
            return ResponseEntity.ok(tests);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getTest/{pid}/{tid}")
    private ResponseEntity<Test> getTest(@PathVariable("pid") String pid, @PathVariable("tid") Integer tid) {
        try {
            Test test = doctorService.getTestById(pid,tid);
            return ResponseEntity.ok(test);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/addTest/{pid}")
    private ResponseEntity<Test> addTest(@PathVariable("pid") String pid, @RequestBody Test test){
        try {
            Test newTest = doctorService.saveTest(pid,test);
            return ResponseEntity.ok(newTest);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/editTest/{pid}/{tid}")
    private ResponseEntity<Test> editTest(@PathVariable("pid") String pid, @PathVariable("tid") Integer tid, @RequestBody Test test){
        try {
            Test newTest = doctorService.editTest(pid,tid,test);
            return ResponseEntity.ok(newTest);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/deleteTest/{pid}/{tid}")
    private ResponseEntity<Void> deleteTest(@PathVariable("pid") String pid, @PathVariable("tid") Integer tid){
        try {
            doctorService.deleteTest(pid, tid);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/testImage/{tid}")
    private ResponseEntity<List<TestImages>> getTestImages(@PathVariable("tid") Integer tid) {
        try {
            List<TestImages> testImage = doctorService.getTestImages(tid);
            return ResponseEntity.ok(testImage);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/setDisease/{pid}/{disease}")
    private ResponseEntity<Visit> setDisease(@PathVariable("pid") String pid, @PathVariable("disease") String disease) {
        try {
            Visit newVisit = doctorService.addDisease(pid,disease);
            return ResponseEntity.ok(newVisit);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/changetoIP/{pid}/{did}")
    private ResponseEntity<Patient> recommendIP(@PathVariable("pid") String pid, @PathVariable("did") String did) {
        try {
            Patient patient = doctorService.recommendIP(pid,did);
            return ResponseEntity.ok(patient);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/discharge/{pid}")
    private ResponseEntity<Patient> dischargePatient(@PathVariable("pid") String pid) {
        try {
            Patient patient = doctorService.dischargePatient(pid);
            return ResponseEntity.ok(patient);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/admittedCount/{email}")
    private ResponseEntity<Long> admittedPatientCount(@PathVariable("email") String email){
        try {
            Long admittedPatient = doctorService.admittedPatientCount(email);
            return ResponseEntity.ok(admittedPatient);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/treatedCount/{email}")
    private ResponseEntity<Long> treatedPatientCount(@PathVariable("email") String email){
        try {
            Long admittedPatient = doctorService.treatedPatientCount(email);
            return ResponseEntity.ok(admittedPatient);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getSpecializationDoctors/{specialization}")
    private ResponseEntity<List<Doctor>> getDoctorsBySpecialization(@PathVariable("specialization") String specialization){
        try {
            List<Doctor> doctorsBySpecialization= doctorService.getDoctorsBySpecialization(specialization);
            return ResponseEntity.ok(doctorsBySpecialization);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/exitDutyDoctor/{email}")
    private ResponseEntity<Doctor> exitDoctor(@PathVariable("email") String email){
        try {
            Doctor newDoctor = doctorService.exitDoctor(email);
            return ResponseEntity.ok(newDoctor);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @PutMapping("/onDutyDoctor/{email}")
    private ResponseEntity<Doctor> dutyDoctor(@PathVariable("email") String email){
        try {
            Doctor newDoctor = doctorService.dutyDoctor(email);
            return ResponseEntity.ok(newDoctor);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
