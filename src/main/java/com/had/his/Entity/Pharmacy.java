package com.had.his.Entity;

import com.had.his.Role.UserRole;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;

@Entity
@Component
@Table(name = "pharmacy")
public class Pharmacy implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pharmacy_seq")
    @SequenceGenerator(name = "pharmacy_seq", sequenceName = "pharmacy_sequence", allocationSize = 1)
    private Long Id;
    @Column(name="pharmacy_id",unique = true,nullable = false)
    private String pharmacyId;
    @Column(name="name",nullable = false)
    private String name;
    @Column(name="address")
    private String address;
    @Column(name="email",unique = true,nullable = false)
    private String email;
    @Column(name="contact",nullable = false,unique = true)
    private String contact;

    @Column(name="active",nullable = false)
    private Boolean active;

    @Column(name = "license_number")
    private String licenseNumber;

    @Column(name="password",unique = true)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    public Pharmacy() {

    }

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getPharmacyId() {
        return pharmacyId;
    }
    public void setPharmacyId(String pharmacyId) {
        this.pharmacyId = pharmacyId;
        generateEmail();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        generateEmail();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("PHARMACY"));
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public boolean isPasswordMatch(String enteredPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(enteredPassword, this.password);
    }

    public void generateEmail() {
        if (this.name != null && !this.name.trim().isEmpty() && this.pharmacyId != null) {
            this.email = this.name.trim().toLowerCase().replaceAll("\\s+", "") + this.pharmacyId.replaceAll("\\D+", "") + "@his.com";
        }
    }

    @PrePersist
    public void generatePharmacyId() {
        if (Id != null) {
            pharmacyId = "PH" + String.format("%02d", Id);
            generateEmail();
        }
    }
    public Pharmacy(Long Id, String pharmacyId, String name, String address , String contact,Boolean active ,String password, String licenseNumber,UserRole role) {
        this.Id = Id;
        this.pharmacyId=pharmacyId;
        this.name = name;
        this.address = address;
        this.password = password;
        this.contact = contact;
        this.active = active;
        this.licenseNumber=licenseNumber;
        this.role=role;
        generateEmail();
    }

    @Override
    public String toString() {
        return "Pharmacy{" +
                "Id=" + Id +
                ", pharmacyId='" + pharmacyId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", contact='" + contact + '\'' +
                ", active=" + active +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

