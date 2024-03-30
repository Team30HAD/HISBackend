package com.had.his.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name="visits")
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="visit_id")
    private Long visitId;

    @Column(name="admitted_date")
    private LocalDate admittedDate;

    @Column(name="admitted_time")
    private LocalTime admittedTime;

    @Column(name="discharge_date")
    private LocalDate dischargedDate;

    @Column(name = "emergency", nullable = false)
    private Boolean emergency;

    @Column(name = "specialization", nullable = false)
    private String specialization;

    @Column(name="disease")
    private String disease;

    @ManyToOne
    @JoinColumn(name = "did", nullable = false,referencedColumnName = "doctor_id")
    @JsonIgnore
    private Doctor doctor;

    @JsonIgnore
    @OneToMany(mappedBy = "visit",cascade = CascadeType.ALL)
    private List<Medication> medications;

    @JsonIgnore
    @OneToMany(mappedBy = "visit",cascade = CascadeType.ALL)
    private List<Test> tests;

    @ManyToOne
    @JoinColumn(name = "pid", nullable = false,referencedColumnName = "patient_id")
    @JsonIgnore
    private Patient patient;

    public Visit() {
    }

    public Visit(Long visitId, LocalDate admittedDate, LocalTime admittedTime, LocalDate dischargedDate, Boolean emergency, String specialization, String disease, String doctorId, String patientId) {
        this.visitId = visitId;
        this.admittedDate = admittedDate;
        this.admittedTime = admittedTime;
        this.dischargedDate = dischargedDate;
        this.emergency = emergency;
        this.specialization = specialization;
        this.disease = disease;

        this.doctor = new Doctor();
        doctor.setDoctorId(doctorId);

        this.patient = new Patient();
        patient.setPatientId(patientId);
    }

    public Long getVisitId() {
        return visitId;
    }

    public void setVisitId(Long visitId) {
        this.visitId = visitId;
    }


    public LocalDate getAdmittedDate() {
        return admittedDate;
    }

    public void setAdmittedDate(LocalDate admittedDate) {
        this.admittedDate = admittedDate;
    }

    public LocalTime getAdmittedTime() {
        return admittedTime;
    }

    public void setAdmittedTime(LocalTime admittedTime) {
        this.admittedTime = admittedTime;
    }

    public LocalDate getDischargedDate() {
        return dischargedDate;
    }

    public void setDischargedDate(LocalDate dischargedDate) {
        this.dischargedDate = dischargedDate;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Boolean getEmergency() {
        return emergency;
    }

    public void setEmergency(Boolean emergency) {
        this.emergency = emergency;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    @Override
    public String toString() {
        return "Visit{" +
                "visitId=" + visitId +
                ", admittedDate=" + admittedDate +
                ", admittedTime=" + admittedTime +
                ", dischargedDate=" + dischargedDate +
                ", emergency=" + emergency +
                ", specialization='" + specialization + '\'' +
                ", disease='" + disease + '\'' +
                ", doctor=" + doctor +
                ", medications=" + medications +
                ", tests=" + tests +
                ", patient=" + patient +
                '}';
    }
}
