package com.had.his.Service;

import com.had.his.DAO.*;
import com.had.his.DTO.LoginDTO;
import com.had.his.Entity.*;
import com.had.his.Response.LoginResponse;
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


    // Implement the remaining DAO injections for other entities as needed

    /*
    @Override
    public Patient bookAppointment() {
        // Implement logic to book an appointment
        return null;
    }*/


    @Override
    public Patient getPatientDetails(String pid) {
        return patientDAO.findPatientDetailsById(pid);
    }

    @Override
    public List<Patient> getAllPatients() {
        // Implement the logic to fetch all patients from the database
        // You can use a patient repository or any other method to retrieve the data
        return patientDAO.findAll(); // Assuming you have a patient repository
    }

    @Override
    public Patient addPatient(Patient patient) {
        return patientDAO.save(patient);
    }

    @Transactional
    public Visit bookAppointmentForExistingPatient(String pid, String did, Visit visit) {
        System.out.println("Visit details received: " + visit);
        System.out.println("Patient details after retrieval: " + visit.getPatient());
        // Set admittedDate to the current date
        visit.setAdmittedDate(LocalDate.now());

        // Set admittedTime to the current time
        visit.setAdmittedTime(LocalTime.now());
        // Set dischargedDate to null
        visit.setDischargedDate(null);
// Check if the patient exists
        //<Patient> patient = patientDAO.findById(pid).orElse(null);
        Patient attachedPatient = patientDAO.findPatientDetailsById(pid);
        Doctor attachedDoctor = doctorDAO.findByDoctorId(did);
        attachedDoctor.addVisit(visit);
        attachedPatient.addVisit(visit);
        doctorDAO.save(attachedDoctor);
        patientDAO.save(attachedPatient);
        visit.setPatient(attachedPatient);
        visit.setDoctor(attachedDoctor);
        System.out.println(attachedPatient);
//        if (attachedPatient == null) {
//            throw new RuntimeException("Patient not found");
//        }
//        visit.setPatient(attachedPatient);
//
//        if(visit.getDoctor()!=null && visit.getDoctor().getDoctorId()!=null)
//        {
//            Doctor attachedDoctor = doctorDAO.findDoctorDetailsById(visit.getDoctor().getDoctorId());
////            System.out.println(attachedPatient);
//            if (attachedDoctor == null) {
//                throw new RuntimeException("Doctor not found");
//            }
//            visit.setDoctor(attachedDoctor);
//        }
//        else {
//            // Handle the case where patient or patientId is null
//            throw new RuntimeException("Doctor or Doctor ID is null");
//        }

        return visitDAO.save(visit);
    }


    @Override
    public Visit bookAppointmentForNewPatient(String did, Visit visit) {
//        System.out.println("Visit details received: " + visit);
//        System.out.println("Patient details after retrieval: " + visit.getPatient());

        //Setting default visit details
        visit.setAdmittedDate(LocalDate.now());
        visit.setAdmittedTime(LocalTime.now());
        visit.setDischargedDate(null);
        visit.setEmergency(Boolean.valueOf("false"));
        visit.setDisease("Not yet examined");
        visit.setMedications(null);
        visit.setTests(null);
        if (visit.getSpecialization() == null) visit.setSpecialization("General Physician");

        //Taking the patient details as given
        if (visit.getPatient() != null) {
            Patient new_patient = visit.getPatient();
            if (new_patient.getDepartment() == null) new_patient.setDepartment("OP");
            visit.setPatient(new_patient);
            patientDAO.save(new_patient);
        } else {
            throw new RuntimeException("Patient is null");
        }

        //Finding the doctor to be assigned using path variable parameter
        Doctor attachedDoctor = doctorDAO.findByDoctorId(did);
        if (attachedDoctor == null) throw new RuntimeException("Doctor not found");
        visit.setDoctor(attachedDoctor);
        return visitDAO.save(visit);
    }

    @Override
    public Visit bookEmergencyAppointment(String did, Visit visit) {
        //Setting default visit details
        visit.setAdmittedDate(LocalDate.now());
        visit.setAdmittedTime(LocalTime.now());
        visit.setDischargedDate(null);
        visit.setEmergency(Boolean.valueOf("true"));
        visit.setDisease("Not yet examined");
        visit.setMedications(null);
        visit.setTests(null);
        if (visit.getSpecialization() == null) visit.setSpecialization("General Physician");

        //Taking the patient details as given or making new patient object
        Patient new_patient;
        if (visit.getPatient() != null) new_patient = visit.getPatient();
        else new_patient = new Patient();

        //Null field are updated with default values
        if (new_patient.getPatientName() == null) new_patient.setPatientName("Anonymous Patient");
        if (new_patient.getAge() == null) new_patient.setAge(0);
        if (new_patient.getSex() == null) new_patient.setSex("Unknown");
        if (new_patient.getContact() == null) new_patient.setContact("No Contact Provided");
        if (new_patient.getEmail() == null) new_patient.setEmail("No Email Provided");
        if (new_patient.getDepartment() == null) new_patient.setDepartment("OP");
        visit.setPatient(new_patient);
        patientDAO.save(new_patient);

        //Finding the doctor to be assigned using path variable parameter
//        if(visit.getDoctor()!=null && visit.getDoctor().getDoctorId()!=null)
//        Doctor attachedDoctor = doctorDAO.findDoctorDetailsById(visit.getDoctor().getDoctorId());
        Doctor attachedDoctor = doctorDAO.findByDoctorId(did);
        if (attachedDoctor == null) throw new RuntimeException("Doctor not found");
        visit.setDoctor(attachedDoctor);
        return visitDAO.save(visit);
    }


    @Override
    public Patient updatePatient(String patientId, Patient updatedPatient) {
        Patient existingPatient = patientDAO.findPatientDetailsById(patientId);
        // Update the fields you want to allow modification
        existingPatient.setPatientName(updatedPatient.getPatientName());
        existingPatient.setAge(updatedPatient.getAge());
        existingPatient.setSex(updatedPatient.getSex());
        existingPatient.setContact(updatedPatient.getContact());
        existingPatient.setEmail(updatedPatient.getEmail());
        existingPatient.setDepartment(updatedPatient.getDepartment());
        // Save the updated patient
        return patientDAO.save(existingPatient);
    }

    @Override
    public Patient deletePatientPII(String patientId) {
        Patient patient = patientDAO.findPatientDetailsById(patientId);
        // Set PII fields to null or empty
        assert patient != null;
        patient.setPatientName("Anonymous Patient");
//        patient.setAge(0);
//        patient.setSex("Male");
        patient.setContact("No Contact Provided");
        patient.setEmail("No Email Provided");
        return patientDAO.save(patient);
    }

    @Override
    public Patient deletePatientRecords(String patientId) {
        Patient patient = patientDAO.findPatientDetailsById(patientId);
        // Set PII fields to null or empty
        patient.setPatientName("Anonymous Patient");
//        patient.setAge(0);
//        patient.setSex("Male");
        patient.setContact("No Contact Provided");
        patient.setEmail("No Email Provided");
        patient.setDepartment("NA");
        patient.setPastHistories(null);
        patient.setSymptoms(null);
        patient.setSymptomImages(null);
        patient.setVitals(null);
        patient.setBed(null);
        patient.setProgress(null);
        patient.setVisit(new ArrayList<Visit>());
//        for(Visit visits:visitDAO.findAll())
//        {
//
//        }
        return patientDAO.save(patient);
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
    public List<Doctor> getDoctorsBySpecialization(String specialization) {
        return doctorDAO.getDoctorsBySpecialization(specialization);
    }
    // Implement other methods similarly...

    @Override
    public List<Bed> getAllBeds() {
        // Implement logic to get all beds
        return null;
    }

    @Override
    public List<Bed> getAvailableBeds() {
        // Implement logic to get available beds
        return null;
    }

    @Override
    public List<Bed> getFilledBeds() {
        // Implement logic to get filled beds
        return null;
    }
}