package com.had.his.Service;

import com.had.his.DTO.LoginDTO;
import com.had.his.Entity.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ReceptionistService {
    Patient getPatientDetails(String pid);

    List<Patient> getAllPatients();

    Patient addPatient(Patient patient);

    Visit bookAppointmentForExistingPatient(String pid, String did, Visit visit);

    Visit bookAppointmentForNewPatient(String did, Visit visit);

    Visit bookEmergencyAppointment(String did, Visit visit);

    Patient updatePatient(String pid, Patient updatedPatient);

    //void deletePatient(String pid);
    Patient deletePatientPII(String patientId);
    Patient deletePatientRecords(String patientId);


    // Methods for counting and listing doctors based on work location and availability
//    int getIndoorDoctorsCount();
//
//    int getOutdoorDoctorsCount();
//
//    int getTotalDoctorsCount();

    List<Doctor> getIndoorDoctors();

    List<Doctor> getOutdoorDoctors();

    List<Doctor> getAllDoctors();


    // Methods for counting and listing patients based on their location
//    int getIndoorPatientsCount();
//
//    int getOutdoorPatientsCount();
//
//    int getTotalPatientsCount();

    List<Patient> getIndoorPatients();

    List<Patient> getOutdoorPatients();

    //List<Patient> getAllPatientsWithDetails();

    List<Doctor> getDoctorsBySpecialization(String specialization);
    // Methods for working with beds
    List<Bed> getAllBeds();

    List<Bed> getAvailableBeds();

    List<Bed> getFilledBeds();
}
