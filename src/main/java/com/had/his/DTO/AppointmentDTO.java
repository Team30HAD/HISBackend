package com.had.his.DTO;

<<<<<<< HEAD
import jakarta.validation.constraints.NotEmpty;
import org.checkerframework.checker.units.qual.N;

public class AppointmentDTO {


    private String name;


    private String age;


    private String sex;


=======
public class AppointmentDTO {

    private String name;

    private Integer age;

    private String sex;

>>>>>>> 8e0f9a839520fed7932bb660778a56592ca8bdb2
    private String contact;

    private String email;

    private String category;

    private Boolean emergency;

    private String doctorId;

    private Boolean consent;

    public AppointmentDTO() {
    }

<<<<<<< HEAD
    public AppointmentDTO(String name, String age, String sex, String contact, String email, String category, Boolean emergency, String doctorId, Boolean consent) {
=======
    public AppointmentDTO(String name, Integer age, String sex, String contact, String email, String category, Boolean emergency, String doctorId, Boolean consent) {
>>>>>>> 8e0f9a839520fed7932bb660778a56592ca8bdb2
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.contact = contact;
        this.email = email;
        this.category = category;
        this.emergency = emergency;
        this.doctorId = doctorId;
        this.consent = consent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

<<<<<<< HEAD
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
=======
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
>>>>>>> 8e0f9a839520fed7932bb660778a56592ca8bdb2
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getEmergency() {
        return emergency;
    }

    public void setEmergency(Boolean emergency) {
        this.emergency = emergency;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public Boolean getConsent() {
        return consent;
    }

    public void setConsent(Boolean consent) {
        this.consent = consent;
    }

    @Override
    public String toString() {
        return "AppointmentDTO{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", sex='" + sex + '\'' +
                ", contact='" + contact + '\'' +
                ", email='" + email + '\'' +
                ", category='" + category + '\'' +
                ", emergency='" + emergency + '\'' +
                ", doctorId='" + doctorId + '\'' +
                ", consent=" + consent +
                '}';
    }
}
