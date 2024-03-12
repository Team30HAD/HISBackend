package com.had.his.Controller;

import com.had.his.DTO.LoginDTO;
import com.had.his.Entity.*;
import com.had.his.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/addAdmin")
    private ResponseEntity<Admin> saveAdmin(@RequestBody Admin admin) {
        try {
            Admin newAdmin = adminService.saveAdmin(admin);
            return ResponseEntity.ok(newAdmin);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<String> adminLogin(@RequestBody LoginDTO credentials) {
        if (adminService.verifyAdmin(credentials)) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Login failed");
        }
    }

    @PostMapping("/addDoctor")
    private ResponseEntity<Doctor> saveDoctor(@RequestBody Doctor doc) {
        try {
            Doctor newDoctor = adminService.saveDoctor(doc);
            return ResponseEntity.ok(newDoctor);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("/viewDoctors/{department}") //
    private ResponseEntity<List<Doctor>> getAllDoctors(@PathVariable("department") String department) {
        try {
            List<Doctor> doctors = adminService.getAllDoctors(department);
            return ResponseEntity.ok(doctors);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/addNurse")
    private ResponseEntity<Nurse> saveNurse(@RequestBody Nurse nurse) {
        try {
            Nurse newNurse = adminService.saveNurse(nurse);
            return ResponseEntity.ok(newNurse);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("/viewNurses")
    private ResponseEntity<List<Nurse>> getAllNurses() {
        try {
            List<Nurse> nurses = adminService.getAllNurses();
            return ResponseEntity.ok(nurses);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @PostMapping("/addReceptionist") //
    private ResponseEntity<Receptionist> saveReceptionist(@RequestBody Receptionist rec) {
        try {
            Receptionist newRec = adminService.saveReceptionist(rec);
            return ResponseEntity.ok(newRec);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("/viewReceptionists") //
    private ResponseEntity<List<Receptionist>> getAllReceptionists() {
        try {
            List<Receptionist> receptionists = adminService.getAllReceptionists();
            return ResponseEntity.ok(receptionists);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @PostMapping("/addPharmacy") //
    private ResponseEntity<Pharmacy> saveReceptionist(@RequestBody Pharmacy pharma) {
        try {
            Pharmacy newPh = adminService.savePharmacy(pharma);
            return ResponseEntity.ok(newPh);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("/viewPharmacies") //
    private ResponseEntity<List<Pharmacy>> getAllPharmacies() {
        try {
            List<Pharmacy> pharmacies = adminService.getAllPharmacies();
            return ResponseEntity.ok(pharmacies);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/deactivateDoctor/{doctorId}") //
    public ResponseEntity<String> deactivateDoctor(@PathVariable("doctorId") String doctorId) {
        try {
            adminService.deactivateDoctor(doctorId);
            return ResponseEntity.ok("Doctor with ID " + doctorId + " deactivated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/deactivateNurse/{nurseId}") //
    public ResponseEntity<String> deactivateNurse(@PathVariable("nurseId") String nurseId) {
        try {
            adminService.deactivateNurse(nurseId);
            return ResponseEntity.ok("Nurse with ID " + nurseId + " deactivated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/deactivateReceptionist/{recepId}") //
    public ResponseEntity<String> deactivateReceptionist(@PathVariable("recepId") String recepId) {
        try {
            adminService.deactivateReceptionist(recepId);
            return ResponseEntity.ok("Receptionist with ID " + recepId + " deactivated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/deactivateReceptionist/{pharmaId}") //
    public ResponseEntity<String> deactivatePharmacy(@PathVariable("pharmaId") String pharmaId) {
        try {
            adminService.deactivatePharmacy(pharmaId);
            return ResponseEntity.ok("Phaarmacy with ID " + pharmaId + " deactivated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/patientCount") //
    public ResponseEntity<Long> getPatientCount() {
        try {
            long patientCount = adminService.countPatient();
            return ResponseEntity.ok(patientCount);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/doctorCount") //
    public ResponseEntity<Long> getDoctorCount(){
        try {
            long doctorCount = adminService.countDoctor();
            return ResponseEntity.ok(doctorCount);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/nurseCount") //
    public ResponseEntity<Long> getNurseCount(){

        try {
            long nurseCount = adminService.countNurse();
            return ResponseEntity.ok(nurseCount);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @PutMapping("/editDoctor/{did}") //
    private ResponseEntity<Doctor> editDoctor(@PathVariable("did") String did,@RequestBody Doctor doctor){
        try {
            Doctor newDoctor = adminService.editDoctor(did,doctor);
            return ResponseEntity.ok(newDoctor);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/editNurse/{nid}") //
    private ResponseEntity<Nurse> editNurse(@PathVariable("nid") String nid,@RequestBody Nurse nurse){
        try {
            Nurse newNurse = adminService.editNurse(nid,nurse);
            return ResponseEntity.ok(newNurse);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/editPharmacy/{phid}") //
    private ResponseEntity<Pharmacy> editPharmacy(@PathVariable("phid") String phid,@RequestBody Pharmacy pharma){
        try {
            Pharmacy newPharma = adminService.editPharmacy(phid,pharma);
            return ResponseEntity.ok(newPharma);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/editReceptionist/{rid}") //
    private ResponseEntity<Receptionist> editReceptionist(@PathVariable("rif") String rid,@RequestBody Receptionist recep){
        try {
            Receptionist newReceptionist = adminService.editReceptionist(rid,recep);
            return ResponseEntity.ok(newReceptionist);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


}