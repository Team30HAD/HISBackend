package com.had.his.Service;

import com.had.his.DTO.AppointmentDTO;
import com.had.his.DTO.LoginDTO;
import com.had.his.Entity.*;
import com.had.his.Response.LoginResponse;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ReceptionistService {

    LoginResponse verifyReceptionist(LoginDTO loginDTO);

    Patient getPatientDetails(String pid);

    List<Patient> getAllPatients();

    Visit bookAppointmentForExistingPatient(String email,String pid, AppointmentDTO appointment);

    Visit bookAppointmentForNewPatient(String email,AppointmentDTO appointment);

    Patient updatePatient(String pid, Patient updatedPatient);

    Patient deletePatientPII(String patientId);

    void deletePatientRecords(String patientId);

    List<Doctor> getIndoorDoctors();

    List<Doctor> getOutdoorDoctors();

    List<Doctor> getAllDoctors();

    List<Patient> getIndoorPatients();

    List<Patient> getOutdoorPatients();

    List<Doctor> getOutdoorDoctorsBySpecialization(String specialization);

    List<String> getSpecialization();
}
