package com.had.his.DAO;

import com.had.his.Entity.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpecializationDAO extends JpaRepository<Specialization,Long> {
    @Query("select distinct s.specializationName from Specialization s")
    List<String> findALLSpecializations();
}
