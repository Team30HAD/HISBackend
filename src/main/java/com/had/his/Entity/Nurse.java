package com.had.his.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Entity
@Table(name = "nurses")
public class Nurse {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nurse_seq")
    @SequenceGenerator(name = "nurse_seq", sequenceName = "nurse_sequence", allocationSize = 1)
    private Long Id;


    @Column(name="nurse_id",nullable = false,unique = true)
    private String nurseId;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="age")
    private int age;

    @Column(name="sex")
    private String sex;

    @Column(name="contact", nullable = false, unique = true)
    private String contact;

    @Column(name="email", unique = true, nullable = false)
    private String email;

    @Column(name="active",nullable = false)
    private Boolean active;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name="profilephoto", columnDefinition = "MEDIUMTEXT")
    private String photo;

    @OneToMany(mappedBy = "nurse",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","nurse"})
    private List<NurseSchedule> nurseSchedules;

    public Nurse() {
    }

    public Nurse(Long Id,String nurseId, String name, int age, String sex, String contact,Boolean active , String photo, String password , List<NurseSchedule> nurseSchedules) {
        this.Id = Id;
        this.nurseId = nurseId;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.contact = contact;
        this.active = active;
        this.password = password;
        this.photo = photo;
        this.nurseSchedules = nurseSchedules;
        generateEmail();
    }


    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getNurseId() {
        return nurseId;
    }


    public void setNurseId(String nurseId) {
        this.nurseId = nurseId;
        generateEmail();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        generateEmail();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public List<NurseSchedule> getNurseSchedules() {
        return nurseSchedules;
    }

    public void setNurseSchedules(List<NurseSchedule> nurseSchedules) {
        this.nurseSchedules = nurseSchedules;
    }


    public boolean isPasswordMatch(String enteredPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(enteredPassword, this.password);
    }


    private void generateEmail() {
        if (this.name != null && !this.name.trim().isEmpty() && this.nurseId != null) {
            this.email = this.name.trim().toLowerCase().replaceAll("\\s+", "") + this.nurseId.replaceAll("\\D+", "") + "@his.com";

        }
    }

    @PrePersist
    public void generateNurseId() {
        if (Id != null) {
            nurseId = "N" + String.format("%02d", Id);
            generateEmail();
        }
    }

    @Override
    public String toString() {
        return "Nurse{" +
                "Id=" + Id +
                ", nurseId='" + nurseId + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", contact='" + contact + '\'' +
                ", email='" + email + '\'' +
                ", active=" + active +
                ", password='" + password + '\'' +
                ", photo='" + photo + '\'' +
                ", nurseSchedules=" + nurseSchedules +
                '}';
    }
}



