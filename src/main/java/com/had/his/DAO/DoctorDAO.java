package com.had.his.DAO;

import com.had.his.Entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface DoctorDAO extends JpaRepository<Doctor,Long> {
     @Query("select d from Doctor d where d.email=?1")
     Doctor findByEmail(String email);

     Doctor findByDoctorId(String did);

     @Query("select d from Doctor d where d.availability=true and d.department='IP' and d.active=true and d.specialization=?1")
     List<Doctor> getIPDoctorsBySpecialization(String specialization);

     @Query("select d from Doctor d where d.department=?1 and d.active=true")
     List<Doctor> getDoctorByDepartmentAndActive(String department);

     @Query("select d from Doctor d where d.department = 'IP'")
     List<Doctor> findIndoorDoctorDetails();

     @Query("select d from Doctor d where d.department = 'OP'")
     List<Doctor> findOutdoorDoctorDetails();

     @Query("select d from Doctor d where d.availability=true and d.specialization=?1")
     List<Doctor> getDoctorsBySpecialization(String specialization);

}
