package com.had.his.Service;

import com.had.his.DTO.AppointmentDTO;
import com.had.his.DTO.LoginDTO;
import com.had.his.Entity.*;
import com.had.his.Response.LoginResponse;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ReceptionistService {

<<<<<<< HEAD
    LoginResponse verifyReceptionist(LoginDTO loginDTO);

    void logoutService(String email);

    Patient getPatientDetails(String pid,String consenttoken);
=======
    Receptionist getReceptionistDetailsByEmail(String email);
    LoginResponse verifyReceptionist(LoginDTO loginDTO);

    Patient getPatientDetails(String pid);
>>>>>>> 8e0f9a839520fed7932bb660778a56592ca8bdb2

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
    List<ReceptionistSchedule> viewReceptionistScheduleById(String receptionistId);
<<<<<<< HEAD

    String sendOtp(String contact);

    Boolean verifyOtp(String contact,String otp);

    Receptionist getReceptionistDetailsByEmail(String email);

    String getConsentToken(String pid);

    List<String> getAllAppointments(String pid, String consenttoken);

    String deleteAppointment(String pid, String email);

    Receptionist changePassword(LoginDTO loginDTO);

    String getContactFromEmail(String email);

    String sendOtpPass(String contact);

    Boolean verifyOtpPass(String contact, String otp);
}
=======
}
>>>>>>> 8e0f9a839520fed7932bb660778a56592ca8bdb2
