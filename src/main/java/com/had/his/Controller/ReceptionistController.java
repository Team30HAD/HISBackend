package com.had.his.Controller;

import com.had.his.DTO.LoginDTO;
import com.had.his.Entity.*;
import com.had.his.Service.ReceptionistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/bookAppointmentForExistingPatient/{pid}/{did}")
    public ResponseEntity<Visit> bookAppointmentForExistingPatient(@PathVariable("pid") String pid,@PathVariable("did") String did,
                                                                   @RequestBody Visit visit)
    {
        try {
            receptionistService.bookAppointmentForExistingPatient(pid,did,visit);
            return ResponseEntity.ok(visit);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/bookAppointmentForNewPatient/{did}")
    public ResponseEntity<Visit> bookAppointmentForNewPatient(@PathVariable("did") String did, @RequestBody Visit visit)
    {
        try {
            receptionistService.bookAppointmentForNewPatient(did,visit);
            return ResponseEntity.ok(visit);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/bookEmergencyAppointment/{did}")
    public ResponseEntity<Visit> bookEmergencyAppointment(@PathVariable("did") String did, @RequestBody Visit visit)
    {
        try {
            receptionistService.bookEmergencyAppointment(did,visit);
            return ResponseEntity.ok(visit);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getPatientDetails/{pid}")
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


    @PostMapping("/addPatient")
    private ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {
        try {
            Patient newPatient = receptionistService.addPatient(patient);
            return ResponseEntity.ok(newPatient);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }




    @PutMapping("/updatePatient/{pid}")
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
    public ResponseEntity<Patient> deletePatientPII(@PathVariable("pid") String patientId) {
        try {
            Patient updatedPatient = receptionistService.deletePatientPII(patientId);
            return ResponseEntity.ok(updatedPatient);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/deletePatientRecords/{pid}")
    public ResponseEntity<String> deletePatientRecords(@PathVariable("pid") String patientId) {
        try {
            receptionistService.deletePatientRecords(patientId);
            return ResponseEntity.ok("Medical records deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getAllPatients")
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


    @GetMapping("/getDoctorsBySpecialization/{specialization}")
    private ResponseEntity<Map<String, Object>> getDoctorsBySpecialization(@PathVariable String specialization) {
        try {
            List<Doctor> doctors = receptionistService.getDoctorsBySpecialization(specialization);

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


}
