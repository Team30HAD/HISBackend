package com.had.his.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctor_seq")
    @SequenceGenerator(name = "doctor_seq", sequenceName = "doctor_sequence", allocationSize = 1)
    private Long Id;

    @Column(name="doctor_id",unique = true,nullable = false)
    private String doctorId;

    @Column(nullable = false)
    private String name;
    private Integer age;
    private String sex;
    @Column(nullable = false)
    private String qualification;
    @Column(nullable = false)
    private String specialization;
    @Column(nullable = false)
    private String department;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    private String contact;

    @Column(name = "license_number", unique = true, nullable = false)
    private String licenseNumber;

    @Column(name="availability",nullable = false)
    private Boolean availability;

    @Column(name="active",nullable = false)
    private Boolean active;

    @Column(nullable = false)
    private String password;

    @Column(name="profile_photo", columnDefinition = "MEDIUMTEXT")
    private String photo;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade= CascadeType.ALL)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "doctor"})
    private List<Visit> visits;

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
        generateEmail();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        generateEmail();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }


    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }

    public boolean isPasswordMatch(String enteredPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(enteredPassword, this.password);
    }



    private void generateEmail() {
        if (this.name != null && !this.name.trim().isEmpty() && this.doctorId != null) {
            this.email = this.name.trim().toLowerCase().replaceAll("\\s+", "") + this.doctorId.replaceAll("\\D+", "") + "@his.com";
        }
    }

    @PrePersist
    public void generateDoctorId() {
        if (Id != null) {
            doctorId = "D" + String.format("%02d", Id);
            generateEmail();
        }
    }

    public void addVisit(Visit newVisit) {
        if (visits == null) {
            visits = new ArrayList<>();
        }
        visits.add(newVisit);
        newVisit.setDoctor(this);
    }

    public Doctor(Long Id, String doctorId,String name, Integer age, String sex, String qualification,
                  String specialization, String department, String contact, String licenseNumber ,Boolean availability ,Boolean active ,List<Visit> visits ,String photo, String password) {
        this.Id = Id;
        this.doctorId=doctorId;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.qualification = qualification;
        this.specialization = specialization;
        this.department = department;
        this.contact = contact;
        this.licenseNumber = licenseNumber;
        this.availability = availability;
        this.active = active;
        this.visits = visits;
        this.password = password;
        this.photo = photo;

        generateEmail();
    }


    public Doctor() {
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "Id=" + Id +
                ", doctorId='" + doctorId + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", qualification='" + qualification + '\'' +
                ", specialization='" + specialization + '\'' +
                ", department='" + department + '\'' +
                ", email='" + email + '\'' +
                ", contact='" + contact + '\'' +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", availability=" + availability +
                ", active=" + active +
                ", password='" + password + '\'' +
                ", photo='" + photo + '\'' +
                ", visits=" + visits +
                '}';
    }
}

