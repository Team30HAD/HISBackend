package com.had.his.Controller;


import com.had.his.DTO.LoginDTO;
import com.had.his.Entity.Medication;
import com.had.his.Entity.Pharmacy;
import com.had.his.Response.LoginResponse;
import com.had.his.Service.PharmacyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/pharmacy")
public class PharmacyController {


    private final PharmacyService pharmacyService;

    public PharmacyController(PharmacyService pharmacyService) {
        this.pharmacyService = pharmacyService;
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> pharmacyLogin(@RequestBody LoginDTO credentials) {
        try {
            LoginResponse loginResponse = pharmacyService.verifyPharmacy(credentials);
            return ResponseEntity.ok(loginResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @PostMapping("/passwordChange")
    @PreAuthorize("hasRole('PHARMACY')")
    public ResponseEntity<Pharmacy> changeDoctorPassword(@RequestBody LoginDTO credentials) {
        try {
            Pharmacy newPharmacy = pharmacyService.changePassword(credentials);
            return ResponseEntity.ok(newPharmacy);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/home/{email}")
    @PreAuthorize("hasRole('PHARMACY')")
    private ResponseEntity<Pharmacy> findByEmail(@PathVariable("email") String email) {
        try {
            Pharmacy newPharmacy = pharmacyService.findByEmail(email);
            return ResponseEntity.ok(newPharmacy);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/viewPharmacy/{pharmacyId}")
    @PreAuthorize("hasRole('PHARMACY')")
    public ResponseEntity<Pharmacy> viewPharmacy(@PathVariable String pharmacyId)
    {
        Pharmacy pharmacy= pharmacyService.viewPharmacy(pharmacyId);
        return new ResponseEntity<>(pharmacy, HttpStatus.OK);

    }

    @GetMapping("/viewMedication/{patientId}")
    @PreAuthorize("hasRole('PHARMACY')")
    public ResponseEntity<List<Medication>> viewMedication(@PathVariable String patientId)
    {
        List<Medication> medications= pharmacyService.viewMedication(patientId);
        if(medications.isEmpty())
        {
            return new ResponseEntity<>(medications, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(medications, HttpStatus.OK);

    }

    @PutMapping("/serve/{medicineId}")
    @PreAuthorize("hasRole('PHARMACY')")
    public ResponseEntity<String> serveMedication(@PathVariable Long medicineId) {

        try {
            pharmacyService.serveMedication(medicineId);
            return ResponseEntity.ok("Medication served successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to serve medication: " + e.getMessage());
        }
    }
}