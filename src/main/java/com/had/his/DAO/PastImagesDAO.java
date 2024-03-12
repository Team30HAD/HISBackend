package com.had.his.DAO;

import com.had.his.Entity.PastHistory;
import com.had.his.Entity.PastImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PastImagesDAO extends JpaRepository<PastImages,Integer> {

    @Query("select pi from PastImages pi where pi.pastHistory.historyId = ?1")
    List<PastImages> getPastImagesByPastHistory(Integer phid);

    @Query("delete from PastImages p where p.imgId=?1")
    void deleteByPastImgId(Integer imgId);

}
