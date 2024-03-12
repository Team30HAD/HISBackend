package com.had.his.DAO;

import com.had.his.Entity.Nurse;
import com.had.his.Entity.Receptionist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReceptionistDAO extends JpaRepository<Receptionist,String> {
    @Query("select recep from Receptionist recep where recep.receptionistId=?1")
    Receptionist findReceptionistByReceptionistId(String rid);
    @Query("select r from Receptionist r where r.active=true")
    List<Receptionist> getReceptionistByActive();
}