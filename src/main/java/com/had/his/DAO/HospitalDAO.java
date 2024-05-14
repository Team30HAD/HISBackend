package com.had.his.DAO;

import com.had.his.Entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HospitalDAO extends JpaRepository<Hospital,String> {
<<<<<<< HEAD

=======
>>>>>>> 8e0f9a839520fed7932bb660778a56592ca8bdb2
    @Query("SELECT h FROM Hospital h")
    Hospital getHospitalDetails();
}
