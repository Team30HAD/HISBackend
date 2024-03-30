package com.had.his.Controller;

import com.had.his.DTO.AppointmentDTO;
import com.had.his.DTO.LoginDTO;
import com.had.his.Entity.*;
import com.had.his.Response.LoginResponse;
import com.had.his.Service.ReceptionistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/receptionist")
public class ReceptionistController {

    @Autowired
    private ReceptionistService receptionistService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> verifyReceptionist(@RequestBody LoginDTO loginDto)
    {
        try {
            LoginResponse loginResponse = receptionistService.verifyReceptionist(loginDto);
            return ResponseEntity.ok(loginResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }


    @PostMapping("/bookAppointmentForExistingPatient/{email}/{pid}")
    @PreAuthorize("hasRole('RECEPTIONIST')")
    public ResponseEntity<Visit> bookAppointmentForExistingPatient(@PathVariable("email") String email, @PathVariable("pid") String pid, @RequestBody AppointmentDTO appointment)
    {
        try {
            Visit visit = receptionistService.bookAppointmentForExistingPatient(email,pid,appointment);
            return ResponseEntity.ok(visit);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/bookAppointmentForNewPatient/{email}")
    @PreAuthorize("hasRole('RECEPTIONIST')")
    public ResponseEntity<Visit> bookAppointmentForNewPatient(@PathVariable("email") String email, @RequestBody AppointmentDTO appointment)
    {
        try {
            Visit visit = receptionistService.bookAppointmentForNewPatient(email,appointment);
            return ResponseEntity.ok(visit);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @GetMapping("/getPatientDetails/{pid}")
    @PreAuthorize("hasRole('RECEPTIONIST')")
    private ResponseEntity<Patient> getPatientDetails(@PathVariable("pid") String pid) {
        try {
            Patient patient = receptionistService.getPatientDetails(pid);
            if (patient != null) {
                return ResponseEntity.ok(patient);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @PutMapping("/updatePatient/{pid}")
    @PreAuthorize("hasRole('RECEPTIONIST')")
    private ResponseEntity<Patient> updatePatient(@PathVariable("pid") String pid, @RequestBody Patient updatedPatient) {
        try {
            Patient newPatient = receptionistService.updatePatient(pid, updatedPatient);
            return ResponseEntity.ok(newPatient);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/deletePatientPII/{pid}")
    @PreAuthorize("hasRole('RECEPTIONIST')")
    public ResponseEntity<Patient> deletePatientPII(@PathVariable("pid") String patientId) {
        try {
            Patient updatedPatient = receptionistService.deletePatientPII(patientId);
            return ResponseEntity.ok(updatedPatient);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/deletePatientRecords/{pid}")
    @PreAuthorize("hasRole('RECEPTIONIST')")
    public ResponseEntity<String> deletePatientRecords(@PathVariable("pid") String patientId) {
        try {
            receptionistService.deletePatientRecords(patientId);
            return ResponseEntity.ok("Medical records deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getAllPatients")
    @PreAuthorize("hasRole('RECEPTIONIST')")
    private ResponseEntity<Map<String, Object>> getAllPatients() {
        try {
            List<Patient> patients = receptionistService.getAllPatients();

            if (patients != null) {
                int patientCount = patients.size();
                Map<String, Object> response = new HashMap<>();
                response.put("patientCount", patientCount);
                response.put("patients", patients);
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @GetMapping("/getIndoorPatients")
    @PreAuthorize("hasRole('RECEPTIONIST')")
    private ResponseEntity<Map<String, Object>> getIndoorPatients() {
        try {
            List<Patient> patients = receptionistService.getIndoorPatients();

            if (patients != null) {
                int patientCount = patients.size();
                Map<String, Object> response = new HashMap<>();
                response.put("patientCount", patientCount);
                response.put("patients", patients);
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @GetMapping("/getOutdoorPatients")
    @PreAuthorize("hasRole('RECEPTIONIST')")
    private ResponseEntity<Map<String, Object>> getOutdoorPatients() {
        try {
            List<Patient> patients = receptionistService.getOutdoorPatients();

            if (patients != null) {
                int patientCount = patients.size();
                Map<String, Object> response = new HashMap<>();
                response.put("patientCount", patientCount);
                response.put("patients", patients);
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getAllDoctors")
    @PreAuthorize("hasRole('RECEPTIONIST')")
    private ResponseEntity<Map<String, Object>> getAllDoctors() {
        try {
            List<Doctor> doctors = receptionistService.getAllDoctors();

            if (doctors != null) {
                int doctorCount = doctors.size();
                Map<String, Object> response = new HashMap<>();
                response.put("doctorCount", doctorCount);
                response.put("doctors", doctors);
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getIndoorDoctors")
    @PreAuthorize("hasRole('RECEPTIONIST')")
    private ResponseEntity<Map<String, Object>> getIndoorDoctors() {
        try {
            List<Doctor> doctors = receptionistService.getIndoorDoctors();

            if (doctors != null) {
                int doctorCount = doctors.size();
                Map<String, Object> response = new HashMap<>();
                response.put("doctorCount", doctorCount);
                response.put("doctors", doctors);
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getOutdoorDoctors")
    @PreAuthorize("hasRole('RECEPTIONIST')")
    private ResponseEntity<Map<String, Object>> getOutdoorDoctors() {
        try {
            List<Doctor> doctors = receptionistService.getOutdoorDoctors();

            if (doctors != null) {
                int doctorCount = doctors.size();
                Map<String, Object> response = new HashMap<>();
                response.put("doctorCount", doctorCount);
                response.put("doctors", doctors);
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @GetMapping("/getOutdoorDoctorsBySpecialization/{specialization}")
    @PreAuthorize("hasRole('RECEPTIONIST')")
    private ResponseEntity<Map<String, Object>> getDoctorsBySpecialization(@PathVariable String specialization) {
        try {
            List<Doctor> doctors = receptionistService.getOutdoorDoctorsBySpecialization(specialization);

            if (doctors != null) {
                int doctorCount = doctors.size();
                Map<String, Object> response = new HashMap<>();
                response.put("doctorCount", doctorCount);
                response.put("doctors", doctors);
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getAllSpecializations")
    @PreAuthorize("hasRole('RECEPTIONIST')")
    private ResponseEntity<?> getAllSpecializations() {
        try {
            List<String> specializations = receptionistService.getSpecialization();
            if (specializations.isEmpty())
                return ResponseEntity.ok("No specializations exist! Please add a doctor first");
            return ResponseEntity.ok(specializations);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while fetching specializations: " + e.getMessage());
        }
    }




}
