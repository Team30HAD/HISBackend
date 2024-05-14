package com.had.his.DAO;

import com.had.his.Entity.TestImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestImagesDAO extends JpaRepository<TestImages,Long> {

    @Query("select t from TestImages t where t.test.id=?1")
    List<TestImages> findTestById(Integer id);

<<<<<<< HEAD
    @Query("select t from TestImages t where t.test.id=?1 and t.testimageId=?2")
=======
    @Query("select t from TestImages t where t.tests.id=?1 and t.testimageId=?2")
>>>>>>> 8e0f9a839520fed7932bb660778a56592ca8bdb2
    TestImages findTestImageById(Integer id,Long testimageId);


    @Modifying
    @Query("delete from TestImages t where t.testimageId=?1")
    void deleteTestImageById(Long testimageId);
}
