package com.had.his.DAO;

import com.had.his.Entity.Bed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BedDAO extends JpaRepository<Bed,String> {
    @Query("select b from Bed b where b.patient is null order by b.bId asc limit 1")
    Bed getFirstFreeBed();
}
