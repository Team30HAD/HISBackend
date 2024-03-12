package com.had.his.DAO;

import com.had.his.Entity.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NurseDAO extends JpaRepository<Nurse,Long> {

    Nurse findByEmail(String email);

    @Query("select n from Nurse n where n.nurseId=?1")
    Nurse findDetailsById(String nurseId);

    @Query("select n from Nurse n  where n.active=true")
    List<Nurse> getNurseByActive();

}
