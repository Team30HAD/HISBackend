package com.had.his.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name="testimages")
public class TestImages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="testimageId")
    private Long testimageId;

    @Column(columnDefinition = "MEDIUMTEXT",nullable = false)
    private String image;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="tid" ,nullable = false)
    private Test tests;

    public TestImages() {
    }

    public TestImages(Long testimageId, String image, Test tests) {
        this.testimageId = testimageId;
        this.image = image;
        this.tests = tests;
    }

    public Long getTestimageId() {
        return testimageId;
    }

    public void setTestimageId(Long testimageId) {
        this.testimageId = testimageId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Test getTests() {
        return tests;
    }

    public void setTests(Test tests) {
        this.tests = tests;
    }

    @Override
    public String toString() {
        return "TestImages{" +
                "testimageId=" + testimageId +
                ", image='" + image + '\'' +
                ", tests=" + tests +
                '}';
    }
}