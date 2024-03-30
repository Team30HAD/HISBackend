package com.had.his.Service;

import com.had.his.DAO.*;
import com.had.his.DTO.AppointmentDTO;
import com.had.his.DTO.LoginDTO;
import com.had.his.Entity.*;
import com.had.his.Response.LoginResponse;
import com.had.his.Security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ReceptionistServiceImpl implements ReceptionistService {

    @Autowired
    private PatientDAO patientDAO;

    @Autowired
    private VisitDAO visitDAO;

    @Autowired
    private DoctorDAO doctorDAO;

    @Autowired
    private ReceptionistDAO receptionistDAO;

    @Autowired
    private SymptomsDAO symptomsDAO;

    @Autowired
    private VitalsDAO vitalsDAO;

    @Autowired
    private SpecializationDAO specializationDAO;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    public LoginResponse verifyReceptionist(LoginDTO loginDto) {
        Receptionist receptionist = receptionistDAO.findByEmail(loginDto.getEmail());

        if (receptionist != null && receptionist.getActive().equals(true)) {

            List<ReceptionistSchedule> schedules = receptionist.getReceptionistSchedules();

            if (!schedules.isEmpty()) {
                LocalDate currentDate = LocalDate.now();
                LocalTime currentTime = LocalTime.now();

                for (ReceptionistSchedule schedule : schedules) {
                    if (currentDate.getDayOfWeek() == schedule.getDay() &&
                            currentTime.isAfter(schedule.getStartTime()) &&
                            currentTime.isBefore(schedule.getEndTime())) {

                        String password = loginDto.getPassword();

                        boolean passMatch = receptionist.isPasswordMatch(password);

                        if (passMatch) {
                            System.out.println("Password matched");

                            String jwtToken = jwtTokenProvider.generateToken(receptionist);
                            return new LoginResponse("Login Successful", true, jwtToken);
                        } else {
                            return new LoginResponse("Password not matched", false, null);
                        }
                    }
                }
                return new LoginResponse("Receptionist can't login at this time.", false, null);
            } else {
                return new LoginResponse("Receptionist schedules not found.", false, null);
            }
        } else {
            return new LoginResponse("Email not found or receptionist is not active", false, null); // Adjusted error message
        }
    }

    @Override
    public Patient getPatientDetails(String pid) {
        return patientDAO.findPatientDetailsById(pid);
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientDAO.findAll();
    }


    @Transactional
    public Visit bookAppointmentForExistingPatient(String email,String pid, AppointmentDTO appointment) {
        Patient patient = patientDAO.findPatientDetailsById(pid);
        if (!appointment.getEmergency()){
            patient.setAge(appointment.getAge());
            patient.setContact(appointment.getContact());
            patient.setEmail(appointment.getEmail());
        }
        patient.setDepartment("OP");
        Visit visit = new Visit();
        Doctor doctor = doctorDAO.findByDoctorId(appointment.getDoctorId());
        visit.setDoctor(doctor);
        visit.setEmergency(appointment.getEmergency());
        visit.setPatient(patient);
        visit.setSpecialization(appointment.getCategory());
        visit.setAdmittedDate(LocalDate.now());
        visit.setAdmittedTime(LocalTime.now());
        patient.addVisit(visit);
        patientDAO.save(patient);
//        Consent consent = consentDAO.getConsentByPatient(pid);
//        if(consent.getExpired()){
//            consent.generateNewToken();
//            consent.setExpired(false);
//            consent.setTakenOn(LocalDate.now());
//            consent.setTakenBy(email);
//            consentDAO.save(consent);
//        }
        return visitDAO.save(visit);
    }

    @Override
    public Visit bookAppointmentForNewPatient(String email,AppointmentDTO appointment) {
        Patient patient = new Patient();
        if (appointment.getEmergency()) {
            // Set default values for emergency patients
            patient.setPatientName("Emergency Patient");
            patient.setAge(30);
            patient.setSex("Unknown");
            patient.setContact("911");
            patient.setEmail("emergency@example.com");
        } else {
            // Set values based on appointment details
            patient.setPatientName(appointment.getName());
            patient.setAge(appointment.getAge());
            patient.setSex(appointment.getSex());
            patient.setContact(appointment.getContact());
            patient.setEmail(appointment.getEmail());
        }
        patient.setDepartment("OP");
        Visit visit = new Visit();
        visit.setDoctor(doctorDAO.findByDoctorId(appointment.getDoctorId()));
        visit.setEmergency(appointment.getEmergency());
        visit.setPatient(patient);
        visit.setSpecialization(appointment.getCategory());
        visit.setAdmittedDate(LocalDate.now());
        visit.setAdmittedTime(LocalTime.now());
        visit.setDischargedDate(null);
        visit.setMedications(null);
        visit.setTests(null);
        patient.addVisit(visit);
        patientDAO.save(patient);
//    Consent consent = new Consent();
//    consent.generateNewToken();
//    consent.setExpired(false);
//    consent.setTakenOn(LocalDate.now());
//    consent.setTakenBy(email);
//    consent.setPatient(patient);
//    consentDAO.save(consent);
        return visitDAO.save(visit);
    }


    @Override
    public Patient updatePatient(String patientId, Patient updatedPatient) {
        Patient existingPatient = patientDAO.findPatientDetailsById(patientId);
        existingPatient.setPatientName(updatedPatient.getPatientName());
        existingPatient.setAge(updatedPatient.getAge());
        existingPatient.setSex(updatedPatient.getSex());
        existingPatient.setContact(updatedPatient.getContact());
        existingPatient.setEmail(updatedPatient.getEmail());
        existingPatient.setDepartment(updatedPatient.getDepartment());
        return patientDAO.save(existingPatient);
    }

    @Override
    public Patient deletePatientPII(String patientId) {
        Patient patient = patientDAO.findPatientDetailsById(patientId);
        assert patient != null;
        patient.setPatientName("Anonymous Patient");
        patient.setAge(0);
        patient.setSex("NA");
        patient.setContact("No Contact Provided");
        patient.setEmail("No Email Provided");
        return patientDAO.save(patient);
    }

    @Override
    public void deletePatientRecords(String patientId) {
        Patient patient = patientDAO.findPatientDetailsById(patientId);
        patient.setPatientName("Anonymous Patient");
        patient.setAge(0);
        patient.setSex("NA");
        patient.setContact("No Contact Provided");
        patient.setEmail("No Email Provided");
        patient.setDepartment("NA");
//        pastHistoryDAO.deleteAll(pastHistoryDAO.getPastHistoriesByPatient(patientId));
        vitalsDAO.deleteVitalsByPatient(patientId);
        symptomsDAO.delete(symptomsDAO.getSymptomsByPatient(patientId));
//        patient.setPastHistories(null);
//        patient.setSymptoms(null);
//        patient.setSymptomImages(null);
//        patient.setVitals(null);
//        patient.setBed(null);
//        patient.setProgress(null);
//        patient.setVisit(new ArrayList<Visit>());
        patientDAO.save(patient);
    }

    @Override
    public List<Patient> getIndoorPatients() {
        return patientDAO.findIndoorPatientDetails();
    }

    @Override
    public List<Patient> getOutdoorPatients() {
        return patientDAO.findOutdoorPatientDetails();
    }


    @Override
    public List<Doctor> getIndoorDoctors() {
        return doctorDAO.findIndoorDoctorDetails();
    }

    @Override
    public List<Doctor> getOutdoorDoctors() {
        return doctorDAO.findOutdoorDoctorDetails();
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorDAO.findAll();
    }

    @Override
    public List<Doctor> getOutdoorDoctorsBySpecialization(String specialization) {
        return doctorDAO.getOutdoorDoctorsBySpecialization(specialization);
    }

    @Override
    public List<String> getSpecialization() {
        return specializationDAO.findALLSpecializations();    }
}