package com.otod.server.otod.respository.vrss;

import com.otod.server.otod.model.vrss.FileInfo;
import com.otod.server.otod.model.vrss.Tag;
import com.otod.server.otod.model.vrss.VrssUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileInfoRepository extends JpaRepository<FileInfo,Integer> {
    List<FileInfo> findAllByOrderByLove();
    List<FileInfo> findAllByOrderByViews();
    List<FileInfo> findAllByOrderByScore();
    List<FileInfo> findAllByOrderByDownloadCnt();
    List<FileInfo> findAllByOrderByUploadTime();
    List<FileInfo> findAllByTag(Tag tag);
    List<FileInfo> findByVrssUser(VrssUser vrssUser);
    @Query(value="select f from FileInfo f where f.name like %:p% or f.description like %:p% or f.type like %:p%")
    List<FileInfo> findBykey(@Param("p") String key);
}
