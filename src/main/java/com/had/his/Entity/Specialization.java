package com.had.his.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
<<<<<<< HEAD
import jakarta.validation.constraints.NotEmpty;
=======
>>>>>>> 8e0f9a839520fed7932bb660778a56592ca8bdb2

import java.util.List;

@Table(name="specializations")
@Entity
public class Specialization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="specialization_id")
    private Long sId;

<<<<<<< HEAD
    @NotEmpty(message = "Mention specialization")
=======
>>>>>>> 8e0f9a839520fed7932bb660778a56592ca8bdb2
    @Column(name = "specialization_name",unique = true,nullable = false)
    private String specializationName;

    @JsonIgnore
    @OneToMany(mappedBy = "specialization",cascade = CascadeType.ALL)
    private List<Doctor> doctors;

    public Long getsId() {
        return sId;
    }

    public void setsId(Long sId) {
        this.sId = sId;
    }

    public String getSpecializationName() {
        return specializationName;
    }

    public void setSpecializationName(String specializationName) {
        this.specializationName = specializationName;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public Specialization() {
    }

    public Specialization(Long sId, String specializationName, List<Doctor> doctors) {
        this.sId = sId;
        this.specializationName = specializationName;
        this.doctors = doctors;
    }


    @Override
    public String toString() {
        return "Specialization{" +
                "sId=" + sId +
                ", specializationName='" + specializationName + '\'' +
                ", doctors=" + doctors +
                '}';
    }
}
