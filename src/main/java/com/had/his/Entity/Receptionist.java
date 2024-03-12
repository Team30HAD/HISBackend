package com.had.his.Entity;

import jakarta.persistence.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name = "receptionists")
public class Receptionist {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "receptionist_seq")
    @SequenceGenerator(name = "receptionist_seq", sequenceName = "receptionist_sequence", allocationSize = 1)
    private Long Id;


    @Column(name = "receptionist_id",unique = true,nullable = false)
    private String receptionistId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "sex")
    private String sex;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "contact",nullable = false,unique = true)
    private String contact;

    @Column(name="active",nullable = false)
    private Boolean active;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "profile_photo", columnDefinition = "MEDIUMTEXT")
    private String photo;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getReceptionistId() {
        return receptionistId;
    }

    public void setReceptionistId(String receptionistId) {
        this.receptionistId = receptionistId;
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



    public boolean isPasswordMatch(String enteredPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(enteredPassword, this.password);
    }


    private void generateEmail() {
        if (this.name != null && !this.name.trim().isEmpty() && this.receptionistId != null) {
            this.email = this.name.trim().toLowerCase().replaceAll("\\s+", "") + this.receptionistId.replaceAll("\\D+", "") + "@his.com";
        }
    }

    @PrePersist
    public void generateReceptionistId() {
        if (Id != null) {
            receptionistId = "R" + String.format("%02d", Id);
            generateEmail();
        }
    }

    public Receptionist() {

    }

    public Receptionist(Long Id,String receptionistId,String name, Integer age, String sex, String contact,Boolean active, String password,
                        String photo) {
        this.Id = Id;
        this.receptionistId=receptionistId;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.contact = contact;
        this.active = active;
        this.password = password;
        this.photo = photo;

        generateEmail();
    }

    @Override
    public String toString() {
        return "Receptionist{" +
                "Id=" + Id +
                ", receptionistId='" + receptionistId + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", email='" + email + '\'' +
                ", contact='" + contact + '\'' +
                ", active=" + active +
                ", password='" + password + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}

